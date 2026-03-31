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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardDeletePostReqDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardDetailGetReqDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardDetailGetResDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardFileGetReqDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardFileGetResDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardSavePostReqDto;
import cjfw.wms.comfunc.func.bbs.service.BbsAdminRegPopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("comfunc/func/bbs/bbsAdminRegPop")
public class BbsAdminRegPopController {

	private final BbsAdminRegPopService bbsAdminRegPopService;

	/**
	 * 공지사항 조회한다.<br>
	 */
	@GetMapping(value = "/search")
	public ApiResult<List<BbsBoardDetailGetResDto>> getBoardDetail(@Valid BbsBoardDetailGetReqDto bbsBoardDetailGetReqDto){
		return ApiResult.createResult(bbsAdminRegPopService.getBoardDetail(bbsBoardDetailGetReqDto));
	}
	
	/**
	 * 공지사항 파일 조회.<br>
	 */
	@GetMapping(value = "/searchFile")
	public ApiResult<List<BbsBoardFileGetResDto>> getBoardFile(@Valid BbsBoardFileGetReqDto bbsBoardFileGetReqDto) {
		return ApiResult.createResult(bbsAdminRegPopService.getBoardFile(bbsBoardFileGetReqDto));
	}
	
	/**
	 * 공지사항 저장한다.<br>
	 */
	@PostMapping(value = "/save")
	public ApiResult<String> saveBoard(@RequestBody @Valid BbsBoardSavePostReqDto bbsBoardSavePostReqDto) {
		return ApiResult.createResult(bbsAdminRegPopService.saveBoard(bbsBoardSavePostReqDto));
	}
	
	/**
	 * 공지사항 삭제한다.<br>
	 */
	@PostMapping(value = "/delete")
	public ApiResult<String> deleteBoard(@RequestBody @Valid BbsBoardDeletePostReqDto bbsBoardDeletePostReqDto) {
		return ApiResult.createResult(bbsAdminRegPopService.deleteBoard(bbsBoardDeletePostReqDto));
	}
	
}

