package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmCodeReqDto;
import cjfw.wms.cm.dto.CmIssuePicturePopupReqDto;
import cjfw.wms.cm.dto.CmIssuePicturePopupResDto;
import cjfw.wms.cm.dto.CmMainCodeResDto;
import cjfw.wms.cm.service.CmCodeService;
import cjfw.wms.cm.service.CmIssuePicturePopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2026.02.20 
 * @description : 배송이슈 사진 파일 조회 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.20 KimSunHo(sunhokim6229@cj.net) 생성 </pre>
 */
@Tag(name = "배송이슈 사진 파일 팝업", description = "배송이슈 사진 파일 팝업")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/issuepicturepopup")
public class CmIssuePicturePopupController {

	private final CmIssuePicturePopupService cmIssuePicturePopupService;

	/**
	 * @description : 배송이슈 사진 파일 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.20 KimSunHo(sunhokim6229@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배송이슈 사진 파일 조회", description = "배송이슈 사진 파일 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<CmIssuePicturePopupResDto>> getMasterList(@Valid CmIssuePicturePopupReqDto reqDto) {
		return ApiResult.createResult(cmIssuePicturePopupService.getMasterList(reqDto));
	}

}