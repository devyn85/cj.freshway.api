package cjfw.wms.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmUserPopupReqDto;
import cjfw.wms.cm.dto.CmUserPopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.09.11 
 * @description : 사용자 팝업 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.11 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmUserPopupService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmUserPopupService.";
	
	private final CommonDao commonDao;
	/**
	 * @description : 사용자 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- ㄹ
	 * 2025.09.11 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public Page<CmUserPopupResDto> getPopList(CmUserPopupReqDto cmPopupReqDto, Page<?> page) {
		
		return commonDao.selectPageList(SERVICEID_PREFIX + "getPopList", cmPopupReqDto, page); 
	}
	
	/** 
	 * @description : 사용자 팝업 목록 조회(전체)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.30        JangJaeHyun 생성
	 * ----------------------------------------------------------- 
	 */
	public List<CmUserPopupResDto> getPopAllList(CmUserPopupReqDto cmPopupReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getPopList", cmPopupReqDto); 
	}

}


