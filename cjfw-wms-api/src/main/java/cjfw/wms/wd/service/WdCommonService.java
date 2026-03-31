package cjfw.wms.wd.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.core.utility.masking.MaskUtil;
import cjfw.core.utility.masking.MaskingTypeEnum;
import cjfw.wms.wd.dto.WdQuickResAPI02Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description :퀵배송상세  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class WdCommonService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(WdCommonService.class.getSimpleName()) + ".";
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;		
	
	/** 
	 * @description : 퀵접수(VSR)및처리 퀵주문접수 API 전문 결과 update Method
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	 */
	public WdQuickResAPI02Dto setResAPI02(JsonNode n,WdQuickResAPI02Dto paramDto) {
		// 파라미터 위변조 방지
		WdQuickResAPI02Dto dto = ModelMapperUtil.map(paramDto, WdQuickResAPI02Dto.class);
		
	    dto.setSerialNumber(n.path("serial_number").asText()); // 퀵주문번호
	    dto.setOrderState(n.path("order_state").asText());    // 주문상태
	    dto.setOrderDate(n.path("order_date").asText());      // 주문일시

	    dto.setCustomerName(n.path("customer_name").asText()); // 접수자 이름ㆍ상호
	    dto.setCustomerDepartment(n.path("customer_department").asText()); // 접수 부서명

	    dto.setCarType(n.path("car_type").asText()); // 차량 유형
	    dto.setDeliveryType(n.path("delivery_type").asText()); // 배송 유형(편도/왕복)

	    dto.setDepartureDepartment(n.path("departure_department").asText()); // 출발 부서
	    dto.setDepartureStaff(n.path("departure_staff").asText()); // 출발 담당자
	    dto.setDepartureCustomer(n.path("departure_customer").asText()); // 출발지 명칭
	    dto.setDepartureDongName(n.path("departure_dong_name").asText()); // 출발지 동명
	    dto.setDepartureAddress(n.path("departure_address").asText()); // 출발지 상세주소

	    dto.setDestinationCustomer(n.path("destination_customer").asText()); // 도착지 명칭
	    dto.setDestinationDongName(n.path("destination_dong_name").asText()); // 도착지 동명
	    dto.setDestinationAddress(MaskUtil.mask(StringUtil.nvl(n.path("destination_address").asText()),MaskingTypeEnum.ADDR)); // 도착지 상세주소
	    //
	    dto.setTotalCost(getBigDecimal(n, "total_cost"));        // 총 요금
	    dto.setSummary(n.path("summary").asText());              // 주문 요약/요청사항
	    dto.setBasicCost(getBigDecimal(n, "basic_cost")       ); // 기본 요금
	    dto.setAdditionCost(getBigDecimal(n, "addition_cost") ); // 추가 요금
	    dto.setDiscountCost(getBigDecimal(n, "discount_cost") ); // 할인 요금
	    dto.setDeliveryCost(getBigDecimal(n, "delivery_cost") ); // 배송 요금
	    //
	    dto.setHappyCall(n.path("happy_call").asText()); // 해피콜 여부
	    dto.setCustomerCode(n.path("customer_code").asText()); // 고객 코드

	    dto.setRiderCode(n.path("rider_code").asText()); // 배송기사 코드
	    dto.setRiderId(n.path("rider_id").asText()); // 배송기사 ID
	    dto.setRiderName(  MaskUtil.mask(StringUtil.nvl(n.path("rider_name").asText())  ,MaskingTypeEnum.NAME)); // 배송기사 이름
	    dto.setRiderMobile(MaskUtil.mask(StringUtil.nvl(n.path("rider_mobile").asText()),MaskingTypeEnum.PHONE)); // 배송기사 연락처
	    dto.setRiderLon(n.path("rider_lon").asText()); // 배송기사 경도
	    dto.setRiderLat(n.path("rider_lat").asText()); // 배송기사 위도

	    dto.setDistince(n.path("distince").asText()); // 이동 거리
	    dto.setOrderRegistType(n.path("order_regist_type").asText()); // 주문 등록 유형
	    dto.setCompleteTime(n.path("complete_time").asText()); // 완료 시간

	    dto.setOEtc1(n.path("o_etc1").asText()); // 기타1 - 센터접수번호
	    dto.setOEtc2(n.path("o_etc2").asText()); // 기타2 - VOC번호)
	    dto.setOEtc3(n.path("o_etc3").asText()); // 기타3 - 센터코드
	    dto.setOEtc4(n.path("o_etc4").asText()); // 기타4 - 귀책사유
	    dto.setOEtc5(n.path("o_etc5").asText()); // 기타5 - 귀책부서
	    dto.setOEtc6(n.path("o_etc6").asText()); // 기타6 - 귀책담당자
	    dto.setOEtc7(n.path("o_etc7").asText()); // 기타7 - 서버정보(local:로컬,dev:개발,qa:Q/A,prd:운영)
	    dto.setOEtc8(n.path("o_etc8").asText()); // 기타8
	    dto.setOEtc9(n.path("o_etc9").asText()); // 기타9
	    dto.setOEtc10(n.path("o_etc10").asText()); // 기타10
	    
	    
		return dto;
	}	
	
	private BigDecimal getBigDecimal2(JsonNode node, String field) {
	    String v = node.path(field).asText(null);
	    return (v == null || v.isBlank()) ? BigDecimal.ZERO : new BigDecimal(v);
	}
	
	private BigDecimal getBigDecimal(JsonNode node, String field) {
	    String v = node.path(field).asText(null);
	    if (v == null || v.isBlank()) return BigDecimal.ZERO;
	    v = v.replace(",", ""); // Remove thousands separator
	    return new BigDecimal(v);
	}	

	/** 
	 * @description : 퀵 사용자id 조회
	 * @issues :<pre>
	 * 
	 * realOpen 설정이 Y인 경우 운영기에서 quick api 오픈한 경우로 테스트데이터 프리픽스 제거하지 않고 quickUserId 그대로 반환
	 * 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	 */
	public String getQuickUserid(String quickUserId) {
		String realOpen = ContextUtil.getProperty("cf.quick.realOpen");
		
		// 퀵접수 오픈 전이면 테스트 데이터로 고정
		if(!"Y".equals(realOpen)) { 
			quickUserId = "10000_test"; 
		}
		
		if (quickUserId == null || quickUserId.isBlank()) {
			throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"quickUserId"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
		}
		return quickUserId;
		
	}
	
	/** @description : 퀵사용자 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> selectQuickUserIdList(T reqDto) {	
		return commonDao.selectList(SERVICEID_PREFIX+"selectQuickUserIdList", reqDto);
	}		
	
	/** @description : 센터리스트 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getCenterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getCenterList", reqDto);
	}	
	
	/** @description : 퀙센터 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> selectQuickCenterList(T reqDto) {	
		return commonDao.selectList(SERVICEID_PREFIX+"selectQuickCenterList", reqDto);
	}		

}
