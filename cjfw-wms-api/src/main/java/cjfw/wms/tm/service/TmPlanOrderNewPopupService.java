package cjfw.wms.tm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.TmOrderListReqDto;
import cjfw.wms.tm.dto.TmPlanOrderNewPopupCustResDto;
import cjfw.wms.tm.dto.TmPlanOrderNewPopupReqDto;
import cjfw.wms.tm.dto.TmSetDispatchUnassignedOrderResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.31 
 * @description : 신규 주문 알림 서비스 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.31 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmPlanOrderNewPopupService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmPlanOrderNewPopupService.";
	private transient static final String SERVICEID_PREFIX_DISPATCH = "tmSetDispatchService.";
	private static final String PAKAGE_NAME = "SPTM_INPLAN";
	private static final String COMMAND_CREATION_WD = "CREATION_WD";

	private final CommonDao commonDao;
	private final CmCommonService cmCommonService;
    private final TmOrderListService tmOrderListService;
	private final UserContext userContext;
	
    public Integer getNewOrderCount(@RequestBody TmPlanOrderNewPopupReqDto dto) {
    	var result = commonDao.selectOne(SERVICEID_PREFIX + "getNewOrderCount", dto);
    	if(result == null) {
    		result = 0;
    	}
        return (int) result;
    }
    
    public List<TmSetDispatchUnassignedOrderResDto> saveNewOrder(@RequestBody TmPlanOrderNewPopupReqDto dto) {
    	TmOrderListReqDto reqDto = new TmOrderListReqDto();
    	reqDto.setDeliveryDate(dto.getDeliveryDate());
    	reqDto.setDeliveryType(dto.getTmDeliveryType());
    	reqDto.setGMultiDccode(dto.getDccode());

    	// 좌표 데이터 업데이트
    	final int failCount = tmOrderListService.updateBulkCustDlvInfoPoint(reqDto);

    	if(failCount == 0) {
    		throw new UserHandleException("거래처 좌표 업데이트 실패 " + failCount + "건 있습니다.");
    	}

    	List<TmPlanOrderNewPopupCustResDto> newCustList = commonDao.selectList(SERVICEID_PREFIX + "getNewOrder", dto);
    	List<TmSetDispatchUnassignedOrderResDto> response = new ArrayList<>();

    	newCustList.stream().forEach(cust -> {
    		TmSetDispatchUnassignedOrderResDto orderDto = new TmSetDispatchUnassignedOrderResDto();

    		// 문자열을 Double로 안전하게 변환
    		Double latitude = Double.parseDouble(cust.getLatitude());
    		Double longitude = Double.parseDouble(cust.getLongitude());

    		// [longitude, latitude] 순서로 리스트 생성
    		List<Double> coordinates = Arrays.asList(longitude, latitude);

    		orderDto.setClaimYn(cust.getClaimYn());
    		orderDto.setCustAddress(cust.getAddress1());
    		orderDto.setLocation(coordinates);
    		orderDto.setStorerkey(cust.getStorerkey());
    		orderDto.setCustType(cust.getCustType());
    		orderDto.setCustName(cust.getDescription());
    		orderDto.setFaceInspect(cust.getFaceInspect());
    		orderDto.setSpecialConditionYn(cust.getSpecialYn());
    		orderDto.setClaimYn(cust.getClaimYn());
    		orderDto.setReqdlvtime1From(cust.getReqdlvtime1From());
    		orderDto.setReqdlvtime1To(cust.getReqdlvtime1To());
    		orderDto.setKeyCustType(cust.getCustType());
    		orderDto.setReturnYn("N");
    		orderDto.setWeight(cust.getWeight());
    		orderDto.setCube(cust.getCube());
    		orderDto.setOrderQty(cust.getOrderQty());

    		response.add(orderDto);

    	});

    	commonDao.insert(SERVICEID_PREFIX_DISPATCH + "mergeTmInplanFromDocumentH", dto);

        return response;
    }

    /**
     * 신규 주문 추가 (TM_INPLAN 적재 + 실착지 목록 반환)
     * - SP 호출 전 신규 주문 상세 정보 조회 (정확한 신규 주문 수량/중량)
     * - SP 호출하여 TM_INPLAN에 주문 적재
     * - 조회된 신규 주문 정보를 미배차 데이터로 반환
     *
     * @param dto 요청 DTO (dccode, deliveryDate, tmDeliveryType)
     * @return 신규 주문 실착지 목록
     */
    public List<TmSetDispatchUnassignedOrderResDto> addNewOrders(@RequestBody TmPlanOrderNewPopupReqDto dto) {
        // 1. SP 호출 전 신규 주문 상세 정보 조회 (TM_INPLAN.DCCODE IS NULL인 주문)
        //    - getNewOrderCount와 동일한 조건으로 조회
        //    - SP 호출 전에 조회해야 신규 주문만의 정확한 수량/중량 확보
        List<TmSetDispatchUnassignedOrderResDto> newOrders = commonDao.selectList(
                SERVICEID_PREFIX + "getNewOrdersForDispatch", dto);

        if (ObjectUtils.isEmpty(newOrders)) {
            log.info("신규 주문이 없습니다. dccode: {}, deliveryDate: {}", dto.getDccode(), dto.getDeliveryDate());
            return new ArrayList<>();
        }

        log.info("신규 주문 조회 완료. dccode: {}, deliveryDate: {}, 건수: {}",
                dto.getDccode(), dto.getDeliveryDate(), newOrders.size());

        // 2. SP 호출하여 TM_INPLAN에 주문 적재 (SPTM_INPLAN.CREATION_WD)
        callCreationWdProcedure(dto);

        // 3. location 배열 생성 및 uniqueId 설정
        newOrders.forEach(order -> {
            // location 배열 생성 [경도, 위도]
            if (order.getLocationLng() != null && order.getLocationLat() != null) {
                order.setLocation(Arrays.asList(order.getLocationLng(), order.getLocationLat()));
            }
            // uniqueId 생성
            order.createUniqueId();
        });

        log.info("신규 주문 추가 완료. dccode: {}, deliveryDate: {}, 건수: {}",
                dto.getDccode(), dto.getDeliveryDate(), newOrders.size());

        return newOrders;
    }

    /**
     * SPTM_INPLAN.CREATION_WD 프로시저 호출
     * - WD_INPLAN → TM_INPLAN 데이터 적재
     */
    private void callCreationWdProcedure(TmPlanOrderNewPopupReqDto dto) {
        // 프로시저 파라미터 설정
        ProcedureParametersFactory.initParamDto(dto, dto, PAKAGE_NAME);
        dto.setAvc_COMMAND(COMMAND_CREATION_WD);
        dto.setAvc_DCCODE(dto.getDccode());

        String[] keyList = { "DELIVERYDT", "DCCODE", "STORERKEY" };
        Object[] valueList = { dto.getDeliveryDate(), dto.getDccode(), "FW00" };

        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));

        int rv = cmCommonService.saveProcedure(dto);
        log.info("SP CREATION_WD 호출 결과 rv: {}", rv);

        // 프로시저 결과 확인
        String resultCode = StringUtil.nvl(dto.getResultCode());
        String resultMessage = StringUtil.nvl(dto.getResultMessage());
        String returnMessage = StringUtil.nvl(dto.getReturnMessage());

        log.info("SP 결과 - resultCode: {}, resultMessage: {}, returnMessage: {}",
                resultCode, resultMessage, returnMessage);
    }

}
