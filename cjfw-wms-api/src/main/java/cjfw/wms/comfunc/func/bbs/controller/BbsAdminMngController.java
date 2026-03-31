/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.bbs.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.comfunc.func.bbs.dto.BbsAdminCheckGetReqDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardListGetReqDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardListGetResDto;
import cjfw.wms.comfunc.func.bbs.service.BbsAdminMngService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("comfunc/func/bbs/bbsAdminMng")
public class BbsAdminMngController {
	
	private final BbsAdminMngService bbsAdminMngService;
	
	
	
	/**
     * 관리자 권한 조회<br>
	 * @return 
     */
    @GetMapping(value = "/adminCheck")
	public ApiResult<Boolean> getBbsAdminCheck(BbsAdminCheckGetReqDto bbsAdminCheckGetReqDto) {
    	boolean isAdmin = bbsAdminMngService.getBbsAdminCheck(bbsAdminCheckGetReqDto);
		return ApiResult.createResult(isAdmin);
	}
    
	/**
	 * 공지사항을 조회한다.<br>
	 */
    @GetMapping(value = "/search")
	public ApiResult<List<BbsBoardListGetResDto>> getBoardList(BbsBoardListGetReqDto bbsBoardListGetReqDto) {
		return ApiResult.createResult(bbsAdminMngService.getBoardList(bbsBoardListGetReqDto));
	}
}

