package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsSkuCbmResDto;
import cjfw.wms.ms.dto.MsSkuDetailResDto;
import cjfw.wms.ms.dto.MsSkuReqDto;
import cjfw.wms.ms.dto.MsSkuResDto;
import cjfw.wms.ms.entity.MsSkuEntity;
import cjfw.wms.ms.entity.MsUomEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.11
 * @description : 기준정보 > 상품기준정보 > 상품정보 목록 조회 및 저장 Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.11        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsSkuService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msSkuService.";
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
	public Page<MsSkuResDto> getMasterList(MsSkuReqDto dto) {
		if( dto.getSkuCode() == null || dto.getSkuCode().isEmpty()) {			
			return commonDao.selectPageList(SERVICEID_PREFIX + "getMasterList", dto, dto);
		} else {
			String[] originalSkus = dto.getSkuCode().split(","); 
			int pageSize = 1000;
			int groupCount = (int) Math.ceil((double) originalSkus.length / pageSize);

			// 2차원 배열 생성: String[묶음개수][최대1000개]
			String[][] skuCodeList = new String[groupCount][];

			for (int i = 0; i < groupCount; i++) {
			    int start = i * pageSize;
			    int end = Math.min(start + pageSize, originalSkus.length);
			    // 배열 복사를 통해 자르기
			    skuCodeList[i] = Arrays.copyOfRange(originalSkus, start, end);
			}
			dto.setSkuCodeList(skuCodeList);			
			return commonDao.selectPageList(SERVICEID_PREFIX + "getMasterList", dto, dto);	
		}		
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
	public MsSkuDetailResDto getMaster(MsSkuReqDto dto) {
		MsSkuDetailResDto data = commonDao.selectOne(SERVICEID_PREFIX + "getMaster", dto);
		return data;
	}
	
	/**
	 * 
	 * @description : 상품목록 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.11        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveSkuPlt(List<MsSkuReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsSkuEntity.class);
				if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
//					commonDao.update(SERVICEID_PREFIX +"updateMsSkuPlt", entity);
					commonDao.selectOne(SERVICEID_PREFIX +"updatePlt", entity);
					
					if(entity.getErr() > 0) {
						throw new UserHandleException(""+"에러코드 : "+ entity.getErr() + "에러메세지 : " + entity.getErrmsg());
					}
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
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
	public String saveMaster(MsSkuReqDto dto) {		
		var entity = ModelMapperUtil.map(dto, userContext, MsSkuEntity.class);
		if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
//			commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
			commonDao.selectOne(SERVICEID_PREFIX +"update", entity);
			
			if(entity.getErr() > 0) {
				throw new UserHandleException(""+"에러코드 : "+ entity.getErr() + "에러메세지 : " + entity.getErrmsg());
			}
		
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	/**
	 * 
	 * @description : CBM 목록조회 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsSkuCbmResDto> getCbmList(MsSkuReqDto dto) {
		List<MsSkuCbmResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getCbmList", dto);
		return list;
	}
	
	/**
	 * 
	 * @description : CBM목록 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.11        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveCbm(List<MsSkuReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsUomEntity.class);
				if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateCbm", entity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 엑셀 상품정보 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * -----------------------------------------------------------
	 * 2025.07.01        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveExcelList(List<MsSkuReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsSkuEntity.class);
				commonDao.update(SERVICEID_PREFIX +"updateExcelList", entity);
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
	 * 2025.12.02        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsSkuResDto> getValidateExcelList(List<MsSkuReqDto> dtoList) {
		List<MsSkuResDto> checkedResult = new ArrayList<MsSkuResDto>();
		if(dtoList != null) {
			for (MsSkuReqDto dto : dtoList) {
				MsSkuResDto result = commonDao.selectOne(SERVICEID_PREFIX + "getValidateExcelList", dto);
				checkedResult.add(result);
			}
		}
		return checkedResult;
	}
	
}
