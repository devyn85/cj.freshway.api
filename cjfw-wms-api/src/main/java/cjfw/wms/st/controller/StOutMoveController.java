package cjfw.wms.st.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StOutMoveMasterListReqDto;
import cjfw.wms.st.dto.StOutMoveResultListReqDto;
import cjfw.wms.st.service.StOutMoveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : 2025.07.29  
 * @date : 2025.07.01 
 * @description : 외부비축센터간이동 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.29 ParkJinWoo 생성
 */
@Tag(name = "재고 > 재고작업 > 외부비축센터간이동", description = "외부비축센터간이동")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/outMove")
public class StOutMoveController {

	private final StOutMoveService stOutMoveService;

	/**
	 * @description :  외부비축센터간이동 헤더목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.29 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축재고조회", description = "외부비축재고조회")
	@PostMapping(value = "/v1.0/getDataHeaderList")
	public ApiResult<Object> getDataHeaderList(@Valid @RequestBody StOutMoveMasterListReqDto reqDto) {
		return ApiResult.createResult(stOutMoveService.getStOutMoveMasterList(reqDto));
	}
	
	/**
	 * @description :  외부비축센터간이동 단가 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.29 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축재고단가 조회", description = "외부비축재고조회")
	@PostMapping(value = "/v1.0/getStockPrice")
	public ApiResult<Object> getStockPrice(@Valid @RequestBody StOutMoveMasterListReqDto reqDto) {
		return ApiResult.createResult(stOutMoveService.getStockPrice(reqDto));
	}
	/**
	 * @description : 외부비축센터간이동 저장결과 조회을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.29 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축재고조회", description = "외부비축재고조회")
	@PostMapping(value = "/v1.0/getDataResultList")
	public ApiResult<Object> getDataResultList(@Valid @RequestBody StOutMoveResultListReqDto reqDto) {
		return ApiResult.createResult(stOutMoveService.getStOutMoveResultList(reqDto));
	}
	/**
	 * @description : 외부비축센터간이동 저장결과 조회을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.29 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축재고조회", description = "외부비축재고조회")
	@PostMapping(value = "/v1.0/getExcelValChk")
	public ApiResult<Object> getExcelValChk(@Valid @RequestBody StOutMoveMasterListReqDto reqDto) {
		return ApiResult.createResult(stOutMoveService.getExcelValChk(reqDto));
	}
	
	/**
	 * @description : 외부비축센터간이동 저장기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.29 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축재고조회", description = "외부비축재고조회")
	@PostMapping(value = "/v1.0/saveDataList")
	public ApiResult<Object> saveDataList(@Valid @RequestBody StOutMoveMasterListReqDto reqDto) {
		return ApiResult.createResult(stOutMoveService.saveDataList(reqDto));
	}

}