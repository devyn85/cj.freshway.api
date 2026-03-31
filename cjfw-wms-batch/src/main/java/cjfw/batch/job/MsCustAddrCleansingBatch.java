package cjfw.batch.job;

import cjfw.batch.common.BatchJobListener;
import cjfw.batch.common.SetParamService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.dto.MsCustReqDto;
import cjfw.batch.dto.MsCustResDto;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.common.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 김환수 (hwansoo.kim@cj.net)
 * @date : 2025.11.11
 * @description : MS_CUST(거래처마스터) 주소정제 WITH NAVER MAP API (위경도 및 행정동코드 현행화)  
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.11 김환수 (hwansoo.kim@cj.net) 생성 </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MsCustAddrCleansingBatch {

    private final CommonDao commonDao;
    private final String BATCH_JOB_NAME = "msCustAddrCleansingJob";

    private final SetParamService setParamService;

    private static final String API_STATUS_OK = "OK";
    private static final String API_STATUS_FAIL = "FAILED";
    private static final String API_ERR_200 = "200"; //Authentication Failed

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private BatchJobListener batchJobListener;

    @Bean
    public Job msCustAddrCleansingJob() {
        return new JobBuilder(BATCH_JOB_NAME, jobRepository)
                .listener(batchJobListener)
                .flow(execStep())
                .end()
                .build();
    }

    @Bean
    @StepScope
    public Step execStep() {
        return new StepBuilder("execStep", jobRepository)
                .tasklet(execTasklet(), transactionManager)
                .build();
    }

    public Tasklet execTasklet() {
        return (contribution, chunkContext) -> {
            // 배치 파라미터 조회.
            BatchLogParamsDto batchLogDto = BatchLogParamsDto.builder()
                    .jobExecutionId(Long.toString(chunkContext.getStepContext().getStepExecution().getJobExecution().getId()))
                    .jobName(BATCH_JOB_NAME)
                    .jobDiv("JAVA")
                    .nodeLevel(0)
                    .jobStatus("")
                    .command("")
                    .lineNo("")
                    .resultCode("0")
                    .resultMsg("")
                    .build();

            //중복실행 방지를 위해 현재 JOB_INSTANCE_ID
            //long instanceId = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getId()

            Map<String, Object> jobParams = chunkContext.getStepContext().getJobParameters();
            BatchParamsUtil batchParam = setParamService.getObjectBatchParam(jobParams, batchLogDto.getJobExecutionId());

            //*확장*로그기록공통변수(공통 + {작업구분}^{LOGGING_CNT}^{FETCH_CNT}^{DELAY_TIME})
            //     : API_JOB     [5]: A(전체)|B(map-geocode)|C(map-reversegeocode)
            //     : LOGGING_CNT [6]: LOGGING COUNT
            //     : FETCH_CNT   [7]: SQL ROW FETCH COUNT
            //     : DELAY_TIME  [8]: Y(100~500ms Random)|N
            String[] logOption = batchParam.getAVC_LOG_PARAMS().split("\\^"); //AVC_LOG_PARAMS 문자열 분리
            String apiJob      = logOption[5];
            long   loggingCnt  = Long.valueOf(logOption[6]);
            String fetchCnt    = logOption[7];
            String ynDelay     = logOption[8];

            MsCustReqDto custReqDto = new MsCustReqDto();
            custReqDto.setJobName(BATCH_JOB_NAME);
            custReqDto.setFetchCnt(fetchCnt);

            long loopCnt      = 0;
            //FOR위경도
            long cntApiCall01 = 0; //NAVER API CALL COUNT
            long cntUpdate01  = 0; //MS_CUST UPDATE COUNT
            //FOR행정동
            long cntApiCall02 = 0; //NAVER API CALL COUNT
            long cntUpdate02  = 0; //MS_CUST UPDATE COUNT

            List<MsCustResDto> custListB = null;
            List<MsCustResDto> custListC = null;

            try {
                logSystemOut(batchLogDto, "START", logOption[4], "200", "JAVA BATCH START: "+batchParam.getAVC_LOG_PARAMS());

                loggingCnt = (loggingCnt<50)?50:loggingCnt; // 진행중 표시는 위한 로깅 단위 (최소 50이상)

                /////////////////////////////////
                //Step.B: 위경도좌표 처리      //
                /////////////////////////////////
                if (apiJob.equals("A")||apiJob.equals("B")) {
                    custReqDto.setApiJob("B"); //API_JOB(map-geocode) 위경도API

                    custListB = commonDao.selectList("msCustAddrCleansingService.selectGeocodeList", custReqDto);
                    logSystemOut(batchLogDto, "STEP.B LOOP", logOption[4], "300", "selectGeocodeList.Rows():"+String.valueOf(custListB.size()));

                    loopCnt = 0;
                    for (MsCustResDto resDto : custListB) {
                        boolean ynFirstTry = false;

                        //Step.B10: 1순위(기본주소-지번:ADDRESS1+ADDRESS2)
                        if (resDto.getAddress1() != null) {
                            callMapApi(resDto, "1"); ynFirstTry = true;
                            cntApiCall01++;
                            if (StringUtils.isNotEmpty(resDto.getErrcode()) &&
                                    resDto.getErrcode().equals(API_ERR_200)) {
                                throw new UserHandleException(API_ERR_200);  //Authentication Failed!
                            }
                        }
                        //Step.B20: 2순위(도로명주소:ADDRESS3+ADDRESS3)
                        if ( (resDto.getAddress3() != null) &&	//도로명주소가 있어야 함
                                ( (!ynFirstTry) || 					//지번기준 api호출이 없는 경우
                                        (ynFirstTry && (StringUtils.isEmpty(resDto.getX()) || StringUtils.isEmpty(resDto.getY()))) //지번기준 api호출후 위경도값 없는 경우
                                ) ) {
                            callMapApi(resDto, "3");
                            cntApiCall01++;
                            if (StringUtils.isNotEmpty(resDto.getErrcode()) &&
                                    resDto.getErrcode().equals(API_ERR_200)) {
                                throw new UserHandleException(API_ERR_200);  //Authentication Failed
                            }
                        }

//						//주소정제 결과 적용: 위경도정보(단건처리)
//						if (StringUtils.isNotEmpty(resDto.getStatus()) &&
//							resDto.getStatus().equals(API_STATUS_OK)   && 
//							StringUtils.isNotEmpty(resDto.getX())      && 
//							StringUtils.isNotEmpty(resDto.getY())) {
//							int cntRows = commonDao.update("msCustAddrCleansingService.updateMsCust", resDto): // <==
//							cntUpdate01 = cntUpdate01 + cntRows: // <==
//						}

                        //동시접속에 의한 block예방용도
                        if (ynDelay.equals("Y")) { Thread.sleep(((int)(Math.random()*3)+1)*50); } // 50|100|150

                        loopCnt++;
                        if (loopCnt % loggingCnt == 0) {
                            logSystemOut(batchLogDto, "......>>>", logOption[4], "400", "API처리건수...:"+String.valueOf(loopCnt));
                            loopCnt = 0;
                        }
                    }

//					//주소정제 결과 적용: 위경도정보(BULK처리)
//					if (custListB.size() > 0) {
//						cntUpdate01 = commonDao.update("msCustAddrCleansingService.updateMsCustBulk", custListB): // <==
//					}
                }

                /////////////////////////////////
                //Step.C: 행정동코드 처리      //
                /////////////////////////////////
                if (apiJob.equals("A")||apiJob.equals("C")) {
                    custReqDto.setApiJob("C"); //API_JOB(map-reversegeocode) 행정동코드API

                    custListC = commonDao.selectList("msCustAddrCleansingService.selectAdmcodeList", custReqDto);
                    logSystemOut(batchLogDto, "STEP.C LOOP", logOption[4], "500", "selectAdmcodeList.Rows():"+String.valueOf(custListC.size()));

                    loopCnt = 0;
                    for (MsCustResDto resDto : custListC) {
                        callMapApi(resDto, "NA");
                        cntApiCall02++;
                        if (StringUtils.isNotEmpty(resDto.getErrcode()) &&
                                resDto.getErrcode().equals(API_ERR_200)) {
                            throw new UserHandleException(API_ERR_200);  //Authentication Failed
                        }

//						//주소정제 결과 적용: 행정동코드정보(단건처리)
//						if (StringUtils.isNotEmpty(resDto.getStatus()) &&
//							resDto.getStatus().equals(API_STATUS_OK)   && 
//							StringUtils.isNotEmpty(resDto.getAdmcd())) {
//							int cntRows = commonDao.update("msCustAddrCleansingService.updateMsCust", resDto): // <==
//							cntUpdate02 = cntUpdate02 + cntRows: // <==
//						}

                        //동시접속에 의한 block예방용도
                        if (ynDelay.equals("Y")) { Thread.sleep(((int)(Math.random()*3)+1)*50); } // 50|100|150

                        loopCnt++;
                        if (loopCnt % loggingCnt == 0) {
                            logSystemOut(batchLogDto, "......>>>", logOption[4], "600", "API처리건수...:"+String.valueOf(loopCnt));
                            loopCnt = 0;
                        }
                    }

//					//주소정제 결과 적용: 행정동코드정보(BULK처리)
//					if (custListC.size() > 0) {
//						cntUpdate02 = commonDao.update("msCustAddrCleansingService.updateMsCustBulk", custListC): // <==
//					}
                }

                //NAVER API호출 완료후 DB UPDATE처리
                //주소정제 결과 적용: 위경도정보(BULK처리)
                if (custListB != null && custListB.size() > 0) {
                    cntUpdate01 = commonDao.update("msCustAddrCleansingService.updateMsCustBulk", custListB);
                }
                //주소정제 결과 적용: 행정동코드정보(BULK처리)
                if (custListC != null && custListC.size() > 0) {
                    cntUpdate02 = commonDao.update("msCustAddrCleansingService.updateMsCustBulk", custListC);
                }

            } catch (UserHandleException e) {
                batchLogDto.setResultCode("-1");
                batchLogDto.setResultMsg("NAVER MAP API 인증오류입니다. CLIENT ID, KEY확인하세요.");
            } catch (Exception e) {
                batchLogDto.setResultCode("-1");
                batchLogDto.setResultMsg(e.toString());
            } finally {
                String procInfo = String.format("\r\n*위경도좌표* API CALL(%s) DB UPDATE(%s)", cntApiCall01, cntUpdate01)
                        + String.format("\r\n*행정동코드* API CALL(%s) DB UPDATE(%s)", cntApiCall02, cntUpdate02)
                        + String.format("\r\n*Exception: %s", batchLogDto.getResultMsg());
                logSystemOut(batchLogDto, "END", logOption[4], "700", "JAVA BATCH END"+procInfo);
            }

            return RepeatStatus.FINISHED;
        };
    }

    /*
     * @description : 지도Api URL호출 및 결과값 처리
     *              : pDivData (1:위경도(지번),2:위경도(도로명))
     */
    public void callMapApi(MsCustResDto pDto, String pDivData) {
        //SonarQube조치용도
        String itmStatus = "status";
        String itmMsg    = "message";
        String itmErr    = "error";

        pDto.setStatus(API_STATUS_FAIL); // 성공시에만 "OK"지정함

        String apiJobDiv = pDto.getApiJob(); //B:위경도API,C:행정동API
        String apiData   = null;

        if ("C".equals(apiJobDiv)) {
            apiData = pDto.getLongitude() + "," + pDto.getLatitude();	//행정동코드를 구하기 위한 좌표값(x,y)
        } else if ("1".equals(pDivData)) {
            apiData = pDto.getAddress1();	//위경도값을 구하기 위한 지번주소
        } else {
            apiData = pDto.getAddress3();	//위경도값을 구하기 위한 지번주소 도로명주소
        }

        try {
            StringBuilder urlBuilder = new StringBuilder(apiJobDiv.equals("B")?ContextUtil.getProperty("cf.naver.api.urlMapGc")	//URL:map-geocode
                    :ContextUtil.getProperty("cf.naver.api.urlMapRGc"));	//URL:map-reversegeocode

            if (apiJobDiv.equals("B")) { //위경도
                urlBuilder.append("?query=").append(apiData);

            } else if (apiJobDiv.equals("C")) { //행정동
                urlBuilder.append("?coords=").append(apiData);
                urlBuilder.append("&orders=admcode");
            }
            urlBuilder.append("&output=json"); //공통

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-NCP-APIGW-API-KEY-ID", ContextUtil.getProperty("cf.naver.api.id"));
            headers.set("X-NCP-APIGW-API-KEY"   , ContextUtil.getProperty("cf.naver.api.key"));

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    urlBuilder.toString(),
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            JsonNode rootNode  = objectMapper.readTree(response.getBody());

            if (response.getStatusCode() == HttpStatus.OK) {

                if (apiJobDiv.equals("B")) { //위경도
                    pDto.setStatus(rootNode.get(itmStatus).asText().toUpperCase());
                    JsonNode rtnList = rootNode.get("addresses"); //List

                    if (!rtnList.isEmpty()) {
                        //다수건 있더라도 첫번째만 사용함.
                        JsonNode addrMap = rtnList.get(0);
                        pDto.setX(addrMap.get("x").asText());
                        pDto.setY(addrMap.get("y").asText());
                        pDto.setStatus(API_STATUS_OK); // 성공시에만 "OK"지정함
                        pDto.setErrmsg("");
                    } else {
                        //비정상주소의 경우에도 status='OK'반환됨(예외처리 적용)
                        pDto.setStatus(API_STATUS_FAIL);
                        pDto.setErrcode("9901"); //사용자정의
                        pDto.setErrmsg("주소확인 필요!(좌표값 미반환)");
                    }

                } else { //행정동
                    pDto.setStatus(rootNode.get(itmStatus).get("name").asText().toUpperCase()); // ok => OK
                    JsonNode rtnList = rootNode.get("results"); //List

                    if (!rtnList.isEmpty()) {
                        //다수건 있더라도 첫번째만 사용함.
                        JsonNode addrMap = rtnList.get(0);
                        pDto.setAdmcd(addrMap.get("code").get("id").asText());
                        pDto.setStatus(API_STATUS_OK); // 성공시에만 "OK"지정함
                        pDto.setErrmsg("");
                    } else {
                        //비정상주소의 경우에도 status='OK'반환됨(예외처리 적용)
                        pDto.setStatus(API_STATUS_FAIL);
                        pDto.setErrcode("9901"); //사용자정의
                        pDto.setErrmsg(rootNode.get(itmStatus).get("name").asText() + " : " + rootNode.get(itmStatus).get(itmMsg).asText());
                    }
                }

            } else {
                pDto.setStatus(API_STATUS_FAIL);
                pDto.setErrcode("9991"); //사용자정의
                if (apiJobDiv.equals("B")) {
                    pDto.setErrmsg(rootNode.get(itmStatus).asText() + " : " + rootNode.get("errorMessage").asText());
                } else {
                    pDto.setErrmsg(rootNode.get(itmErr).get("errorCode").asText() + " : " + rootNode.get(itmErr).get(itmMsg).asText());
                }
            }
        } catch (HttpClientErrorException e) {
            String responseBody = e.getResponseBodyAsString();
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseBody);
                pDto.setErrcode(root.path(itmErr).path("errorCode").asText()); //200
                pDto.setErrmsg(root.path(itmErr).path(itmMsg).asText()); 	//Authentication Failed
            } catch (Exception parseException) {
                pDto.setErrcode("9998"); //사용자정의
                pDto.setErrmsg("Parsing Failed");
            }
        } catch (Exception e) {
            pDto.setErrcode("9999"); //사용자정의
            pDto.setErrmsg("ERROR:" + e.getMessage());
        } finally {
            // 처리결과 업데이트 MG_ADDR_CLEAN_RESULT( 주소정제 처리결과)
            commonDao.update("msCustAddrCleansingService.updateApiProcResult", pDto);
        }
    }

    /*
     * @description : System.out.println ==> BATCH LOG기록으로 전환
     */
    public void logSystemOut(BatchLogParamsDto reqDto, String jobStatus, String isPrint, String lineNo, String logMsg) {
        if (!StringUtil.isEmpty(reqDto.getJobExecutionId()) && isPrint.equals("Y")) {
            reqDto.setJobStatus(jobStatus);
            reqDto.setLineNo(lineNo);
            reqDto.setResultMsg(logMsg);
            try {
                commonDao.insert("batchCommonService.insertBatchLog", reqDto);
            } catch (Exception e) {
                log.debug(e.getMessage());
            } finally {
                reqDto.setResultMsg(""); //Init ResultMsg
            }
        }
    }
}