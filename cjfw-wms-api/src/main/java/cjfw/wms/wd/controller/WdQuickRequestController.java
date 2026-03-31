package cjfw.wms.wd.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cjfw.core.exception.UserHandleException;
import cjfw.core.model.ApiResult;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.common.util.MaskingUtil;
import cjfw.wms.wd.dto.WdQuickRequestCenterResDto;
import cjfw.wms.wd.dto.WdQuickRequestReqDto;
import cjfw.wms.wd.dto.WdQuickRequestResDto;
import cjfw.wms.wd.dto.WdQuickRequestResVOCDto;
import cjfw.wms.wd.dto.WdQuickResAPI01Dto;
import cjfw.wms.wd.service.WdCommonService;
import cjfw.wms.wd.service.WdQuickRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 퀵접수(VSR)및처리 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "WdQuickRequestController API", description = "WdQuickRequestController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/quickRequest")
public class WdQuickRequestController {
	
	/** 퀵접수(VSR)및처리 Service */
	private final WdQuickRequestService wdQuickRequestService;
	/** WD.공통.service */
	private final WdCommonService wdCommonService;
	
	/**
	 * @description : 퀵접수(VSR)및처리 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "퀵접수(VSR)및처리 조회 List", description = "VOC 조회")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<WdQuickRequestResDto>> getTab1MasterList(@RequestBody WdQuickRequestReqDto reqDto) {
		return ApiResult.createResult(wdQuickRequestService.getTab1MasterList(reqDto));
	}
	
	
	/** @throws Exception 
	 * @description :퀵접수(VSR)및처리 접수처리 Method - post->@RequestBody
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "퀵접수(VSR)및처리 접수처리", description = "VOC 센터접수처리")
	@PostMapping(value="/v1.0/saveMasterList01")
	public ApiResult<WdQuickRequestReqDto> saveMasterList01(@RequestBody WdQuickRequestReqDto reqDto) throws Exception  {
		return ApiResult.createResult(wdQuickRequestService.saveMasterList01(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
	
	/** 
	 * @description :퀵접수(VSR)및처리 센터저장처리 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "퀵접수(VSR)및처리 센터저장처리", description = "퀵접수(VSR)및처리 센터저장처리")
	@PostMapping(value="/v1.0/saveMasterListCenterRecept01")
	public ApiResult<WdQuickRequestReqDto> saveMasterListCenterRecept01(@RequestBody WdQuickRequestReqDto reqDto) throws Exception  {
		return ApiResult.createResult(wdQuickRequestService.saveMasterListCenterRecept01(reqDto)); // 저장되었습니다.
	}		
		
	
	/**
	 * @description : 센터리스트 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "센터 조회 List", description = "센터 조회 List")
	@GetMapping(value = "/v1.0/getCenterList")
	public ApiResult<List<WdQuickRequestCenterResDto>> getCenterList(WdQuickRequestReqDto reqDto) {
		return ApiResult.createResult(wdCommonService.getCenterList(reqDto));
	}	
	
	/**
	 * @description : 퀵접수센터리스트 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "퀵접수센터 조회 List", description = "퀵접수센터 조회 List")
	@GetMapping(value = "/v1.0/selectQuickCenterList")
	public ApiResult<List<WdQuickRequestCenterResDto>> selectQuickCenterList(WdQuickRequestReqDto reqDto) {
		return ApiResult.createResult(wdCommonService.selectQuickCenterList(reqDto));
	}	
	

	
	
	/**
	 * @description : 퀵접수(VSR)및처리 조회 - 퀵주문접수 List Method - post->@RequestBody
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "퀵접수(VSR)및처리 조회 - 퀵주문접수 List", description = "센터접수 - 퀵주문접수List")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<WdQuickRequestResVOCDto>> getTab2MasterList(@RequestBody WdQuickRequestReqDto reqDto) {
		return ApiResult.createResult(wdQuickRequestService.getTab2MasterList(reqDto));
	}
	
	
	/**
	 * @description : 퀵접수(VSR)및처리 퀵주문접수 상세 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "퀵접수(VSR)및처리 퀵주문접수 상세 조회 List", description = "센터접수 상세 조회 List")
	@GetMapping(value = "/v1.0/getTab2DetailList01")
	public ApiResult<List<WdQuickRequestResVOCDto>> getTab2DetailList01(@Valid WdQuickRequestReqDto reqDto) {
		return ApiResult.createResult(wdQuickRequestService.getTab2DetailList01(reqDto));
	}	
	
	/*************************************************2번째 Tab*****************************************/
	/** 
	 * @description :퀵접수(VSR)및처리 집하지처리 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "퀵접수(VSR)및처리 센터접수처리", description = "퀵접수(VSR)및처리 센터접수처리")
	@PostMapping(value="/v1.0/saveMasterListCenterRecept02")
	public ApiResult<WdQuickRequestReqDto> saveMasterListCenterRecept02(@RequestBody WdQuickRequestReqDto reqDto) throws Exception  {
		return ApiResult.createResult(wdQuickRequestService.saveMasterListCenterRecept02(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
	
	
	/**
	 * @description :퀵접수(VSR)및처리 집하지처리 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "퀵접수(VSR)및처리 집하지처리", description = "퀵접수(VSR)및처리 집하지처리")
	@PostMapping(value="/v1.0/saveDetailListDelivery01")
	public ApiResult<WdQuickRequestReqDto> saveDetailListDelivery01(@RequestBody WdQuickRequestReqDto reqDto) throws Exception  {
		return ApiResult.createResult(wdQuickRequestService.saveDetailListDelivery01(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
	
	
	/**
	 * @description :퀵접수(VSR)및처리 도착지 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "퀵접수(VSR)및처리 도착지 처리", description = "퀵접수(VSR)및처리 도착지 처리")
	@PostMapping(value="/v1.0/saveDetailListDestination01")
	public ApiResult<WdQuickRequestReqDto> saveDetailListDestination01(@RequestBody WdQuickRequestReqDto reqDto) throws Exception  {
		return ApiResult.createResult(wdQuickRequestService.saveDetailListDestination01(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}	
	
	/**
	 * @description :퀵접수(VSR)및처리 퀵접수 API 처리 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "퀵접수(VSR)및처리 퀵접수 API 처리", description = "퀵접수(VSR)및처리 퀵접수 API 처리")
	@PostMapping(value="/v1.0/getOrderRequest01")
	public ApiResult<WdQuickRequestReqDto> getOrderRequest01(@RequestBody WdQuickRequestReqDto reqDto) throws Exception  {
		WdQuickRequestReqDto resultDto = new WdQuickRequestReqDto();
        int iCnt = 0; // 등록 건수
        int uCnt = 0; // 수정 건수
        int dCnt = 0; // 삭제 건수
        /** 퀵접수번호 */
        String serialNumber = "";
		//String testPrifix = "[★★★TEST데이터★★★]"; // 개발 및 테스트 시 테스트데이터 프리픽스면 api전달시 메모앞에 붙이면 접수하지 않음
		String url      = ContextUtil.getProperty("cf.quick.url_reg");   // URL
		String appkey   = ContextUtil.getProperty("cf.quick.appkey");    // appkey.공용
		//String realOpen = ContextUtil.getProperty("cf.quick.realOpen");  // realOpen.공용
		
		
		// START.필수입력
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getRespDept())))   {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"귀책부서[respDept]"})); }
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getRespReason()))) {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"귀책사유[respReason]"})); }
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getRespEmp())))    {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"귀책담당자[respEmp]"})); }
		// END.필수입력		
		
		// 운영기이고 quick api오픈을 했다면 테스트데이터 프리픽스 제거
		//if("prd".equals(System.getProperty("spring.profiles.active","local")) && "Y".equals(realOpen)) { 
		//	testPrifix = ""; 
		//}
		
		List<WdQuickRequestResDto> saveList = reqDto.getSaveList01();
		WdQuickRequestResDto dto = saveList.get(0);
		WdQuickResAPI01Dto apiDto = (WdQuickResAPI01Dto) wdQuickRequestService.getOrderRequest01(dto).get(0);
		apiDto.setOEtc7(StringUtil.nvl(System.getProperty("spring.profiles.active","local"))); // 서버정보	
		serialNumber = "";
		
		// START.필수입력
		if ("".equals(cjfw.core.utility.StringUtil.nvl(apiDto.getSStart())))     {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"출발지 상호ㆍ이름[sStart]"})); }
		if ("".equals(cjfw.core.utility.StringUtil.nvl(apiDto.getDestTelno())))  {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"도착지 전화번호[destTelno]"})); }
		// END.필수입력
		
		try {
			final String serverUrl = url+"?c_code=1914&api_key="+appkey;
			
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
			params.add("api_idx",        "20");						 // 고정값
			params.add("user_id",        wdCommonService.getQuickUserid(apiDto.getUserId())  );        // 인성과 연동된 ID 값(cjfreshway.stnlogis.com 로그인 가능 유무로 확인가능)
			params.add("c_name",         apiDto.getCName());         // 접수자 이름ㆍ상호
			params.add("c_mobile",       apiDto.getCMobile());       // 접수자 연락처
			params.add("c_dept_name",    apiDto.getCDeptName());     // 접수자 부서명
			params.add("c_charge_name",  apiDto.getCChargeName());   // 접수자 담당
			params.add("s_start",        apiDto.getSStart());        // 출발지 상호ㆍ이름
			params.add("start_telno",    apiDto.getStartTelno());    // 출발지 전화번호
			params.add("dept_name",      apiDto.getDeptName());      // 출발지 부서명
			params.add("charge_name",    apiDto.getChargeName());    // 출발지 담당
			params.add("start_sido",     apiDto.getStartSido());     // 출발지 시ㆍ도
			params.add("start_gugun",    apiDto.getStartGugun());    // 출발지 구ㆍ군
			params.add("start_dong",     apiDto.getStartDong());     // 출발지 동명
			params.add("start_ri",       apiDto.getStartRi());       // 출발지 지번(리)
			params.add("start_location", apiDto.getStartLocation()); // 출발지 상세주소
			params.add("s_dest",         apiDto.getSDest());         // 도착지 상호ㆍ이름
			params.add("dest_telno",     apiDto.getDestTelno());     // 도착지 전화번호
			params.add("dest_dept",      apiDto.getDestDept());      // 도착지 부서명
			params.add("dest_charge",    apiDto.getDestCharge());    // 도착지 담당
			params.add("dest_sido",      apiDto.getDestSido());      // 도착지 시ㆍ도
			params.add("dest_gugun",     apiDto.getDestGugun());     // 도착지 구ㆍ군
			params.add("dest_dong",      apiDto.getDestDong());      // 도착지 동명
			params.add("dest_ri",        apiDto.getDestRi());        // 도착지 지번(리)
			params.add("dest_location",  apiDto.getDestLocation());  // 도착지 상세주소
			params.add("kind",           apiDto.getKind());          // 배송수단(1:오토, 2:다마스, 3:트럭, 4:밴, 5:라보, 6:지하철, 7:플렉스)
			params.add("pay_gbn",        apiDto.getPayGbn());        // 지급방법(1:선불, 2:착불, 3:신용, 4:송금, 5:수금)
			params.add("doc",            apiDto.getDoc());           // 배송방법(1:편도, 3:왕복, 5:경유)
			params.add("sfast",          apiDto.getSfast());         // 배송선택(1:일반, 3:급송, 5:조조, 7:야간, 8:할증, 9:과적, 0:택배, A:심야, B:휴일, C:납품, D:대기, F:눈비, 4:독차, 6:혼적, G:할인, M:마일, H:우편, I:행랑, J:해외, K:신문, Q:퀵, N:보관, O:혹한, P:상하차, R:명절)
			params.add("item_type",      apiDto.getItemType());      // 물품종류(1:서류봉투, 2:소박스, 3:중박스, 4:대박스)
			//params.add("memo",           testPrifix + apiDto.getMemo()); // 전달내용
			params.add("memo",           apiDto.getMemo()); 		 // 전달내용
			params.add("sms_telno",      apiDto.getSmsTelno());      // SMS로 전달 받을 핸드폰번호
			params.add("use_check",      apiDto.getUseCheck());      // 예약시간 사용여부(체크시:3)
			params.add("pickup_date",    apiDto.getPickupDate());    // 예약일(오늘 기준 최대 한달까지 선택가능)
			params.add("pick_hour",      apiDto.getPickHour());      // 예약일(시간단위)
			params.add("pick_min",       apiDto.getPickMin());       // 예약일(분단위)
			params.add("pick_sec",       apiDto.getPickSec());       // 예약일(초단위)
			params.add("car_kind",       apiDto.getCarKind());       // 차종구분 코드(01: 플축카고, 11: 리프트카고, 12: 플러스리, 42: 플축리, 02: 윙바디, 03: 플러스윙, 04: 축윙, 05: 플축윙, 14: 리프트윙, 16: 플러스윙리, 17: 플축윙리, 06: 탑, 08: 리프트탑, 07: 호루, 50: 리프트호루, 47: 자바라, 51: 리프트자바라, 18: 냉동탑, 19: 냉장탑, 21: 냉동윙, 22: 냉장윙, 23: 냉동탑리, 24: 냉장탑리, 25: 냉동플축윙, 26: 냉장플축윙, 27: 냉동플축리, 28: 냉장플축리, 29: 평카, 30: 로브이, 31: 츄레라, 34: 로베드, 32: 사다리, 33: 초장축)
			params.add("o_etc1",         apiDto.getOEtc1());         // 여분필드
			params.add("o_etc2",         apiDto.getOEtc2());         // 여분필드
			params.add("o_etc3",         apiDto.getOEtc3());         // 여분필드
			params.add("o_etc4",         apiDto.getOEtc4());         // 여분필드
			params.add("o_etc5",         apiDto.getOEtc5());         // 여분필드
			params.add("o_etc6",         apiDto.getOEtc6());         // 여분필드
			params.add("o_etc7",         apiDto.getOEtc7());         // 여분필드
			params.add("o_etc8",         apiDto.getOEtc8());         // 여분필드
			params.add("o_etc9",         apiDto.getOEtc9());         // 여분필드
			params.add("o_etc10",        apiDto.getOEtc10());        // 여분필드
			
			if("local".equals(System.getProperty("spring.profiles.active","local"))) { 
				log.info("▶params->{}",MaskingUtil.maskLog(params.toString()));
			}
			
			HttpHeaders headers = new HttpHeaders();
			RestTemplate restTemplate = new RestTemplate();
			
			headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + "; charset=UTF-8");
	        headers.add("corp", "cj");
			//HttpEntity entity = new HttpEntity(headers);
            //ResponseEntity<String> resultBody = restTemplate.exchange(new URI(serverUrl), HttpMethod.GET, entity, String.class);
            @SuppressWarnings("rawtypes")
			ResponseEntity<Map> resultBody = restTemplate.postForEntity(serverUrl, params, Map.class);
			@SuppressWarnings("unchecked")
			@Nonnull
			Map<String, Object> apiResultBody = resultBody.getBody();	
			
			boolean success = false;
			String message = "";
			if(apiResultBody != null) {
				Map body = resultBody.getBody();
				if (body != null && body.get("data") instanceof Map) {
				    Map data = (Map) body.get("data");
				    serialNumber = (String) data.get("serial_number");
				}					

				
				success  = (boolean) apiResultBody.get("success");
				message  = cjfw.core.utility.StringUtil.nvl((String) apiResultBody.get("message"));
			}
			log.info("▶▶▶ statusCode    : {}", resultBody.getStatusCode());
			log.info("▶▶▶ resultBody    : {}", resultBody.toString());
			log.info("▶▶▶ success       : {}", success);
			log.info("▶▶▶ message       : {}", message);
			log.info("▶▶▶ serial_number : {}", serialNumber);
			
			if(success) {
				// 체크.퀵접수번호 존재여부(1/3)
				if ("".equals(serialNumber)) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"serialNumber"} ) ); // 해당 정보가 없어 처리할 수 없습니다.-{0}
				} 	
				
				dto.setQuickDocno(serialNumber); // 퀵접수번호
				uCnt += wdQuickRequestService.updateOrderRequest01(dto); // serial_number 업데이트 처리
				
				// 체크.퀵접수번호 UPDATE 처리여부(2/3)
				if ((iCnt + uCnt + dCnt) < 1) {
					throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
				} 			
			} else {
                log.error("▶퀵접수 API처리 중 오류가 발생하였습니다. resultBody: {}", resultBody.toString());
                throw new UserHandleException(message);
			}
		} catch (Exception e) { // 체크.기타오류 처리여부(3/3)
			log.error("{}", e);
			
			// 필요 시 퀵 망취소 필요
			
			throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"퀵주문접수 API"}) + e.getMessage());
		} 
		return ApiResult.createResult(resultDto); 
	}   
	
	
	/*************************************************3번째 Tab*****************************************/
	
	/**
	 * @description : 퀵접수(VSR)및처리 조회 - 수기퀵주문접수 List Method - 
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "퀵접수(VSR)및처리 조회 - 퀵주문접수 List", description = "퀵접수(VSR)및처리 조회 - 퀵주문접수List")
	@PostMapping(value = "/v1.0/getTab3MasterList")
	public ApiResult<List<WdQuickRequestResVOCDto>> getTab3MasterList(@RequestBody WdQuickRequestReqDto reqDto) {
		return ApiResult.createResult(wdQuickRequestService.getTab3MasterList(reqDto));
	}	
	
	/**
	 * @description : 퀵접수(VSR)및처리 수기퀵주문접수 상세 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "퀵접수(VSR)및처리 수기퀵주문접수 상세 조회 List", description = "상세 조회 List")
	@GetMapping(value = "/v1.0/getTab3DetailList01")
	public ApiResult<List<WdQuickRequestResVOCDto>> getTab3DetailList01(@Valid WdQuickRequestReqDto reqDto) {
		return ApiResult.createResult(wdQuickRequestService.getTab3DetailList01(reqDto));
	}		
	
	/** @throws Exception 
	 * @description :퀵접수(VSR)및처리 수기집하지 처리 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "퀵접수(VSR)및처리 수기집하지 처리", description = "수기집하지 처리")
	@PostMapping(value="/v1.0/saveDetailListDelivery02")
	public ApiResult<WdQuickRequestReqDto> saveDetailListDelivery02(@RequestBody WdQuickRequestReqDto reqDto) throws Exception  {
		return ApiResult.createResult(wdQuickRequestService.saveDetailListDelivery02(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
	
	/** @throws Exception 
	 * @description :퀵접수(VSR)및처리 수기 도착지 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "퀵접수(VSR)및처리 수기 도착지 처리", description = "도착지 처리")
	@PostMapping(value="/v1.0/saveDetailListDestination02")
	public ApiResult<WdQuickRequestReqDto> saveDetailListDestination02(@RequestBody WdQuickRequestReqDto reqDto) throws Exception  {
		return ApiResult.createResult(wdQuickRequestService.saveDetailListDestination02(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}	
}
