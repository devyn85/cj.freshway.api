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
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardDetailGetReqDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardDetailGetResDto;
import cjfw.wms.comfunc.func.bbs.service.BbsUserViewPopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("comfunc/func/bbs/bbsUserViewPop")
public class BbsUserViewPopController {
	
	private final BbsUserViewPopService bbsUserViewPopService;

	/**
	 * 공지사항 조회한다.<br>
	 */
	@GetMapping(value = "/search")
	public ApiResult<List<BbsBoardDetailGetResDto>> getBoardDetail(@Valid BbsBoardDetailGetReqDto bbsBoardDetailGetReqDto){
		return ApiResult.createResult(bbsUserViewPopService.getBoardDetail(bbsBoardDetailGetReqDto));
	}

}

