package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StLocMoveReqDto;
import cjfw.wms.st.dto.StLocMoveResDto;
import cjfw.wms.st.service.StLocMoveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description : 재고일괄이동 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "StLocMoveController API", description = "StLocMoveController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/locMove")
public class StLocMoveController {
	private final StLocMoveService stLocMoveService;

	/**
	 * @description : 재고일괄이동 조회 List Method
	 * @issues :
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고일괄이동 조회 List", description = "재고일괄이동 조회 List")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<StLocMoveResDto>> getTab1MasterList(@RequestBody StLocMoveReqDto reqDto) {
		return ApiResult.createResult(stLocMoveService.getTab1MasterList(reqDto));
	}

	/**
	 * @description : 재고일괄이동 상세 조회 List Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고일괄이동 상세 조회 List", description = "재고일괄이동 상세 조회 List")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<StLocMoveResDto>> getTab2MasterList(@RequestBody StLocMoveReqDto reqDto) {
		return ApiResult.createResult(stLocMoveService.getTab2MasterList(reqDto));
	}
	
	/**
	 * @description : 재고일괄이동 저장 처리 Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고일괄이동 저장 처리", description = "재고일괄이동 저장 처리")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<StLocMoveResDto> saveMasterList(@RequestBody StLocMoveReqDto reqDto) throws Exception{
		return ApiResult.createResult(stLocMoveService.saveMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}	
}
