package cjfw.wms.st.service;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.st.dto.StStockForCJLReqDto;
import cjfw.wms.st.dto.StStockForCJLResDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.09.12 
 * @description : 저장품재고조회(CJ대한통운제공) Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.12    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StStockForCJLService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stStockForCJLService.";	
		
	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	@Data
	private static class DataContainer {
		@JsonProperty("DATA_CNT")
		private int dataCnt;
		@JsonProperty("DATA_LIST")
		private List<StStockForCJLResDto> dataList;
	}
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class ApiResponse {
	 private List<DataContainer> data;
	}
	
	/**
	 * @description : 저장품재고조회(CJ대한통운제공) 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	public List<StStockForCJLResDto> getMasterList(StStockForCJLReqDto dto) {
		
		List<StStockForCJLResDto> result = new ArrayList<StStockForCJLResDto>();
		
		String apiUrl = ContextUtil.getProperty("cf.cjlogistics.url");

        HttpClient client = HttpClient.newHttpClient();
        
        Map<String, String> data = new HashMap<>();
        data.put("ID", dto.getId()); // "FX"
        data.put("TR_NAME", dto.getTrName());
        data.put("PL", dto.getPl());
        data.put("SL", dto.getSl());
        data.put("SKU", dto.getSku());
        
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(data);
        
        // Jackson ObjectMapper를 사용하여 Map을 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        
        String requestBody = null;
		try {
			requestBody = objectMapper.writeValueAsString(dataList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			log.error("@@@@@ requestBody 실패 : {}", e);
		}

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			// 상태 코드 체크
	        if (response.statusCode() == 200) {
	        	// DTO 매핑
	        	objectMapper = JsonMapper.builder()
	        		    .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
	        		    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
	        		    .build();
	        	// JSON 파싱
	            ApiResponse apiRes = objectMapper.readValue(response.body(), ApiResponse.class);
	            

	            if (apiRes != null && apiRes.getData() != null && !apiRes.getData().isEmpty()) {
	                // DATA_LIST 추출
	                result = apiRes.getData().get(0).getDataList();
	                // SKU를 담을 리스트
	                List<String> skuList = new ArrayList<>();
	                // 유통기한으로 출고기한 set 포멧 변환
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	                
	                // 데이터 정제 (선택 사항: 문자열 공백 제거)
	                for (StStockForCJLResDto res : result) {	                    
	                	res.trimFields(); 
	                	
	                	if (res.getSku() != null) {
	                        skuList.add(res.getSku());
	                    }
	                	// 출고기한 set
	                	if (res.getLot4() != null && !res.getLot4().isEmpty()) {
	                        LocalDateTime expireDate = LocalDateTime.parse(res.getLot4(), formatter);
	                        LocalDateTime tareDate = expireDate.minusDays(7);

	                        // 유통기한과 동일한 포맷으로 세팅
	                        res.setTare(tareDate.format(formatter));
	                    }
	                }
	                
	                if(!skuList.isEmpty()) {
	                	
	                	List<Map<String, Object>> dbResult = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", skuList);
	                	// 조회를 빠르게 하기 위해 Map으로 변환
	                    Map<String, String> nameMap = new HashMap<>();
	                    for (Map<String, Object> row : dbResult) {
	                        nameMap.put(String.valueOf(row.get("SKU")), String.valueOf(row.get("SKUNAME")));	                     
	                    }

	                    for (StStockForCJLResDto res : result) {
	                        res.setSkuname(nameMap.get(res.getSku()));
	                    }
	                }
	                
	            }
	        }
		} catch (Exception e) {
			log.error("@@@@@ response 실패 : {}", e);
		}
		
		return result;
	}
	
		
}




