package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmAttendanceListReqDto;
import cjfw.wms.tm.dto.TmAttendanceListResDto;
import cjfw.wms.tm.entity.TmAttendanceEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.09.16 
 * @description : 근태관리 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.16 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmAttendanceService {

	private transient static final String SERVICEID_PREFIX = "tmAttendanceService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 근태관리 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16 ParkJinWoo 생성
	 */
	public List<TmAttendanceListResDto> getMasterList(TmAttendanceListReqDto tmAttendanceListReqDto) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList",tmAttendanceListReqDto);
	}
	
	
	/**
	 * @description : 근태관리 수정 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16 ParkJinWoo 생성
	 */
	public String saveConfirm(TmAttendanceListReqDto req) {
		if(req != null) {
//		
		List<TmAttendanceListResDto> list = req.getSaveList();
		for (var dto : list) {
			var entity = ModelMapperUtil.map(dto, userContext, TmAttendanceEntity.class);
			
//			commonDao.delete(SERVICEID_PREFIX + "deleteAttend",entity);
			commonDao.update(SERVICEID_PREFIX +"mergeDriverAttend", entity);
			}
			
		}
		
	
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}


}
