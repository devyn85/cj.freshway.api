package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmFuelEfficiencyReqDto;
import cjfw.wms.tm.dto.TmFuelEfficiencyResDto;
import cjfw.wms.tm.entity.TmFuelEfficiencyEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.09.10 
 * @description : 연비관리 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.10 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmFuelEfficiencyService {

	private transient static final String SERVICEID_PREFIX = "tmFuelEfficiencyService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 연비관리 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.10 ParkJinWoo 생성
	 */
	public List<TmFuelEfficiencyResDto> getMasterList(TmFuelEfficiencyReqDto tmFuelEfficiencyReqDto) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist",tmFuelEfficiencyReqDto);
	}
	
	
	/**
	 * @description : 연비관리 수정 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.10 ParkJinWoo 생성
	 */
	public String saveConfirm(TmFuelEfficiencyReqDto req) {
//		if(req != null) {
//		List<TmFuelEfficiencyResDto> list = req.getSaveList();
//		for (var dto : list) {
//			System.out.println("dto : " + dto);
//			var entity = ModelMapperUtil.map(dto, userContext, TmFuelEfficiencyEntity.class);
//			if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
//				int seq = commonDao.insert(SERVICEID_PREFIX +"insertFuelEfficiency", entity);
//				if(seq == 0) {
//					 throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
//					}
//			} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
//				int seq = commonDao.update(SERVICEID_PREFIX +"updateFuelEfficiency", entity);
//				if(seq == 0) {
//					 throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
//					}
//			} 
//		}
//		}
//	
		if(req != null) {
			List<TmFuelEfficiencyResDto> list = req.getSaveList();
			for (var dto : list) {
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
				var entity = ModelMapperUtil.map(dto, userContext, TmFuelEfficiencyEntity.class);
						List<TmFuelEfficiencyResDto> result = commonDao.selectList(SERVICEID_PREFIX +"chkVal", entity);
						String chk1  = result.get(0).getChk1();
						String staDt = result.get(0).getStaDt();
						String endDt = result.get(0).getEndDt();
						String serialKey = result.get(0).getSerialKey();
						
						if(chk1.equals("Y")) {
							commonDao.update(SERVICEID_PREFIX +"updateFuelEfficiency01", entity);
						}else {
							if(staDt != null && !"".equals(staDt)) {
								entity.setSerialKey(serialKey);
								commonDao.update(SERVICEID_PREFIX +"updateFuelEfficiency02", entity);
							}
							if (endDt != null && !"".equals(endDt)){
								entity.setToDate(endDt);
							}
							commonDao.insert(SERVICEID_PREFIX +"insertFuelEfficiency", entity);
							
						}

								
							}
						}
			return CanalFrameConstants.MSG_COM_SUC_CODE;
	
	}


}
