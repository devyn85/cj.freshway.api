package cjfw.batch.job;

import cjfw.batch.common.BatchJobListener;
import cjfw.batch.common.SetParamService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.dto.CmRoadAddrReqDto;
import cjfw.batch.dto.CmRoadAddrResDto;
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
 * @date : 2025.12.04
 * @description : CM_ROAD_ADDRESS(도로명주소) 행안부자료 적용후 행정동코드 누락건 처리 - WITH NAVER MAP API (위경도 및 행정동코드 현행화)  
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.04 김환수 (hwansoo.kim@cj.net) 생성 </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CmRoadAddrHjdongBatch {

    private final CommonDao commonDao;
    private final String BATCH_JOB_NAME = "cmRoadAddrHjdongJob";

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
    public Job cmRoadAddrHjdongJob() {
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

            Map<String, Object> jobParams = chunkContext.getStepContext().getJobParameters();
            BatchParamsUtil batchParam = setParamService.getObjectBatchParam(jobParams, batchLogDto.getJobExecutionId());

            //*확장*로그기록공통변수(공통 + {작업구분}^{COMMIT_CNT}^{FETCH_CNT}^{DELAY_TIME})
            //     : FETCH_CNT  [5]: SQL ROW FETCH COUNT
            //     : DELAY_TIME [6]: Y(100~300ms Random)|N
            String[] logOption = batchParam.getAVC_LOG_PARAMS().split("\\^"); //AVC_LOG_PARAMS 문자열 분리
            String fetchCnt    = logOption[5];
            String ynDelay     = logOption[6];

            CmRoadAddrReqDto reqDto = new CmRoadAddrReqDto();
            //reqDto.setCommtCnt(commitCnt)
            reqDto.setFetchCnt(fetchCnt);

            String addrNm   = null;
            long cntLoop    = 0; //LOOPING COUNT
            long cntApiCall = 0; //NAVER API CALL COUNT

            logSystemOut(batchLogDto, "START", logOption[4], "125", "JAVA BATCH START: "+batchParam.getAVC_LOG_PARAMS());

            ///////////////////////////////////////////////////////
            //Step.A: NAVER API 처리                             //
            //      : (1st) {시도명+시군구명+읍면동명}+리명+번지 //
            //      : (2nd) {시도명+시군구명+읍면동명}+리명      //
            //      : (3rd) {시도명+시군구명+읍면동명}           //
            ///////////////////////////////////////////////////////
            List<CmRoadAddrResDto> resList = commonDao.selectList("cmRoadAddrHjdongService.selectTgtList", reqDto);

            try {
                for (CmRoadAddrResDto resDto : resList) {
                    for (int i = 1; i <= 3; i++) { // 총 3회 시도
                        if (i==1) {			//(1st) {시도명+시군구명+읍면동명}+리명+번지
                            addrNm = resDto.getAddrNmBase()+resDto.getAddrNmLi()+resDto.getAddrNmBunji();
                        } else if (i==2) {	//(2nd) {시도명+시군구명+읍면동명}+리명
                            addrNm = resDto.getAddrNmBase()+resDto.getAddrNmLi();
                        } else {			//(3rd) {시도명+시군구명+읍면동명}
                            addrNm = resDto.getAddrNmBase();
                        }
                        resDto.setAddrNm(addrNm);

                        //(B)위경도좌표 처리
                        callMapApi(resDto, "B"); cntApiCall++;

                        if (StringUtils.isNotEmpty(resDto.getErrcode()) &&
                                resDto.getErrcode().equals(API_ERR_200)) {
                            throw new UserHandleException(API_ERR_200);  //Authentication Failed
                        }

                        //위경도정보 정상인 경우 행정동코드 처리
                        if (StringUtils.isNotEmpty(resDto.getStatus()) 		&&
                                resDto.getStatus().equals(API_STATUS_OK)   		&&
                                StringUtils.isNotEmpty(resDto.getLongitude())	&&
                                StringUtils.isNotEmpty(resDto.getLatitude())) {
                            //(C)행정동코드 처리
                            callMapApi(resDto, "C"); cntApiCall++;
                            if (true) break;
                        }
                    }

                    cntLoop++;

                    //동시접속에 의한 block예방용도
                    if (ynDelay.equals("Y")) { Thread.sleep(((int)(Math.random()*3)+1)*100); } // 100|200|300
                }

            } catch (UserHandleException e) {
                batchLogDto.setResultCode("-1");
                batchLogDto.setResultMsg("NAVER MAP API 인증오류입니다. CLIENT ID, KEY확인하세요.");
            } catch (Exception e) {
                batchLogDto.setResultCode("-1");
                batchLogDto.setResultMsg(e.toString());
            } finally {
                String procInfo = String.format("\r\n*도로명주소 행정동정보 주소정제* LOOING(%s) API CALL(%s)", cntLoop, cntApiCall)
                        + String.format("\r\n*Exception: %s", batchLogDto.getResultMsg());
                logSystemOut(batchLogDto, "INFO", logOption[4], "169", "Step.A: NAVER API 처리: "+procInfo);
            }

            ///////////////////////////////////////
            //Step.B: 행정동정보(코드,명) UPDATE //
            ///////////////////////////////////////
            if (resList.size() > 0) {
                commonDao.update("cmRoadAddrHjdongService.updateCmRoadAddrHjdong", resList);
            }

            logSystemOut(batchLogDto, "INFO", logOption[4], "177", "JAVA BATCH END");

            return RepeatStatus.FINISHED;
        };
    }

    /*
     * @description : 지도Api URL호출 및 결과값 처리
     *              : pDivApi (B:위경도좌표(map-geocode),C:행정동코드(map-reversegeocode))
     */
    public void callMapApi(CmRoadAddrResDto pDto, String pDivApi) {
        //SonarQube조치용도
        String itmStatus = "status";
        String itmMsg    = "message";
        String itmErr    = "error";

        pDto.setStatus(API_STATUS_FAIL); // 성공시에만 "OK"지정함

        try {
            String apiData = pDivApi.equals("B")?pDto.getAddrNm() 									//위경도값을 구하기 위한 지번주소 도로명주소
                    :pDto.getLongitude() + "," + pDto.getLatitude();	//행정동코드를 구하기 위한 좌표값(x,y)

            StringBuilder urlBuilder = new StringBuilder(pDivApi.equals("B")?ContextUtil.getProperty("cf.naver.api.urlMapGc")		//URL:map-geocode
                    :ContextUtil.getProperty("cf.naver.api.urlMapRGc"));	//URL:map-reversegeocode

            if (pDivApi.equals("B")) { //위경도
                urlBuilder.append("?query=").append(apiData);
            } else if (pDivApi.equals("C")) { //행정동
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

                if (pDivApi.equals("B")) { //위경도
                    pDto.setStatus(rootNode.get(itmStatus).asText().toUpperCase());
                    JsonNode rtnList = rootNode.get("addresses"); //List

                    if (!rtnList.isEmpty()) {
                        //다수건 있더라도 첫번째만 사용함.
                        JsonNode addrMap = rtnList.get(0);
                        pDto.setLongitude(addrMap.get("x").asText());
                        pDto.setLatitude(addrMap.get("y").asText());
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
                        pDto.setHjdongCd(addrMap.get("code").get("id").asText());
                        pDto.setHjdongNm(addrMap.get("region").get("area3").get("name").asText());
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
                if (pDivApi.equals("B")) {
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