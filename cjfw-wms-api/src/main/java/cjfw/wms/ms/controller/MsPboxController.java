package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsPboxReqDto;
import cjfw.wms.ms.dto.MsPboxResT1Dto;
import cjfw.wms.ms.dto.MsPboxResT2Dto;
import cjfw.wms.ms.service.MsPboxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.18
 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "재고 > 공용기 관리 > P-BOX 관리/사용 현황", description = "재고 > 공용기 관리 > P-BOX 관리/사용 현황을 조회, 저장, 출력 한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/pbox")
public class MsPboxController {
	private final MsPboxService msPboxService;

	/**
	 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 조회", description = "재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT1List")
	public ApiResult<List<MsPboxResT1Dto>> getMasterT1List(@RequestBody MsPboxReqDto dto) {
		return ApiResult.createResult(msPboxService.getMasterT1List(dto));
	}
	
	/**
	 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 사용현황_탭 유효한 차량번호 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 공용기 관리 > P-BOX 관리/사용 현황 사용현황_탭 유효한 차량번호 조회", description = "재고 > 공용기 관리 > P-BOX 관리/사용 현황 사용현황_탭 유효한 차량번호 조회")
	@PostMapping(value = "/v1.0/getCheckCarNo")
	public ApiResult<List<MsPboxResT1Dto>> getCheckCarNo(@RequestBody MsPboxReqDto dto) {
		return ApiResult.createResult(msPboxService.getCheckCarNo(dto));
	}
	
	/**
	 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 사용현황_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.22 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 공용기 관리 > P-BOX 관리/사용 현황 사용현황_탭 조회", description = "재고 > 공용기 관리 > P-BOX 관리/사용 현황 사용현황_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT2List")
	public ApiResult<List<MsPboxResT2Dto>> getMasterT2List(@RequestBody MsPboxReqDto dto) {
		return ApiResult.createResult(msPboxService.getMasterT2List(dto));
	}

	/**
	 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 저장 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 저장", description = "재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody MsPboxReqDto dto) throws Exception {
	    return ApiResult.createResult(msPboxService.saveMasterList(dto));
	}
	
	/**
	 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 인쇄 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 인쇄", description = "재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 인쇄")
	@PostMapping(value = "/v1.0/savePrintList")
	public ApiResult<String> savePrintList(@RequestBody MsPboxReqDto dto) throws Exception {
		return ApiResult.createResult(msPboxService.savePrintList(dto));
	}	
}
