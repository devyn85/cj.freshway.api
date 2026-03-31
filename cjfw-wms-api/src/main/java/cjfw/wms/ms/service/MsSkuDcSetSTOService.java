package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsSkuDcSetSTOReqDto;
import cjfw.wms.ms.dto.MsSkuDcSetSTOResDto;
import cjfw.wms.ms.dto.MsSkuReqDto;
import cjfw.wms.ms.dto.MsSkuResDto;
import cjfw.wms.ms.entity.MsSkuDcSetSTOEntity;
import cjfw.wms.ms.entity.MsSkuEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.04
 * @description : 기준정보 > 상품기준정보 > 센터상품속성(광역일배) 목록 조회 및 저장 Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.11        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsSkuDcSetSTOService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msSkuDcSetSTOService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * 
	 * @description : 목록조회 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.11        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public Page<MsSkuDcSetSTOResDto> getMasterList(MsSkuDcSetSTOReqDto dto, Page page) {
		return commonDao.selectPageList(SERVICEID_PREFIX + "getMasterList", dto, page);
	}	
	
	/**
	 * 
	 * @description : 단건조회 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.16        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public MsSkuDcSetSTOResDto getMaster(MsSkuDcSetSTOReqDto dto) {
		MsSkuDcSetSTOResDto data = commonDao.selectOne(SERVICEID_PREFIX + "getMaster", dto);
		return data;
	}
	
	
	/**
	 * 
	 * @description : 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.11        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveMaster(List<MsSkuDcSetSTOReqDto> dtoList) {
		for (var dto : dtoList) {
			var entity = ModelMapperUtil.map(dto, userContext, MsSkuDcSetSTOEntity.class);
			
			if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
				commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
			} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);			
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	/**
	 * @description : 엑셀 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * -----------------------------------------------------------
	 * 2025.12.22        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveExcelList(List<MsSkuDcSetSTOReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsSkuDcSetSTOEntity.class);
				commonDao.update(SERVICEID_PREFIX +"saveMaster", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 엑셀 업로드 유효성 검사(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.22        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsSkuDcSetSTOResDto> getValidateExcelList(List<MsSkuDcSetSTOReqDto> dtoList) {
		List<MsSkuDcSetSTOResDto> checkedResult = new ArrayList<MsSkuDcSetSTOResDto>();
		if(dtoList != null) {
			for (MsSkuDcSetSTOReqDto dto : dtoList) {
				MsSkuDcSetSTOResDto result = commonDao.selectOne(SERVICEID_PREFIX + "getValidateExcelList", dto);
				checkedResult.add(result);
			}
		}
		return checkedResult;
	}
}
