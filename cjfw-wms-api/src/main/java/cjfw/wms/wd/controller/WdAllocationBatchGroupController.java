package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdAllocationBatchGroupReqDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResCarDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab1CarnoDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab1CustDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab1Dto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab1SkuDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab1SlipDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab1ZoneDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab2DetailDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab2Dto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab3Dto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab4DetailDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab4Dto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab5Dto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab6Dto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab7Dto;
import cjfw.wms.wd.service.WdAllocationBatchGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.07.08
 * @description : 출고재고분배 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.08 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "WdAllocationBatchGroup", description = "출고재고분배")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/wd/allocationBatchGroup", "ltx/wd/allocationBatchGroup"})
public class WdAllocationBatchGroupController {

	private final WdAllocationBatchGroupService wdAllocationBatchGroupService;

	/**
	 * @description : 출고재고분배-자동분배 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.08 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출고재고분배-자동분배 목록 조회", description = "출고재고분배-자동분배 목록 조회")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<WdAllocationBatchGroupResTab1Dto>> getTab1MasterList(@RequestBody WdAllocationBatchGroupReqDto dto) {


		return ApiResult.createResult(wdAllocationBatchGroupService.getTab1MasterList(dto));
	}
	
	/**
	 * @description : 자동분배-거래처별 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.08 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동분배-거래처별 상세 조회", description = "자동분배-거래처별 상세 조회")
	@PostMapping(value = "/v1.0/getTab1CustList")
	public ApiResult<List<WdAllocationBatchGroupResTab1CustDto>> getTab1CustList(@RequestBody WdAllocationBatchGroupReqDto dto) {


		return ApiResult.createResult(wdAllocationBatchGroupService.getTab1CustList(dto));
	}
	
	/**
	 * @description : 자동분배-전표별 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.08 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동분배-전표별 상세 조회", description = "자동분배-전표별 상세 조회")
	@PostMapping(value = "/v1.0/getTab1SlipList")
	public ApiResult<List<WdAllocationBatchGroupResTab1SlipDto>> getTab1SlipList(@RequestBody WdAllocationBatchGroupReqDto dto) {
		
		
		return ApiResult.createResult(wdAllocationBatchGroupService.getTab1SlipList(dto));
	}
	
	/**
	 * @description : 자동분배-상품별 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.08 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동분배-상품별 상세 조회", description = "자동분배-상품별 상세 조회")
	@PostMapping(value = "/v1.0/getTab1SkuList")
	public ApiResult<List<WdAllocationBatchGroupResTab1SkuDto>> getTab1SkuList(@RequestBody WdAllocationBatchGroupReqDto dto) {
		
		
		return ApiResult.createResult(wdAllocationBatchGroupService.getTab1SkuList(dto));
	}	
	
	/**
	 * @description : 자동분배-차량별 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동분배-차량별 상세 조회", description = "자동분배-차량별 상세 조회")
	@PostMapping(value = "/v1.0/getTab1CarnoList")
	public ApiResult<List<WdAllocationBatchGroupResTab1CarnoDto>> getTab1CarnoList(@RequestBody WdAllocationBatchGroupReqDto dto) {
		
		
		return ApiResult.createResult(wdAllocationBatchGroupService.getTab1CarnoList(dto));
	}	
	
	/**
	 * @description : 자동분배-피킹존별 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동분배-피킹존별 상세 조회", description = "자동분배-피킹존별 상세 조회")
	@PostMapping(value = "/v1.0/getTab1ZoneList")
	public ApiResult<List<WdAllocationBatchGroupResTab1ZoneDto>> getTab1ZoneList(@RequestBody WdAllocationBatchGroupReqDto dto) {
		
		
		return ApiResult.createResult(wdAllocationBatchGroupService.getTab1ZoneList(dto));
	}	

	/**
	 * @description : 지정분배 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "지정분배 목록 조회", description = "지정분배 목록 조회")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<WdAllocationBatchGroupResTab2Dto>> getTab2MasterList(@RequestBody WdAllocationBatchGroupReqDto dto) {


		return ApiResult.createResult(wdAllocationBatchGroupService.getTab2MasterList(dto));
	}
		
	/**
	 * @description : 지정분배 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "지정분배 상세 조회", description = "지정분배 상세 조회")
	@PostMapping(value = "/v1.0/getTab2DetailList")
	public ApiResult<List<WdAllocationBatchGroupResTab2DetailDto>> getTab2DetailList(@RequestBody WdAllocationBatchGroupReqDto dto) {
		
		
		return ApiResult.createResult(wdAllocationBatchGroupService.getTab2DetailList(dto));
	}

	/**
	 * @description : 출고재고분배-피킹유형 미정의 관리처 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출고재고분배-피킹유형 미정의 관리처 목록 조회", description = "출고재고분배-피킹유형 미정의 관리처 목록 조회")
	@PostMapping(value = "/v1.0/getTab3MasterList")
	public ApiResult<List<WdAllocationBatchGroupResTab3Dto>> getTab3MasterList(@RequestBody WdAllocationBatchGroupReqDto dto) {
		
		
		return ApiResult.createResult(wdAllocationBatchGroupService.getTab3MasterList(dto));
	}
	

	/**
	 * @description : 차량별 피킹그룹내 CAR 원거리유형 존재여부 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "차량별 피킹그룹내 CAR 원거리유형 존재여부 조회", description = "차량별 피킹그룹내 CAR 원거리유형 존재여부 조회")
	@PostMapping(value = "/v1.0/getTab4CarInfo")
	public ApiResult<List<WdAllocationBatchGroupResCarDto>> getTab4CarInfo(@RequestBody WdAllocationBatchGroupReqDto dto) {


		return ApiResult.createResult(wdAllocationBatchGroupService.getTab4CarInfo(dto));
	}
	
	/**
	 * @description : 차량별분배 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "차량별분배 목록 조회", description = "차량별분배 목록 조회")
	@PostMapping(value = "/v1.0/getTab4MasterList")
	public ApiResult<List<WdAllocationBatchGroupResTab4Dto>> getTab4MasterList(@RequestBody WdAllocationBatchGroupReqDto dto) {
		
		
		return ApiResult.createResult(wdAllocationBatchGroupService.getTab4MasterList(dto));
	}
		
	/**
	 * @description : 차량별분배 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "차량별분배 상세 조회", description = "차량별분배 상세 조회")
	@PostMapping(value = "/v1.0/getTab4DetailList")
	public ApiResult<List<WdAllocationBatchGroupResTab4DetailDto>> getTab4DetailList(@RequestBody WdAllocationBatchGroupReqDto dto) {
		
		
		return ApiResult.createResult(wdAllocationBatchGroupService.getTab4DetailList(dto));
	}

	/**
	 * @description : 거래처상품별 상미율 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.20 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처상품별 상미율 목록 조회", description = "거래처상품별 상미율 목록 조회")
	@PostMapping(value = "/v1.0/getTab5MasterList")
	public ApiResult<List<WdAllocationBatchGroupResTab5Dto>> getTab5MasterList(@RequestBody WdAllocationBatchGroupReqDto dto) {


		return ApiResult.createResult(wdAllocationBatchGroupService.getTab5MasterList(dto));
	}
	
	/**
	 * @description : 분배예외처리거래처 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.21 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "분배예외처리거래처 목록 조회", description = "분배예외처리거래처 목록 조회")
	@PostMapping(value = "/v1.0/getTab6MasterList")
	public ApiResult<List<WdAllocationBatchGroupResTab6Dto>> getTab6MasterList(@RequestBody WdAllocationBatchGroupReqDto dto) {
		
		
		return ApiResult.createResult(wdAllocationBatchGroupService.getTab6MasterList(dto));
	}
	
	/**
	 * @description : 분배예외처리상품 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.21 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "분배예외처리상품 목록 조회", description = "분배예외처리상품 목록 조회")
	@PostMapping(value = "/v1.0/getTab7MasterList")
	public ApiResult<List<WdAllocationBatchGroupResTab7Dto>> getTab7MasterList(@RequestBody WdAllocationBatchGroupReqDto dto) {
		
		
		return ApiResult.createResult(wdAllocationBatchGroupService.getTab7MasterList(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 배치별분배 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배치별분배 저장", description = "배치별분배 저장")
	@PostMapping(value = "/v1.0/saveBatch")
	public ApiResult<String> saveBatch(@RequestBody WdAllocationBatchGroupReqDto dto) throws Exception {
		return ApiResult.createResult(wdAllocationBatchGroupService.saveBatch(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : STO분배 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.25 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "STO분배 저장", description = "STO분배 저장")
	@PostMapping(value = "/v1.0/saveSTOBatch")
	public ApiResult<String> saveSTOBatch(@RequestBody WdAllocationBatchGroupReqDto dto) throws Exception {
		return ApiResult.createResult(wdAllocationBatchGroupService.saveSTOBatch(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 자동분배-거래처별 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동분배-거래처별 저장", description = "자동분배-거래처별 저장")
	@PostMapping(value = "/v1.0/saveBatchDetail1")
	public ApiResult<String> saveBatchDetail1(@RequestBody WdAllocationBatchGroupReqDto dto) throws Exception {
		return ApiResult.createResult(wdAllocationBatchGroupService.saveBatchDetail1(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 자동분배-전표별 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동분배-전표별 저장", description = "자동분배-전표별 저장")
	@PostMapping(value = "/v1.0/saveBatchDetail2")
	public ApiResult<String> saveBatchDetail2(@RequestBody WdAllocationBatchGroupReqDto dto) throws Exception {
		return ApiResult.createResult(wdAllocationBatchGroupService.saveBatchDetail2(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 자동분배-상품별 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동분배-상품별 저장", description = "자동분배-상품별 저장")
	@PostMapping(value = "/v1.0/saveBatchDetail3")
	public ApiResult<String> saveBatchDetail3(@RequestBody WdAllocationBatchGroupReqDto dto) throws Exception {
		return ApiResult.createResult(wdAllocationBatchGroupService.saveBatchDetail3(dto));
	}
	
	
	/**
	 * @throws Exception
	 * @description : 자동분배-피킹존별 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동분배-피킹존별 저장", description = "자동분배-피킹존별 저장")
	@PostMapping(value = "/v1.0/saveBatchDetail4")
	public ApiResult<String> saveBatchDetail4(@RequestBody WdAllocationBatchGroupReqDto dto) throws Exception {
		return ApiResult.createResult(wdAllocationBatchGroupService.saveBatchDetail4(dto));
	}

	/**
	 * @throws Exception
	 * @description : 지정분배 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "지정분배 저장", description = "지정분배 저장")
	@PostMapping(value = "/v1.0/saveBatchTab2")
	public ApiResult<String> saveBatchTab2(@RequestBody WdAllocationBatchGroupReqDto dto) throws Exception {
		return ApiResult.createResult(wdAllocationBatchGroupService.saveBatchTab2(dto));
	}
	

	/**
	 * @throws Exception
	 * @description : 차량별분배 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.27 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "차량별분배 저장", description = "차량별분배 저장")
	@PostMapping(value = "/v1.0/saveBatchCarno")
	public ApiResult<String> saveBatchCarno(@RequestBody WdAllocationBatchGroupReqDto dto) throws Exception {
		return ApiResult.createResult(wdAllocationBatchGroupService.saveBatchCarno(dto));
	}
	

	/**
	 * @description : 피킹유형 미정의 관리처 원거리유형 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.21 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹유형 미정의 관리처 원거리유형 저장", description = "피킹유형 미정의 관리처 원거리유형 저장")
	@PostMapping(value = "/v1.0/saveTab3MasterList")
	public ApiResult<Object> saveTab3MasterList(@RequestBody List<WdAllocationBatchGroupReqDto> dto) {
		return ApiResult.createResult(wdAllocationBatchGroupService.saveTab3MasterList(dto));
	}				

	/**
	 * @description : 거래처상품별 상미율 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.20 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처상품별 상미율 저장", description = "거래처상품별 상미율 저장")
	@PostMapping(value = "/v1.0/saveTab5MasterList")
	public ApiResult<Object> saveTab5MasterList(@RequestBody List<WdAllocationBatchGroupReqDto> dto) {
		return ApiResult.createResult(wdAllocationBatchGroupService.saveTab5MasterList(dto));
	}				
	
	/**
	 * @description : 분배예외처리거래처 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.21 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "분배예외처리거래처 저장", description = "분배예외처리거래처 저장")
	@PostMapping(value = "/v1.0/saveTab6MasterList")
	public ApiResult<Object> saveTab6MasterList(@RequestBody List<WdAllocationBatchGroupReqDto> dto) {
		return ApiResult.createResult(wdAllocationBatchGroupService.saveTab6MasterList(dto));
	}				
	
	/**
	 * @description : 분배예외처리상품 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.21 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "분배예외처리상품 저장", description = "분배예외처리상품 저장")
	@PostMapping(value = "/v1.0/saveTab7MasterList")
	public ApiResult<Object> saveTab7MasterList(@RequestBody List<WdAllocationBatchGroupReqDto> dto) {
		return ApiResult.createResult(wdAllocationBatchGroupService.saveTab7MasterList(dto));
	}				

}