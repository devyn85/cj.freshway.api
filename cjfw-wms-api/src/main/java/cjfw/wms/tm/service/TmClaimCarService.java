package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmClaimCarClaimListReqDto;
import cjfw.wms.tm.dto.TmClaimCarClaimListResDto;
import cjfw.wms.tm.dto.TmClaimCarDtlListReqDto;
import cjfw.wms.tm.dto.TmClaimCarListDtlResDto;
import cjfw.wms.tm.dto.TmClaimCarListReqDto;
import cjfw.wms.tm.dto.TmClaimCarListResDto;
import cjfw.wms.tm.entity.TmClaimCarDtlEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.07 
 * @description : 배송전달사항 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.07 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmClaimCarService {

	/**`
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmClaimCarService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 배송클레임정보 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 ParkJinWoo 생성
	 */
	public List<TmClaimCarListResDto> getMasterList(TmClaimCarListReqDto tmClaimCarListReqDto) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist",tmClaimCarListReqDto);
	}
	
	/**
	 * @description : 배송클레임정보 조회 기능을 구현한 Method 
	 * @issues : 추후 추가 예정
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 ParkJinWoo 생성
	 */
	public List<TmClaimCarListDtlResDto> getMaster1List(TmClaimCarDtlListReqDto req) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "getDataDtllist",req);
	}
	
	/**
	 * @description : 배송클레임정보 클레임 리스트 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 ParkJinWoo 생성
	 */
	public List<TmClaimCarClaimListResDto> getClaimDtlList(TmClaimCarClaimListReqDto tmClaimCarClaimListReqDto) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
		tmClaimCarClaimListReqDto.setBaseCode("D");
		tmClaimCarClaimListReqDto.setCodeList("CLAIMTYPE");
		return commonDao.selectList(SERVICEID_PREFIX + "getClaimDtlList",tmClaimCarClaimListReqDto);
	}
	
	/**
	 * @description : 배송클레임정보 저장 기능을 구현한 Method 
	 * @issues : 추후 하단 그리드 저장 기능 추가
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 ParkJinWoo 생성
	 */
	public String saveConfirm(TmClaimCarDtlListReqDto req) {
		if(req != null) {
		List<TmClaimCarListDtlResDto> list = req.getSaveList();
		for (var dto : list) {
			var entity = ModelMapperUtil.map(dto, userContext, TmClaimCarDtlEntity.class);
			if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
//				commonDao.insert(SERVICEID_PREFIX +"insertDeliveryInfo", entity);
			} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				commonDao.update(SERVICEID_PREFIX +"updateIfRtClaiminfoD", entity);
			} 
		}
		}
	
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}


}
