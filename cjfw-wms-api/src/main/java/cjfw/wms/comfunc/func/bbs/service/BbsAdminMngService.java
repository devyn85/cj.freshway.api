/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.comfunc.func.bbs.dto.BbsAdminCheckGetReqDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardListGetReqDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardListGetResDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BbsAdminMngService{
	
	private final CommonDao commonDao;
	
	private final UserContext context;
	
	/**
	 * 관리자 권한을 조회한다.<br>
	 */
	public boolean getBbsAdminCheck(BbsAdminCheckGetReqDto bbsAdminCheckGetReqDto) {
		bbsAdminCheckGetReqDto.setUserId(context.getUserId());
		boolean isAdmin = ((int)commonDao.selectOne("bbsAdminMngService.getBbsAdminCheck", bbsAdminCheckGetReqDto) == 1) ? true : false;
		return isAdmin;
	}
	
	
	/**
	 * 공지사항 목록 데이터를 조회한다.<br>
	 */
	public List<BbsBoardListGetResDto> getBoardList(BbsBoardListGetReqDto bbsBoardListGetReqDto) {
		List<BbsBoardListGetResDto> list = null;
		if("true".equals(bbsBoardListGetReqDto.getIsAdmin())) {
			//관리자 권한
			list = commonDao.selectList("bbsAdminMngService.getBoardListAll", bbsBoardListGetReqDto);
		}
		else {
			//일반 권한
			list = commonDao.selectList("bbsAdminMngService.getBoardListUser", bbsBoardListGetReqDto);
		}
		
		return list;
	}
	
}