package cjfw.wms.cm.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.dto.CmSkuPopupReqDto;
import cjfw.wms.cm.dto.CmSkuPopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.05.09 
 * @description : ADMIN > 시스템운영 > 상품 코드 조회(상품정보) Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmSearch50Service {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmSearch50Service.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 상품 코드 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public Page<CmSkuPopupResDto> getSkuList(CmSkuPopupReqDto reqDto, Page page) {
		if(reqDto.getKit().equals("Y")) {
			return commonDao.selectPageList(SERVICEID_PREFIX + "getKitSkuList", reqDto, reqDto);
		} else {
			return commonDao.selectPageList(SERVICEID_PREFIX + "getSkuList", reqDto, reqDto);
		}	
	}
	
}
