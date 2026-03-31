package cjfw.wms.om.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.om.dto.OmTnsfMissingSTOReqDto;
import cjfw.wms.om.dto.OmTnsfMissingSTOResDto;
import cjfw.wms.om.entity.OmTnsfMissingSTOEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.11.12 
 * @description : 주문 > 주믄등록 > 누락분 STO 이체 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.12 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmTnsfMissingSTOService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omTnsfMissingSTOService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 누락분 STO 이체 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmTnsfMissingSTOResDto> getMasterList(OmTnsfMissingSTOReqDto dto) {
		List<OmTnsfMissingSTOResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
	
	/**
	 * @description : 누락분 STO 이체 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmTnsfMissingSTOResDto> getMasterPrintList(OmTnsfMissingSTOReqDto dto) {
		List<OmTnsfMissingSTOResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterPrintList", dto);
		return result;
	}
	
	/**
	 * @description : 누락분 STO 공급받는 센터 이체 마스터 조회 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.09 ParkJinWoo 생성
	 */
	public List<OmTnsfMissingSTOResDto> getMasterList1(OmTnsfMissingSTOReqDto dto) {
		List<OmTnsfMissingSTOResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList1", dto);
		return result;
	}
	/**
	 * @description : 누락분 STO 공급받는 센터 이체 마스터 조회 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.09 ParkJinWoo 생성
	 */
	public List<OmTnsfMissingSTOResDto> getMaster1PrintList(OmTnsfMissingSTOReqDto dto) {
		List<OmTnsfMissingSTOResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMaster1PrintList", dto);
		return result;
	}
	
	
	/**
	 * @description : 누락분 STO 이체 마스터 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String saveMasterList(List<OmTnsfMissingSTOReqDto> dtoList) {
		for(OmTnsfMissingSTOReqDto dto : dtoList) {

			OmTnsfMissingSTOEntity entity = ModelMapperUtil.map(dto, userContext, OmTnsfMissingSTOEntity.class);
			commonDao.update(SERVICEID_PREFIX + "saveMasterList", entity);			
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 누락분 STO 대응여부 저장
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.11 ParkJinWoo 생성
	 */
	public String saveMasterList1(List<OmTnsfMissingSTOReqDto> dtoList) {
		for(OmTnsfMissingSTOReqDto dto : dtoList) {

			OmTnsfMissingSTOEntity entity = ModelMapperUtil.map(dto, userContext, OmTnsfMissingSTOEntity.class);
			commonDao.update(SERVICEID_PREFIX + "saveMasterList1", entity);			
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 누락분 STO 이체 마스터 확정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String confirmMasterList(List<OmTnsfMissingSTOReqDto> dtoList) {
		for(OmTnsfMissingSTOReqDto dto : dtoList) {

			OmTnsfMissingSTOEntity entity = ModelMapperUtil.map(dto, userContext, OmTnsfMissingSTOEntity.class);
			commonDao.update(SERVICEID_PREFIX + "confirmMasterList", entity);			
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 누락분 STO 이체 대응완료(요청받는)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.12 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String supplyConfirmList(List<OmTnsfMissingSTOReqDto> dtoList) {
		for(OmTnsfMissingSTOReqDto dto : dtoList) {

			OmTnsfMissingSTOEntity entity = ModelMapperUtil.map(dto, userContext, OmTnsfMissingSTOEntity.class);
			commonDao.update(SERVICEID_PREFIX + "supplyConfirmList", entity);			
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
