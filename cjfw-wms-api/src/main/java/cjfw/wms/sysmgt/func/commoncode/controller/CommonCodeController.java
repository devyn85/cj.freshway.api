/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.commoncode.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeDtlGetReqDto;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeDtlGetResDto;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeGrpGetReqDto;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeGrpGetResDto;
import cjfw.wms.sysmgt.func.commoncode.dto.CommonCodeSaveReqDto;
import cjfw.wms.sysmgt.func.commoncode.service.CommonCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "공통코드관리", description = "공통코드관리 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sysmgt/func/commoncode")
public class CommonCodeController {
	
	private final CommonCodeService commonCodeService;
	
	/**
	 * 공통 그룹 코드를 검색한다.<br>
	 */
	@Operation(summary = "공통그룹코드 목록", description = "공통그룹코드 목록 조회")
	@GetMapping(value = "/searchGroupCd")
	public ApiResult<List<CodeGrpGetResDto>> getGroupCdList(@Valid CodeGrpGetReqDto codeGrpReqDto) {
		return ApiResult.createResult(commonCodeService.getGroupCdList(codeGrpReqDto));
	}
	
	/**
	 * 공통 코드를 조회한다.<br>
	 */
	@Operation(summary = "공통코드 목록", description = "공통코드 목록 조회")
	@GetMapping(value = "/searchCommonCd")
	public ApiResult<List<CodeDtlGetResDto>> getCommonCdList(@Valid CodeDtlGetReqDto codeDtlReqDto) {
		return ApiResult.createResult(commonCodeService.getCommonCdList(codeDtlReqDto));
	}
	
	/**
	 * 공통 코드 변경 정보를 업데이트 한다.<br>
	 */
	@Operation(summary = "공통코드 저장", description = "공통코드 저장(등록/수정/삭제)")
	@PostMapping(value = "/save")
	public ApiResult saveCommonCd(@RequestBody @Valid CommonCodeSaveReqDto codeSaveReqDto) {
		log.info("{}", codeSaveReqDto);
		String resultMsgCode = commonCodeService.saveCommonCd(codeSaveReqDto);
		return ApiResult.createResult(resultMsgCode);
	}
		/**
		{
		 ds_code: {
		 	data: [{
				 CD_DESC: null
				 CD_NM: "aaa"
				 COM_CD: "a"
				 COM_CD_ORIG: "a"
				 COM_GRP_CD: "c"
				 RSV_STR1_VAL: null
				 RSV_STR2_VAL: null
				 RSV_STR3_VAL: null
				 RSV_STR4_VAL: null
				 RSV_STR5_VAL: null
				 RSV_STR6_VAL: null
				 RSV_STR7_VAL: null
				 RSV_STR8_VAL: null
				 SORT_NO: 2
				 USE_YN: "1"
				 __rowStatus: "U"
		 	},
			 {
				 CD_NM: "d-111"
				 COM_CD: "d-1"
				 COM_GRP_CD: "d"
				 SORT_NO: 1
				 USE_YN: "1"
				 __rowStatus: "I"
			 }
		 ],
		 	deletedData: []  // 제거
		 },
		ds_gcode: {
			data: [
			 {
				 COM_GRP_CD: "e"
				 GRP_CD_DESC: "eeeee"
				 GRP_CD_NM: "eeee"
				 USE_YN: "1"
				 __rowStatus: "I"
			 },
			 {
				COM_GRP_CD: "BIZ_SAMPLE_CD"
				COM_GRP_CD_ORIG: "BIZ_SAMPLE_CD"
				GRP_CD_DESC: "test111"
				GRP_CD_NM: "샘플구분"
				UPDR_ID: "devadmin01"
				UPD_DTM: "2021-07-16"
				USE_YN: "1"
				__rowStatus: "U"
				_rowIdField: 3
			 },
		 	{
				 COM_GRP_CD: "d"
				 COM_GRP_CD_ORIG: "d"
				 GRP_CD_DESC: "ddd"
				 GRP_CD_NM: "ddd"
				 UPDR_ID: "jws"
				 UPD_DTM: "2022-05-02"
				 USE_YN: "1"
				 __rowStatus: "U"
		 	},
			 {
				COM_GRP_CD: "d"
				GRP_CD_DESC: "ddd"
				GRP_CD_NM: "ddd"
				USE_YN: "Y"
				__rowStatus: "I"
				_rowIdField: "353EBBF0-0E61-A825-BDF0-8282F47C654E"
		 	}
		 ],
			deletedData: []   // 제거
		 */
	
	/**
	 * 공통코드 목록 조회(비동기 처리 Sample)<br>
	 */
	@Operation(summary = "공통코드 목록(비동기 처리 Sample)", description = "공통코드 목록 조회(비동기 처리 Sample)")
	@GetMapping(value = "/searchCommonCdAsyncTemplate")
	public ApiResult<List<CodeDtlGetResDto>> getCommonCdAsyncTemplateList() {
		return ApiResult.createResult(commonCodeService.getCommonCdAsyncTemplateList());
	}
}

