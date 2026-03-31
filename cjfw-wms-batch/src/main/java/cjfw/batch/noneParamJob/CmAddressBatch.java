package cjfw.batch.noneParamJob;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import cjfw.batch.common.BatchJobListener;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.ContextUtil;
import kr.go.ads.client.ADSReceiver;
import kr.go.ads.client.ADSUtils;
import kr.go.ads.client.ReceiveData;
import kr.go.ads.client.ReceiveDatas;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CmAddressBatch {

    private static final String BATCH_JOB_NAME = "cmAddressJob";

    private final CommonDao commonDao;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private BatchJobListener batchJobListener;

    @Bean
    public Job cmAddressJob() {
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
            // 배치수행로그
            BatchLogParamsDto batchLogDto = BatchLogParamsDto.builder()
                    .jobExecutionId(Long.toString(chunkContext.getStepContext().getStepExecution().getJobExecution().getId()))
                    .jobName(BATCH_JOB_NAME)
                    .jobDiv("JAVA")
                    .nodeLevel(0)
                    .jobStatus("START")
                    .command("")
                    .lineNo("90")
                    .resultCode("0")
                    .resultMsg("")
                    .build();

            try {
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);

                getAddrInfo();
            } catch (Exception e) {
                batchLogDto.setResultCode("-1");
                batchLogDto.setResultMsg(e.toString());
            } finally {
                batchLogDto.setJobStatus("END");
                batchLogDto.setLineNo("104");
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
            }

            return RepeatStatus.FINISHED;
        };
    }

    /**
     * @description : 행정안전부 변동된 주소 가져오기
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.25 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    public void getAddrInfo() {
        ADSReceiver ads = new ADSReceiver();						// ADSReceiver 객체 생성
        String app_key = ContextUtil.getProperty("cf.juso.appKey");	// 승인키
        String date_gb = "D";										// 날짜 구분
        String retry_in = "N";										// 재반영 요청 여부
        String cntc_cd = "100001";									// 자료 요청 구분 (100001: 도로명주소 한글)
        String req_date_from = "";									// 요청일자(From)
        String req_date_to = "";									// 요청일자(To)

        // 오늘 날짜 설정
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String strNowDate = simpleDateFormat.format(nowDate);
        req_date_from = strNowDate;
        req_date_to = strNowDate;

        // root 폴더 삭제 및 생성
        File uploadedDir = new File(ContextUtil.getProperty("cf.juso.filePath"));
        deleteRecursive(uploadedDir);
        if(!(uploadedDir.exists())) {
            uploadedDir.mkdirs();
        }

        // 일변동 자료를 저장할 장소를 설정합니다.
        ads.setFilePath(ContextUtil.getProperty("cf.juso.filePath"));
        ads.setCreateDateDirectory(ADSUtils.YYMMDD);

        try {
            // 변동자료 연계서비스 요청 및 응답데이터 확인
            ReceiveDatas receiveDatas = ads.receiveAddr(app_key, date_gb, cntc_cd, retry_in, req_date_from, req_date_to);

            /* --------------------------------- 응답 결과 확인 --------------------------------- */
            if(receiveDatas.getResult() != 0){
                if(receiveDatas.getResult() == -1){
                    // 서버 접속 실패 : 잠시후 재 시도 하시기 바랍니다.
                    log.info("서버 접속 실패");
                }
                // 서버 페이지 오류 사항 확인
                log.info("@@@@@@@@@@ Result code : {}", receiveDatas.getResult());
                log.info("@@@@@@@@@@ Response code : {}", receiveDatas.getResCode());
                log.info("@@@@@@@@@@ Response Msg : {}", receiveDatas.getResMsg());
                log.info("@@@@@@@@@@ Total count : {}", receiveDatas.getResTotalCnt());
                log.info("@@@@@@@@@@ Normal count : {}", receiveDatas.getResNormalCnt());
                log.info("@@@@@@@@@@ Retry count : {}", receiveDatas.getResRetryCnt());
                throw new UserHandleException("MSG.COM.ERR.001");
            }

            // 서버 응답 확인
            log.info("@@@@@@@@@@ Response code : {}", receiveDatas.getResCode());		// 응답코드
            log.info("@@@@@@@@@@ Response Msg : {}", receiveDatas.getResMsg());			// 응답메시지
            log.info("@@@@@@@@@@ Total count : {}", receiveDatas.getResTotalCnt());		// 전체 자료수
            log.info("@@@@@@@@@@ Normal count : {}", receiveDatas.getResNormalCnt());	// 변동 자료수
            log.info("@@@@@@@@@@ Retry count : {}", receiveDatas.getResRetryCnt());		// 재반영 자료수

            if(!"P0000".equals(receiveDatas.getResCode())){
                // 거절 응답
                throw new UserHandleException("MSG.COM.ERR.001");
            }
            /* --------------------------------- 응답 결과 완료  --------------------------------- */

            // 결과 데이터 정렬
            ArrayList result = receiveDatas.getReceiveDatas(ADSUtils.UPDATE_ASC);
            Iterator itr = result.iterator();
            while(itr.hasNext()){
                // 결과 데이터 건별 정보 확인
                ReceiveData receiveData = (ReceiveData)itr.next();

                log.info("@@@@@@@@@@ 응답코드 : {}", receiveData.getResCode());
                log.info("@@@@@@@@@@ 응답메시지(한글) : {}", receiveData.getResMsg());
                if(!"P0000".equals(receiveData.getResCode())){
                    // 해당 파일응답 에러. 특히 E1001 인경우, 해당 파일을 아직 생성하지 못한 응답으로 추후 재시도 필요.
                    log.info("해당파일에 대한 응답이 정상이 아니기에 재 요청 필요");
                    throw new UserHandleException("MSG.COM.ERR.001");
                }

                log.info("@@@@@@@@@@ 파일 절대경로 : {}", receiveData.getFilePath());
                readZipFile(receiveData.getFilePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserHandleException("MSG.COM.ERR.001");
        }
    }

    /**
     * @description : .ZIP 파일 안의 .txt 파일 DATA 읽어오기
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.25 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    public void readZipFile(String zipFilePath) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().endsWith(".TXT") && entry.getName().contains("TH_SGCO_RNADR_MST")) {
                    log.info("@@@@@@@@@@ 읽는 파일 : {}", entry.getName());
                    List<Map<String, Object>> resultList = readTxtFile(zis);
                    log.info("@@@@@@@@@@ 총 건수 : {}", resultList.size());

                    if (entry.getName().contains("Deletion")) {
                        // 삭제???
                    } else {
                        // 31:신규, 34:수정, 63:폐지
                        // 100건씩 끊어서 저장
                        int bulkCnt =0;
                        List<Map<String, Object>> bulkList = new ArrayList<Map<String, Object>>();
                        for (Map<String, Object> result : resultList) {
                            bulkCnt++;
                            bulkList.add(result);
                            if (bulkCnt % 100 == 0) {
                                int saveCnt = commonDao.insertQuartz("cmAdsReceiverService.insertAddress", bulkList);
                                log.info("@@@@@@@@@@ 저장 건수 : {}", saveCnt);
                                bulkList.clear();
                            }
                        }
                        if (bulkList.size() > 0) { // 나머지
                            int saveCnt = commonDao.insertQuartz("cmAdsReceiverService.insertAddress", bulkList);
                            log.info("@@@@@@@@@@ 저장 건수 : {}", saveCnt);
                        }
                    }
                }
                zis.closeEntry();
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new UserHandleException("MSG.COM.ERR.001");
        }
    }

    /**
     * @description : .txt 파일 안의 DATA 읽어오기
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.25 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    public List<Map<String, Object>> readTxtFile(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "EUC-KR"));
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        String line;

        while ((line = reader.readLine()) != null) {
            // 필드명 정의
            String[] keys = {
                    "ADR_MNG_NO", "BJDONG_CD", "CTP_KOR_NM", "SIG_KOR_NM", "EMD_KOR_NM", "LI_KOR_NM", "MOUNT_YN",
                    "BUNJI", "HO", "RN_CD", "RN", "BULD_SE_CD", "BULD_MNNM",
                    "BULD_SLNO", "HJDONG_CD", "HJDONG_NM", "ZIPCODE",
                    "RD_ADRES_OLD", "EFFECT_DE", "APT_BUL_GB", "MVMN_RES_CD", "BUL_REG_NM", "SIG_BUL_NM", "RMK"
            };

            // 문자열 분리
            String[] values = line.split("\\|", -1);

            // Map으로 변환
            Map<String, Object> resultMap = new LinkedHashMap<>(); // 순서 유지
            for (int i = 0; i < keys.length && i < values.length; i++) {
                resultMap.put(keys[i], values[i]);
            }

            resultList.add(resultMap);
        }

        return resultList;
    }

    // 재귀적으로 폴더 및 파일 삭제
    public static boolean deleteRecursive(File file) {
        if (!file.exists()) return true;

        // 폴더인 경우 하위 파일/폴더 삭제
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteRecursive(child);
                }
            }
        }

        // 파일 또는 빈 폴더 삭제
        return file.delete();
    }
}