/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.file.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.comfunc.func.file.dto.SampleFilePageGetReqDto;
import cjfw.wms.comfunc.func.file.dto.SampleFilePageGetResDto;
import cjfw.wms.comfunc.func.file.dto.SampleFilePagePostReqDto;
import cjfw.wms.comfunc.func.file.service.SampleFilePageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("comfunc/func/filePage")
public class SampleFilePageController {

	private final SampleFilePageService sampleFilePageService;

	/**
	 * 공지사항을 조회한다.<br>
	 * Grid 내 첨부파일 샘플 데이터로 공지사항 데이터 사용<br>
	 */
	@GetMapping(value = "/search")
	public ApiResult<List<SampleFilePageGetResDto>> naviList(@Valid SampleFilePageGetReqDto sampleFilePageGetReqDto) {
		return ApiResult.createResult(sampleFilePageService.getBoardListAll(sampleFilePageGetReqDto));
	}
	
	/**
	 * 공지사항 첨부파일 정보를 저장<br>
	 */
	@PostMapping(value = "/save")
	public ApiResult<String> saveBoardAttchFileGrpNo(@RequestBody @Valid SampleFilePagePostReqDto sampleFilePagePostReqDto) {
		return ApiResult.createResult(sampleFilePageService.saveBoardAttchFileGrpNo(sampleFilePagePostReqDto));
	}
}
