package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : sss 
 * @date : 2025.07.01 
 * @description : 이력재고처리현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.16 성상수 (kduimux@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StStockSNService {

	private transient static final String SERVICEID_PREFIX = "stStockSNService.";
	private final CommonDao commonDao;
	
	/**
	 * @description : 이력재고처리현황 목록 조회
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.01 성상수 생성
	 */
	public <R, T> List<R> getMasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
	}
	

 	
}
