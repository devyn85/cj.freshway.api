package cjfw.wms.webservice.utility;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import cjfw.core.utility.ContextUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2026.03.04 
 * @description : CJ 대한통운 서비스 유틸
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.03.04 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Slf4j
public class CJServiceUtil  {	
	
	/**
	 * @description : 대한통운 API 호출
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.03 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public static <T> T callCJService(Map<String, String> params, Class<T> responseType, String path) {
		String apiUrl = ContextUtil.getProperty("cf.cjlogistics.url") + path;

        HttpClient client = HttpClient.newHttpClient();
        
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(params);
        
        // Jackson ObjectMapper를 사용하여 Map을 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        
        String requestBody = null;
		try {
			requestBody = objectMapper.writeValueAsString(dataList);
		} catch (JsonProcessingException e) {
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
//	        	log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%% {}", response.body());
	            return objectMapper.readValue(response.body(), responseType);
	        }
		} catch (Exception e) {
			log.error("@@@@@ response 실패 : {}", e);
		}
		return null;
	}
	
}