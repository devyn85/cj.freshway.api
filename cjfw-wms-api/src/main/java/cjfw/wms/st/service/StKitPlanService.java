package cjfw.wms.st.service;


import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.st.dto.StKitPlanReqDto;
import cjfw.wms.st.dto.StKitPlanResT1Dto;
import cjfw.wms.st.dto.StKitPlanResT2Dto;
import cjfw.wms.st.dto.StKitPlanResT3Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : 고혜미
 * @date : 2025.10.21 
 * @description : KIT상품 계획등록 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.21 고혜미 (laksjd0606@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StKitPlanService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stKitPlanService.";
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	private final UserContext userContext;	
	
	/**
	 * @description : KIT상품 목록 조회
	 * @issues : <pre>
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.21 고혜미 생성 </pre>
	 */
	public List<StKitPlanResT1Dto> getMasterList01(StKitPlanReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList01", dto);
	}
	
	/**
	 * @description : KIT상품계획 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.21 고혜미 생성 </pre>
	 */
	public String saveMasterList(StKitPlanReqDto paramDto) throws Exception{
        List<StKitPlanResT1Dto> saveList = paramDto.getSaveList(); // 저장리스트
        String month = paramDto.getMonth();
        log.info("month ==> {}", paramDto.getMonth());
        log.info("saveList ==> {}", saveList);

    	for( var dto : saveList) {
    		dto.setPlanDt(month);
    		var entity = ModelMapperUtil.map(dto, userContext, StKitPlanResT1Dto.class);
    		
            if (dto.getDpQty() != null) {
                entity.setPlanDt(month + "00");  // 일자를 00으로 세팅
                entity.setOrderqty(null);
                entity.setOpenqty(null);
                entity.setConfirmqty(null);
                entity.setDpQty(dto.getDpQty());

                log.info("DP_QTY MERGE 실행: planDt={}, dpQty={}", entity.getPlanDt(), dto.getDpQty());
                commonDao.selectOne(SERVICEID_PREFIX + "saveMasterList", entity);
            }
    		
            // 1일부터 31일까지 일자별 데이터 체크
            for (int day = 1; day <= 31; day++) {
                String dayStr = String.format("%02d", day);
                BigDecimal orderqty  = getFieldValue(entity, "d" + dayStr + "Req");
                BigDecimal openqty = getFieldValue(entity, "d" + dayStr + "Plan");
                BigDecimal confirmqty = getFieldValue(entity, "d" + dayStr + "Prod");

                // 셋 다 null이면 스킵
                if (orderqty == null && openqty == null && confirmqty == null) continue;

                String planDt = month + dayStr;
                entity.setPlanDt(planDt);

                // 각각의 필드 매핑
                entity.setDpQty(null);        // DP_QTY는 없으므로 null
                entity.setOrderqty(orderqty);
                entity.setOpenqty(openqty);
                entity.setConfirmqty(confirmqty);

                log.info("일자별 MERGE 실행: planDt={}, req={}, plan={}, prod={}", planDt, orderqty, openqty, confirmqty);

                // Mapper SQL 실행
                commonDao.selectOne(SERVICEID_PREFIX + "saveMasterList", entity);
            }
    		
    	}
    	
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	private BigDecimal getFieldValue(Object obj, String fieldName) {
	    try {
	        Field field = obj.getClass().getDeclaredField(fieldName);
	        field.setAccessible(true);
	        return (BigDecimal) field.get(obj);
	    } catch (Exception e) {
	        return null;
	    }
	}
	
	/**
	 * @description : KIT상품계획등록 > KIT구성조회
	 * @issues : <pre>
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.29 고혜미 생성 </pre>
	 */
	public List<StKitPlanResT2Dto> getMasterList02(StKitPlanReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList02", dto);
	}
	
	/**
	 * @description : KIT상품계획등록 > KIT구성조회
	 * @issues : <pre>
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.29 고혜미 생성 </pre>
	 */
	public List<StKitPlanResT3Dto> getMasterList03(StKitPlanReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList03", dto);
	}
}
