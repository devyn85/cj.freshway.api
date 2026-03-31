package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.st.dto.StStockBrandReqDto;
import cjfw.wms.st.dto.StStockBrandResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.09.12 
 * @description : 브랜드별재고추이 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.12    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StStockBrandService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stStockBrandService.";	
		
	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : 브랜드별재고추이 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	public List<StStockBrandResDto> getMasterList(StStockBrandReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
	}
		
}
