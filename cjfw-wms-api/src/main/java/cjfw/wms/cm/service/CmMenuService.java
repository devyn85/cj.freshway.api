package cjfw.wms.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmMainCodeResDto;
import cjfw.wms.cm.dto.CmMenuReqDto;
import cjfw.wms.cm.entity.CmMyMenuEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.18 
 * @description : 메뉴 조회 및 설정
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.18 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmMenuService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmMenuService.";

	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 즐겨찾기 메뉴 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.18 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<CmMainCodeResDto> getFavoriteMenuList(CmMenuReqDto cmMenuReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getFavoriteMenuList", cmMenuReqDto);
	}
	
	/**
	 * @description : 즐겨찾기 메뉴 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.18 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String insertFavoriteMenu(CmMenuReqDto cmMenuReqDto) {
		CmMyMenuEntity cmMyMenuEntity = ModelMapperUtil.map(cmMenuReqDto, userContext, CmMyMenuEntity.class);
		commonDao.insert(SERVICEID_PREFIX +"insertFavoriteMenu", cmMyMenuEntity);
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 즐겨찾기 메뉴 삭제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.18 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String deleteFavoriteMenu(CmMenuReqDto cmMenuReqDto) {
		CmMyMenuEntity cmMyMenuEntity = ModelMapperUtil.map(cmMenuReqDto, userContext, CmMyMenuEntity.class);
		commonDao.insert(SERVICEID_PREFIX +"deleteFavoriteMenu", cmMyMenuEntity);
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
