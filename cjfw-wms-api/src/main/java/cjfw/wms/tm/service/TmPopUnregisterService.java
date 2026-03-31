package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmPopUnregisterDeliveryResDto;
import cjfw.wms.tm.dto.TmPopUnregisterReqDto;
import cjfw.wms.tm.dto.TmPopUnregisterResDto;
import cjfw.wms.tm.dto.TmPopUnregisterRolltainerResDto;
import cjfw.wms.tm.entity.TmPopUnregisterEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.02 
 * @description : 거래처별 POP 미등록 현황 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmPopUnregisterService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmPopUnregisterService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 거래처별 POP 미등록 현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<TmPopUnregisterResDto> getMasterList(TmPopUnregisterReqDto reqDto) {
		List<TmPopUnregisterResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
		return result;		
	}
	
	
	/**
	 * @description : 거래처별 배송이력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<TmPopUnregisterDeliveryResDto> getCustDeliveryList(TmPopUnregisterReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getCustDeliveryList", reqDto);
	}
	
	
	/**
	 * @description : 차량별 롤테이너별 배송이력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<TmPopUnregisterRolltainerResDto> getCarRolltainerList(TmPopUnregisterReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getCarRolltainerList", reqDto);
	}
	
	/**
	 * @description : 추천 POP 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<TmPopUnregisterDeliveryResDto> getRecommendPOPList(TmPopUnregisterReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getRecommendPopList", reqDto);
	}
	
	/**
	 * @description : 거래처별 POP 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
	 */	
	public String saveCustPOP(TmPopUnregisterReqDto reqDto) {
		if (null != reqDto) {
			for (TmPopUnregisterDeliveryResDto custPop : reqDto.getSaveList()) {
				TmPopUnregisterEntity entity = ModelMapperUtil.map(custPop, userContext, TmPopUnregisterEntity.class);
				
				commonDao.insert(SERVICEID_PREFIX +"insert", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	

}
