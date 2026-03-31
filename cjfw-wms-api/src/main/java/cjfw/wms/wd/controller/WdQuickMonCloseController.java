package cjfw.wms.wd.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cjfw.core.exception.UserHandleException;
import cjfw.core.model.ApiResult;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.common.util.MaskingUtil;
import cjfw.wms.wd.dto.WdQuickMonCloseReqDto;
import cjfw.wms.wd.dto.WdQuickMonCloseResDto;
import cjfw.wms.wd.dto.WdQuickResAPI02Dto;
import cjfw.wms.wd.service.WdCommonService;
import cjfw.wms.wd.service.WdQuickMonCloseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 퀵배송상세 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "WdQuickMonCloseController API", description = "WdQuickMonCloseController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/quickMonClose")
public class WdQuickMonCloseController {
	
	/** 퀵배송상세 Service */
	private final WdQuickMonCloseService wdQuickMonCloseService;
	/** 공통.UserContext */
	private final UserContext userContext;	
	/** WD.공통.service */
	private final WdCommonService wdCommonService;

	/**
	 * @description : 퀵배송상세 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "퀵배송상세 조회 List", description = "퀵배송상세 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdQuickMonCloseResDto>> getMasterList(@RequestBody WdQuickMonCloseReqDto reqDto) {
		return ApiResult.createResult(wdQuickMonCloseService.getMasterList(reqDto));
	}
	
	/**
	 * @description :퀵배송상세 저장처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "퀵배송상세 저장처리", description = "퀵배송상세")
	@PostMapping(value="/v1.0/saveMasterList")
	public ApiResult<WdQuickMonCloseReqDto> saveMasterList(@RequestBody WdQuickMonCloseReqDto reqDto) throws Exception  {
		return ApiResult.createResult(wdQuickMonCloseService.saveMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}	
	
	/**
	 * @description :퀵배송상세 마감 처리	
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "퀵배송상세 저장처리", description = "퀵배송상세")
	@PostMapping(value="/v1.0/saveMasterCloseList")
	public ApiResult<WdQuickMonCloseReqDto> saveMasterCloseList(@RequestBody WdQuickMonCloseReqDto reqDto) throws Exception  {
		return ApiResult.createResult(wdQuickMonCloseService.saveMasterCloseList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
	
	/**
	 * @description : 퀵배송상세 저장 Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "퀵배송조회 조회 List", description = "퀵배송조회 저장 List")
	@PostMapping(value = "/v1.0/saveMasterReveiveList")
	public ApiResult<WdQuickMonCloseReqDto> saveMasterReveiveList(@RequestBody WdQuickMonCloseReqDto paramDto) throws JsonMappingException, JsonProcessingException {
		WdQuickMonCloseReqDto resultDto = new WdQuickMonCloseReqDto();
		int processCnt = 0; // 처리 건수
		String profiles = StringUtil.nvl(System.getProperty("spring.profiles.active","local"));
		String testYn = "N";
		if("prd".equals(System.getProperty("spring.profiles.active","local"))) { 
			testYn = "N"; 
		}
		
		log.info("▶ profiles : {}", profiles);
		log.info("▶ testYn : {}", testYn);
		
		// 1. 사용자ID 조회
		List<WdQuickMonCloseResDto> userIdList = wdCommonService.selectQuickUserIdList(paramDto);
		
		if(userIdList == null || userIdList.isEmpty()) {
			log.warn("▶ 사용자ID가 존재하지 않습니다. paramDto: {}", MaskingUtil.maskLog(paramDto.toString()));
			throw new UserHandleException("퀵사용자ID 존재하지 않습니다."); // 퀵사용자ID가 존재하지 않습니다.
		}
		
		// 2. 사용자ID별로 API 조회 및 저장 처리
		for (WdQuickMonCloseResDto dto : userIdList) {
			WdQuickMonCloseReqDto reqDto = ModelMapperUtil.map(paramDto, WdQuickMonCloseReqDto.class);
		    
			log.info("▶ dto.getQuickUserId() : {}", dto.getQuickUserId());
			
		    reqDto.setQuickUserId(dto.getQuickUserId()); // Set userId or other required fields
		    processCnt += processSaveMasterReceiveList(reqDto,profiles, resultDto); // API 조회 및 저장 처리, 처리 건수 누적
		}
		
		resultDto.setProcessCnt(processCnt); // 처리 건수 설정
		return ApiResult.createResult(resultDto);
	}	

	/**
	 * @description : 퀵배송상세 저장 구현 Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	 */
	private int processSaveMasterReceiveList(WdQuickMonCloseReqDto paramDto, String profiles,WdQuickMonCloseReqDto resultDto) throws JsonMappingException, JsonProcessingException {
	    List<WdQuickResAPI02Dto> saveList = new ArrayList<>();
	    WdQuickMonCloseReqDto reqDto = ModelMapperUtil.map(paramDto, WdQuickMonCloseReqDto.class);
	    int processCnt = 0; // 처리 건수

	    String url      = ContextUtil.getProperty("cf.quick.url_list");
	    String appkey   = ContextUtil.getProperty("cf.quick.appkey");
	    String realOpen = ContextUtil.getProperty("cf.quick.realOpen");
	    String quickUserId = wdCommonService.getQuickUserid(reqDto.getQuickUserId());

	    log.info("▶Dto data->{}", MaskingUtil.maskLog(reqDto.toString()));
	    log.info("▶url->{}",MaskingUtil.maskLog(url));
	    log.info("▶realOpen->{}",MaskingUtil.maskLog(realOpen));
	    log.info("▶quickUserId->{}",quickUserId);

	    String serverUrl = url;
	    HttpHeaders headers = new HttpHeaders();
	    RestTemplate restTemplate = new RestTemplate();

	    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + "; charset=UTF-8");
	    headers.add("corp", "cj");
	    HttpEntity entity = new HttpEntity(headers);
	    
	    /*
        ▶realOpen->N
        ▶quickUserId->10000_test
        ▶ code       : 1000
        ▶ message       : RESULT:OK
        ▶ totalRecord : 1033
        ▶ totalPage : 2
        ▶ currentPage : 1
        ▶ root.size() : 1002
        ▶ profiles : local
        */

	    URI uri = UriComponentsBuilder
	            .fromHttpUrl(serverUrl)
	            .queryParam("c_code", "1914")
	            .queryParam("api_key", appkey)
	            .queryParam("api_idx", "20")
	            .queryParam("user_id", quickUserId)
	            .queryParam("limit", reqDto.getLimit())
	            .queryParam("page", reqDto.getCurrentPage())
	            .queryParam("from_date", reqDto.getDt1())
	            .queryParam("to_date", reqDto.getDt2())
	            .queryParam("char_set", "utf-8")
	            .build()
	            .encode()
	            .toUri();

	    ResponseEntity<String> resultBody = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode root = mapper.readTree(resultBody.getBody());

	    JsonNode header = root.get(0);
	    String code = header.path("code").asText();
	    String msg  = header.path("msg").asText();
	    log.info("▶ code       : {}", code);
	    log.info("▶ message       : {}", msg);

	    BigDecimal totalRecord = BigDecimal.ZERO;
	    BigDecimal totalPage = BigDecimal.ZERO;
	    BigDecimal currentPage = BigDecimal.ZERO;
	    JsonNode page = root.get(1);
	    if(page != null) {
	        totalRecord = BigDecimal.valueOf(page.path("total_record").asLong());
	        totalPage = BigDecimal.valueOf(page.path("total_page").asLong());
	        currentPage = BigDecimal.valueOf(page.path("current_page").asLong());
	    }

	    log.info("▶ totalRecord : {}", totalRecord);
	    log.info("▶ totalPage : {}", totalPage);
	    log.info("▶ currentPage : {}", currentPage);

	    resultDto.setTotalRecord(totalRecord);
	    resultDto.setTotalPage(totalPage);
	    resultDto.setCurrentPage(currentPage);

	    if(!"1000".equals(code)) {
	        log.error("▶퀵접수 API 조회 시 중 오류가 발생하였습니다. resultBody: {}", resultBody.toString());
	        throw new UserHandleException(msg);
	    }

	    log.info("▶ root.size() : {}", root.size());
	    
	    log.info("▶ profiles : {}", profiles);
	    
	    for (int i = 2; i < root.size(); i++) {
	        JsonNode n = root.get(i);
	        WdQuickResAPI02Dto dto = ModelMapperUtil.map(reqDto, userContext, WdQuickResAPI02Dto.class);
	        wdCommonService.setResAPI02(n, dto);
	        
	        if("Y".equals(realOpen)) {
	        	log.info("▶ realOpen 환경");
	        	saveList.add(dto);
	        } else {
	        	// 운영이 아니면 프로파일별로 데이터 저장 여부 결정
		        if ("local".equals(profiles) || "dev".equals(profiles)) {
			        log.info("▶ 1.로컬이나 개발 환경");
			        saveList.add(dto);
		        } else if ("qa".equals(profiles)) {
			        log.info("▶ 2.qa 환경");
			        saveList.add(dto);
		        } else if ("prd".equals(profiles)) {
		        	log.info("▶ 3.prd 환경");
		        	saveList.add(dto);
		        }
	        }
	        
	    }
	    
	    log.info("▶ saveList.size() : {}", saveList.size());
	    reqDto.setSaveList01(saveList);
	    
	    processCnt = root.size() - 2; // 실제 데이터 건수 (header, page 제외)

	    //reqDto.setSaveList01(saveList);
	    wdQuickMonCloseService.mergeQuickList(reqDto);

	    return processCnt;
	}
	
}
