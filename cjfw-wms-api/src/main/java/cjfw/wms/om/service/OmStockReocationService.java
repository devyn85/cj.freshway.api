package cjfw.wms.om.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.opencsv.bean.CsvToBeanBuilder;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cb.dto.CbNoticeReqDto;
import cjfw.wms.cb.service.CbNoticeService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.ms.dto.MsCustDeliveryInfoResDto;
import cjfw.wms.om.dto.OmStockReocationCapaResDto;
import cjfw.wms.om.dto.OmStockReocationCompareResDto;
import cjfw.wms.om.dto.OmStockReocationPltResDto;
import cjfw.wms.om.dto.OmStockReocationReqDto;
import cjfw.wms.om.dto.OmStockReocationResDto;
import cjfw.wms.om.dto.OmStockReocationWeightResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.12.22 
 * @description : 주문 > 주믄등록 > 재고재배치조회 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.22 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmStockReocationService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omStockReocationService.";
	private transient static final String PAKAGE_NAME = "SPOM_AUTOORD";
	
	private final CbNoticeService cbNoticeService;
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 재고재배치조회 plt 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.22 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public OmStockReocationResDto getDataList() {
		OmStockReocationResDto result = new OmStockReocationResDto();
		List<OmStockReocationPltResDto> pltResult = commonDao.selectList(SERVICEID_PREFIX + "getPltList");
		List<OmStockReocationCapaResDto> capaResult = commonDao.selectList(SERVICEID_PREFIX + "getCapaList");
		List<OmStockReocationWeightResDto> weightResult = commonDao.selectList(SERVICEID_PREFIX + "getWeightList");
		
		if(pltResult.size()>0) {
			result.setPltList(pltResult);
		}
		if(capaResult.size()>0) {
			result.setCapaList(capaResult);
		}		
		if(weightResult.size()>0) {
			result.setWeightList(weightResult);
		}
		return result;
	}	
	
	/**
	 * @description : AI팀 CSV 파일 저장 및 response
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.22 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public boolean startOptimization(OmStockReocationReqDto dto) {
		// call ai api
		// 호출 URL
//    	String apiUrl = "https://j315sb4xxc.execute-api.ap-northeast-2.amazonaws.com/prd/start-job"; // 기존 내역
    	String apiUrl = "https://scm-capa-wms-prd.ifresh.co.kr/start-job";  //수정내역
//    	String apiUrl = "https://436zkal1cg.execute-api.ap-northeast-2.amazonaws.com/prd/start-job";
//	        Path savePath = Path.of(ContextUtil.getProperty("cf.file.uploadPath") + "/ai" + "/20251219.csv"); // 저장 경로
        
    	log.info("dto ==========" + dto.toString());
        // 빌더 패턴 사용 시 (추천)
        ObjectMapper objectMapper = JsonMapper.builder()
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE) // Camel -> Snake 자동 변환
            .build();
    	log.info("objectMapper ==========" + objectMapper);

        // 또는 일반 인스턴스 사용 시
        // objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        String jsonRequest = null;
		try {
			jsonRequest = objectMapper.writeValueAsString(dto.getAiDto());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        log.info("jsonRequest ==========" + jsonRequest.toString());
        HttpClient client = HttpClient.newBuilder()
        	    .connectTimeout(Duration.ofSeconds(10)) // 연결 타임아웃
        	    .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .timeout(Duration.ofSeconds(30)) // 응답 타임아웃
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .header("Content-Type", "application/json")
                .build();
        log.info("request ==========" + request.toString());
//        HttpResponse<InputStream> response;
        HttpResponse<String> response;
		try {
//			response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			log.info("response ==========" + response.toString());
			log.info("response body ==========" + response.body());
			log.info("response statusCode==========" + response.statusCode());
			// 상태 코드 체크
	        return response.statusCode() >= 200 && response.statusCode() < 300;
	        
		} catch (Exception e) {
			log.error("@@@@@ AI CSV 파일 다운로드 실패 : {}", e.toString());
			return false;
		}    	
	}
	
	/**
	 * @description : 상세정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.22 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmStockReocationResDto> getDetailList() {		    	
		List<OmStockReocationResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getDetailList");
		
		return result;
	}
	
	/**
	 * @description : 현재고 배차안 asis tobe 상품 비교
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmStockReocationCompareResDto> getSkuCompareList(OmStockReocationReqDto dto) {
		log.info("CompareList dto chekc ===" + dto.toString());
		List<OmStockReocationCompareResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getSkuCompareList", dto);
		
		return result;
	}
	
	/**
	 * @description : AI팀 CSV 파일 저장 및 response 콜백 수신부
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.22 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public void optimization(Map<String, Object> payload) {
		
		ObjectMapper mapper = new ObjectMapper();
		boolean hasData = false;
		boolean errorOccurred = false;
		
//		if (payload == null) {
//			log.error("payload is null");
//			return;
//		}
		try {
			log.info("AI 분석 결과 payload" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 1. 페이로드에서 정보 추출
		Map<String, Object> files = (Map<String, Object>) payload.get("files");
		String jobId = (String) payload.get("job_id");
		if (files == null || files.get("csv_final") == null) {
		    log.error("csv_final not found in payload");
		    CbNoticeReqDto cbNoticeReqDto = new CbNoticeReqDto();
		    cbNoticeReqDto.setBrdTit("AI재고재배치 완료");
		    cbNoticeReqDto.setRcvcustType("R");
		    cbNoticeReqDto.setRecvGroupId("2002002");
		    cbNoticeReqDto.setBrdCntt("재배치 결과 파일이 존재하지 않습니다.");

		    cbNoticeService.saveNotice(cbNoticeReqDto);
		    return;
		}
//        Map<String, Object> files = (Map<String, Object>) payload.get("files");
        String finalResultUrl = (String) files.get("csv_final"); // 최종 결과 URL 추출

        log.info("AI 분석 결과 수신 시작 - JobID: {"+jobId+"}, URL: {"+finalResultUrl+"}" );

        try {
            // 2. S3 URL에서 CSV 읽기 및 DTO 변환
            List<OmStockReocationResDto> resultList = parseCsvFromS3(finalResultUrl);
            log.info("AI 분석 결과 csv 변환 resultlist size =="+ resultList.size() );
            if (resultList != null && !resultList.isEmpty()) {
            	hasData = true;
                // 3. 각 DTO에 jobId(reqNo) 세팅 및 DB 저장
                for (OmStockReocationResDto resDto : resultList) {
                    resDto.setReqNo(jobId); // 넘겨받은 job_id를 reqNo로 사용
                }
                final int LIST_SIZE = 500;
                int totalCount = resultList.size();

                for (int i = 0; i < totalCount; i += LIST_SIZE) {
                    int end = Math.min(i + LIST_SIZE, totalCount);
                    List<OmStockReocationResDto> insertList = resultList.subList(i, end);

                    // insert (foreach + UNION ALL)
                    commonDao.insert(SERVICEID_PREFIX + "insertDetailList", insertList);
                }
            }
        } catch (Exception e) {
            log.error("@@@@@ 콜백 처리 중 오류 발생", e);
            errorOccurred = true;
            // omStockMapper.updateJobStatus(jobId, "ERROR");
        } finally {

        	log.info("cbNoticeService 호출 알람설정 " );
            CbNoticeReqDto cbNoticeReqDto = new CbNoticeReqDto();
            cbNoticeReqDto.setBrdTit("AI재고재배치 완료");		// 제목
            cbNoticeReqDto.setRcvcustType("R");				// 수신처유형 (R:수신 그룹ID, U:수신 사용자ID)
            cbNoticeReqDto.setRecvGroupId("2002002");		// 수신그룹ID (/cm/cmReceiveGroup 메뉴의 수신그룹ID) (ex. "1,2,501")

            if (errorOccurred) {
                cbNoticeReqDto.setBrdCntt("AI재고재배치 처리 중 오류가 발생했습니다.");		// 내용
            } else if (!hasData) {
                cbNoticeReqDto.setBrdCntt("재배치할 건이 없습니다");
            } else {
                cbNoticeReqDto.setBrdCntt("AI재고재배치 작업이 완료되었습니다.");
            }
            cbNoticeService.saveNotice(cbNoticeReqDto);
        }
		
	}
	
	private List<OmStockReocationResDto> parseCsvFromS3(String s3Url) {
		try {
			log.info("parseCsvFromS3 url =="+ s3Url);
            URL url = new URL(s3Url);
            try (InputStream is = url.openStream()) {
                // 스트림 전체를 문자열로 읽어 CSV 내용을 먼저 확인
                String csvContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                log.info("CSV 내용:\n{}", csvContent); // CSV 데이터 전체 로그

                // StringReader로 CsvToBeanBuilder에 전달
                try (StringReader reader = new StringReader(csvContent)) {
                    List<OmStockReocationResDto> list = new CsvToBeanBuilder<OmStockReocationResDto>(reader)
                            .withType(OmStockReocationResDto.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build()
                            .parse();
                    log.info("DTO 리스트 = {}", list);
                    return list == null ? Collections.emptyList() : list;
                }
            }
        } catch (Exception e) {
        	// 파일이 0byte거나 내용이 없어 에러가 발생하면 로그만 찍고 빈 리스트 반환
            log.warn("CSV 파싱 실패 또는 빈 파일 (0byte 예상): {}", e);
            return Collections.emptyList();
        }
    }
		
}
