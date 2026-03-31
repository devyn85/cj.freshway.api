package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimDongHyeon 
 * @date : 2025.07.01 
 * @description : 시점별재고조회 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.05 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StDailyStockService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stDailyStockService.";
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	
	/**
	 * @description : 시점별재고조회 목록 조회
	 * @issues : <pre>
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.01 KimDongHyeon 생성 </pre>
	 */
	public <R, T> List<R> getMasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
	}
	
	/**
	 * @description : 존 리스트 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public <R, T> List<R> getDataCodeList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataCodeList", reqDto);
	}
 	
}
