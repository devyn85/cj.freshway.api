package cjfw.wms.om.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.om.dto.OmPurchaseMonitoringGraphResDto;
import cjfw.wms.om.dto.OmPurchaseMonitoringReqDto;
import cjfw.wms.om.dto.OmPurchaseMonitoringResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.10.14 
 * @description : 주문 > 주믄등록 > 저장품발주현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmPurchaseMonitoringService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omPurchaseMonitoringService.";
	
	private final CommonDao commonDao;

	
	/**
	 * @description : PO 발주현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmPurchaseMonitoringResDto> getMasterList(OmPurchaseMonitoringReqDto dto) {
		List<OmPurchaseMonitoringResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
	
	/**
	 * @description : 발주추이 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmPurchaseMonitoringGraphResDto> getGraphList(OmPurchaseMonitoringReqDto dto) {
		List<OmPurchaseMonitoringGraphResDto> rowBasedResults = commonDao.selectList(SERVICEID_PREFIX + "getGraphList", dto);
			
		
		Map<String, OmPurchaseMonitoringGraphResDto> resultMap = new LinkedHashMap<>();

		if (rowBasedResults != null && !rowBasedResults.isEmpty()) {

			for (OmPurchaseMonitoringGraphResDto row : rowBasedResults) {
		        String dt = row.getDt();
		        String dccode = row.getDccode();
		        String dynamicFieldName = "weight" + dccode;
		        
		        // type(발주구분)별 check
		        for (String type : dto.getTypeList()) {
		            
		            String key = dt + "_" + type;
		            
		            // Map에서 해당 DT+TYPE 그룹 객체를 가져오거나 새로 생성
		            OmPurchaseMonitoringGraphResDto currentResDto = resultMap.get(key);
		            
		            if (currentResDto == null) {
		                // 새로운 그룹 객체 생성 및 기본 정보 설정
		                currentResDto = new OmPurchaseMonitoringGraphResDto();
		                currentResDto.setDt(dt);
		                currentResDto.setType(type);
		                currentResDto.setWeights(new LinkedHashMap<>());
		                resultMap.put(key, currentResDto);
		            }
		            
		            // TYPE에 따라 적절한 Weight 값을 선택
		            String weightValue = null;
		            if ("ALL".equals(type)) {
		                weightValue = row.getWeightValue();
		            } else if ("PO".equals(type)) {
		            	weightValue = row.getPoWeightValue();
		            } else if ("STO".equals(type)) {
		            	weightValue = row.getStoWeightValue();
		            }
		            
		            // 동적 필드명(DCCODE)과 Weight 값을 Weights 맵에 추가
		            if (weightValue != null) {
		                currentResDto.getWeights().put(dynamicFieldName, weightValue);
		            }
		        }
		    }
		}

		// 맵의 값들을 최종 리스트로 변환하여 반환
		List<OmPurchaseMonitoringGraphResDto> result = new ArrayList<>(resultMap.values());
		return result;
	}
	
}
