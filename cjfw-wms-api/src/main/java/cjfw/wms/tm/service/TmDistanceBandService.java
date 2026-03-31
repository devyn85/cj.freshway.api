package cjfw.wms.tm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmDistanceBandListReqDto;
import cjfw.wms.tm.dto.TmDistanceBandListResDto;
import cjfw.wms.tm.dto.TmEntityRuleMasterListReqDto;
import cjfw.wms.tm.dto.TmEntityRuleMasterListResDto;
import cjfw.wms.tm.dto.TmHjdDataListReqDto;
import cjfw.wms.tm.dto.TmHjdDataListResDto;
import cjfw.wms.tm.entity.TmDistanceBandEntity;
import cjfw.wms.tm.entity.TmEntityRuleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.09.11 
 * @description : 센터별구간설정 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.11 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmDistanceBandService {

	private transient static final String SERVICEID_PREFIX = "tmDistanceBandService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 센터별구간설정 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 ParkJinWoo 생성
	 */
	public List<TmDistanceBandListResDto> getMasterList(TmDistanceBandListReqDto tmDistanceBandListReqDto) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist",tmDistanceBandListReqDto);
	}
	
	
	/**
	 * @description : 센터별구간설정 수정 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 ParkJinWoo 생성
	 */
	public String saveConfirm(TmDistanceBandListReqDto req) {
		if(req != null) {
		List<TmDistanceBandListResDto> list = req.getSaveList();
		
		

			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, TmDistanceBandEntity.class);
				   /*
		          CASE1 ) UPDATE A
			           |---------------------------| ->A
			           |                             ->B
	                 => 정보만 UPDATE A
		          CASE2 ) UPDATE A, INSERT B
			           |---------------------------| ->A
			               |                         ->B
			           =>
			           |---|
			                |----------------------|

				  CASE3 ) UPDATE A, INSERT B
			           |-------|           |-------| ->A
			               |                         ->B
			           =>
			           |---|               |-------| ->A
			                |-------------|          ->B

	           */
//				var entity = ModelMapperUtil.map(dto, userContext, TmDistanceBandEntity.class);
						List<TmDistanceBandListResDto> result = commonDao.selectList(SERVICEID_PREFIX +"chkValcourierRange", entity);
						String chk1  = result.get(0).getChk1();
						String staDt = result.get(0).getStaDt();
						String endDt = result.get(0).getEndDt();
//						String serialKey = result.get(0).getSerialKey();
						
						if(chk1.equals("Y")) {
							commonDao.update(SERVICEID_PREFIX +"updateDistanceBand01", entity);
						}else {
							if(staDt != null && !"".equals(staDt)) {
//								entity.setSerialKey(serialKey);
								commonDao.update(SERVICEID_PREFIX +"updateDistanceBand02", entity);
							}
							if (endDt != null && !"".equals(endDt)){
								entity.setToDate(endDt);
							}
							commonDao.insert(SERVICEID_PREFIX +"insertDistanceBand", entity);
							
						}

								
							}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	public List<TmHjdDataListResDto> getHjdDataList(TmHjdDataListReqDto TmHjdDataListReqDto) {

		
		return commonDao.selectList(SERVICEID_PREFIX + "getHjdCdData", TmHjdDataListReqDto);
		
	}
	/**
	 * @description : 센터별구간설정 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 ParkJinWoo 생성
	 */
	public List<TmDistanceBandListResDto> getHjdDataSetList(TmHjdDataListReqDto TmHjdDataListReqDto) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "getHjdCdDataList",TmHjdDataListReqDto);
	}
	
	public List<TmEntityRuleMasterListResDto> ExcelUpload(TmEntityRuleMasterListReqDto req) {
		List<TmEntityRuleMasterListResDto> result = new ArrayList<TmEntityRuleMasterListResDto>();
		if(req != null) {
//		List<TmEntityRuleMasterListResDto> list ;
//		System.out.print(list);
			// ★ saveList 전체에 동일한 dcCode 주입
//	        for (TmEntityRuleMasterListResDto item : req.getSaveList()) {
//	            item.setDcCode(req.getDcCode());
//	        }
		result = commonDao.selectList(SERVICEID_PREFIX + "tmEntityExcelValChk", req.getSaveList());
		}
		
	
		
		return  result;
	}
	/**
	 * @description : 센터별구간설정 수정 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 ParkJinWoo 생성
	 */
	public String saveConfirmTmentity(TmEntityRuleMasterListReqDto req) {
		if(req != null) {
			List<TmEntityRuleMasterListResDto> list = req.getSaveList();
			for (var dto : list) {
			var entity = ModelMapperUtil.map(dto, userContext, TmEntityRuleEntity.class);
			List<TmEntityRuleMasterListResDto> result = commonDao.selectList(SERVICEID_PREFIX +"chkVal", entity);
			String chk1  = result.get(0).getChk1();
			String staDt = result.get(0).getStaDt();
			String endDt = result.get(0).getEndDt();
			String serialKey = result.get(0).getSerialKey();
			
			if (endDt != null && !"".equals(endDt)){
				entity.setToDate(endDt);
			}
			if(chk1.equals("Y")) {
				commonDao.update(SERVICEID_PREFIX +"updateTmSettlementItemRate01", entity);
			}else {
				if(staDt != null && !"".equals(staDt)) {
					entity.setSerialKey(serialKey);
					commonDao.update(SERVICEID_PREFIX +"updateTmSettlementItemRate02", entity);
				}
				
				commonDao.insert(SERVICEID_PREFIX +"insertTmSettlementItemRate", entity);
				
			}

			}		
				}
			
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
