package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StLocMoveBoxReqDto;
import cjfw.wms.st.dto.StLocMoveBoxResDto;
import cjfw.wms.st.service.StLocMoveBoxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description : 재고일괄이동(수원3층) Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "StLocMoveBoxController API", description = "StLocMoveBoxController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/locMoveBox")
public class StLocMoveBoxController {
	private final StLocMoveBoxService stLocMoveBoxService;

	/**
	 * @description : 재고일괄이동(수원3층) 조회 List Method
	 * @issues :
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고일괄이동(수원3층) 조회 List", description = "재고일괄이동(수원3층) 조회 List")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<StLocMoveBoxResDto>> getTab1MasterList(@RequestBody StLocMoveBoxReqDto reqDto) {
		return ApiResult.createResult(stLocMoveBoxService.getTab1MasterList(reqDto));
	}

	/**
	 * @description : 재고일괄이동(수원3층) 상세 조회 List Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고일괄이동(수원3층) 상세 조회 List", description = "재고일괄이동(수원3층) 상세 조회 List")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<StLocMoveBoxResDto>> getTab2MasterList(@Valid StLocMoveBoxReqDto reqDto) {
		return ApiResult.createResult(stLocMoveBoxService.getTab2MasterList(reqDto));
	}
	
	/**
	 * @description : 재고일괄이동(수원3층) 상세 조회 List Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고일괄이동(수원3층) 상세 저장 List", description = "재고일괄이동(수원3층) 상세 저장 List")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<StLocMoveBoxResDto> saveMasterList(@RequestBody StLocMoveBoxReqDto reqDto) throws Exception{
		return ApiResult.createResult(stLocMoveBoxService.saveMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}	
}
