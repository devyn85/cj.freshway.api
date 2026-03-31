package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.TmInvoicelogMgrReqDto;
import cjfw.wms.tm.dto.TmInvoicelogMgrResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.30 
 * @description :납품서 츨력로그 (관리자) Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.30 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmInvoicelogMgrService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmInvoicelogMgrService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 납품서 츨력로그 (관리자)조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.30 ParkJinWoo 생성
	 */
	public List<TmInvoicelogMgrResDto> getInvoiceLogList(TmInvoicelogMgrReqDto tmInvoicelogMgrReqDto) {
		tmInvoicelogMgrReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "getInvoiceLogList",tmInvoicelogMgrReqDto);
	}
	
	


}
