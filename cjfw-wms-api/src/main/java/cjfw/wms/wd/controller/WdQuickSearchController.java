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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cjfw.core.exception.UserHandleException;
import cjfw.core.model.ApiResult;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.common.util.MaskingUtil;
import cjfw.wms.wd.dto.WdQuickResAPI02Dto;
import cjfw.wms.wd.dto.WdQuickSearchReqDto;
import cjfw.wms.wd.service.WdCommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 퀵배송조회 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "WdQuickSearchController API", description = "WdQuickSearchController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/quickSearch")
public class WdQuickSearchController {
	
	/** WD.공통.service */
	private final WdCommonService wdCommonService;	

	/**
	 * @description : 퀵배송조회 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "퀵배송조회 조회 List", description = "퀵배송조회 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<WdQuickSearchReqDto> getMasterList(@RequestBody WdQuickSearchReqDto paramDto) {
		WdQuickSearchReqDto resultDto = new WdQuickSearchReqDto();
		List<WdQuickResAPI02Dto> bodyDto = new ArrayList<>();
		
		// 파라미터 위변조 방지
		WdQuickSearchReqDto reqDto = ModelMapperUtil.map(paramDto, WdQuickSearchReqDto.class);
		
        /** 퀵접수번호 */
		String url      = ContextUtil.getProperty("cf.quick.url_list");  // URL
		String appkey   = ContextUtil.getProperty("cf.quick.appkey");    // appkey.공용
		String realOpen = ContextUtil.getProperty("cf.quick.realOpen");  // realOpen.공용
		String quickUserId = wdCommonService.getQuickUserid(""); // 인성과 연동된 ID 값(cjfreshway.stnlogis.com 로그인 가능 유무로 확인가능)
		log.info("▶Dto data->{}", MaskingUtil.maskLog(reqDto.toString()));
		log.info("▶url->{}",MaskingUtil.maskLog(url));
		log.info("▶realOpen->{}",MaskingUtil.maskLog(realOpen));
		log.info("▶ quickUserId : {}", quickUserId);
		
		//String url      = "https://cjfreshway.stnlogis.com/api/order_list.php"; // 오더등록 url과 상이함 
		//String appkey   = "c86fda0a22e404ad0d6cba5ea0a70741";
		
		
		// START.필수입력
		//if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getRespDept())))   {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"귀책부서[respDept]"})); }
		// END.필수입력		

		try {
			/*
			https://cjfreshway.stnlogis.com/api/order_list.php?
			   c_code=1914&
			   api_key=c86fda0a22e404ad0d6cba5ea0a70741&
			   api_idx=20&
			   user_id=10000_test&
			   limit=100&
			   page=1&
			   from_date=2025-12-01&
			   to_date=2025-12-12&
			   char_set=utf-8
		    */
			String serverUrl = url;
			HttpHeaders headers = new HttpHeaders();
			RestTemplate restTemplate = new RestTemplate();
			
			headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + "; charset=UTF-8");
	        headers.add("corp", "cj");
			HttpEntity entity = new HttpEntity(headers); // Header 용
		
			URI uri = UriComponentsBuilder
			        .fromHttpUrl(serverUrl)
					.queryParam("c_code"    ,"1914")	              // 고정값(1914)  
					.queryParam("api_key"   ,appkey)	              // appkey	  
					.queryParam("api_idx"   ,"20")		              // 고정값(20)
					.queryParam("user_id"   ,quickUserId)             // 인성과 연동된 ID 값(cjfreshway.stnlogis.com 로그인 가능 유무로 확인가능)
					.queryParam("limit"     ,reqDto.getLimit())       // 페이지당 출력갯수
					.queryParam("page"      ,reqDto.getCurrentPage()) // 페이지번호
					.queryParam("from_date" ,reqDto.getDt1())         // 시작일   
					.queryParam("to_date"   ,reqDto.getDt2())         // 종료일 
					.queryParam("char_set"  ,"utf-8")    
			        .build()
			        .encode()
			        .toUri();

			ResponseEntity<String> resultBody = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
			
			//log.info("▶resultBody->{}",MaskingUtil.maskLog(resultBody.toString()));
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(resultBody.getBody());
			
			// 1.page 헤더 정보
			JsonNode header = root.get(0);
			String code = header.path("code").asText();
			String msg  = header.path("msg").asText();
			log.info("▶ code       : {}", code);
			log.info("▶ message       : {}", msg);				
			
			// 2.page 정보
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
			
			/* 응답데이터 예시
			  {
		        "code": "1000",
		        "msg": "RESULT:OK"
			    },
			    {
			        "from_date": "2025-12-01",
			        "to_date": "2025-12-12",
			        "total_record": "4",
			        "total_page": "1",
			        "current_page": "1",
			        "display_article": "100",
			        "current_display_article": "4"
			    },
			    {
			        "serial_number": "1",
			        "order_state": "취소",
			        "order_date": "2025-12-12 11:16",
			        "customer_name": "홍길동1",
			    ),
			    {
			        "serial_number": "2",
			        "order_state": "취소",
			        "order_date": "2025-12-12 11:16",
			        "customer_name": "홍길동2",
			    ),
			  */
			
			for (int i = 2; i < root.size(); i++) {
			    JsonNode n = root.get(i);

			    WdQuickResAPI02Dto dto = new WdQuickResAPI02Dto();
			    wdCommonService.setResAPI02(n, dto); // 공통.응답데이터 set
			    bodyDto.add(dto);
			}
		} catch (Exception e) { // 체크.기타오류 처리여부(3/3)
			log.error("{}", e);
			throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"퀵주문접수(VSR)"}) + e.getMessage());
		} 
		
		resultDto.setResultList(bodyDto);
		return ApiResult.createResult(resultDto);
	}


}
