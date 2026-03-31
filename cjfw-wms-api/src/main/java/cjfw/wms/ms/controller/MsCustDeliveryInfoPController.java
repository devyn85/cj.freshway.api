package cjfw.wms.ms.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.ms.dto.MsCustDeliveryInfoPDetailPersonResDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoPDetailResDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoPReqDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoPResDto;
import cjfw.wms.ms.service.MsCustDeliveryInfoPService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.07.30 
 * @description : 기준정보 > 거래처기준정보 > 협력사정보 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 거래처기준정보 > 협력사정보", description = "협력사정보")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/custDeliveryInfoP")
public class MsCustDeliveryInfoPController {

	private final MsCustDeliveryInfoPService msCustDeliveryInfoPService;
		
	/**
	 * @description : 거래처 배송정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처 정보 조회 (목록)", description = "거래처 정보 조회 (목록)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Page<MsCustDeliveryInfoPResDto>> getMasterList(@RequestBody @Valid MsCustDeliveryInfoPReqDto dto) {
		return ApiResult.createResult(msCustDeliveryInfoPService.getMasterList(dto));
	}
	
	/**
	 * @description : 거래처 배송정보 조회 (단건) & 협력사 입고검수결과 수신자 마스터정보 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처 정보 조회 (단건) & 협력사 입고검수결과 수신자 마스터정보 조회(목록)", description = "거래처 정보 조회 (단건) & 협력사 입고검수결과 수신자 마스터정보 조회(목록)")
	@GetMapping(value = "/v1.0/getMaster")
	public ApiResult<MsCustDeliveryInfoPResDto> getMaster(@Valid MsCustDeliveryInfoPReqDto dto) {
		return ApiResult.createResult(msCustDeliveryInfoPService.getMaster(dto));
	}
		
	/**
	 * @description : 거래처 정보 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처 정보 저장", description = "거래처 정보 저장")
	@PostMapping(value = "/v1.0/saveMaster")
	public ApiResult<MsCustDeliveryInfoPResDto> saveMaster(@RequestBody @Valid MsCustDeliveryInfoPReqDto msCustDeliveryInfoPReqDto) {
		return ApiResult.createResult(msCustDeliveryInfoPService.saveMaster(msCustDeliveryInfoPReqDto));
	}
	
	/**
	 * @description : 엑셀 업로드 유효성 검사(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "엑셀 업로드 유효성 검사(목록)", description = "엑셀 업로드 유효성 검사(목록)")
	@PostMapping(value = "/v1.0/getValidateExcelList")
	public ApiResult<List<MsCustDeliveryInfoPDetailResDto>> getValidateExcelList(@RequestBody @Valid List<MsCustDeliveryInfoPReqDto> msCustDeliveryInfoPReqDto) {
		return ApiResult.createResult(msCustDeliveryInfoPService.getValidateExcelList(msCustDeliveryInfoPReqDto));
	}
	/**
	 * @description : 협력사 담당자 알림톡 수신 대상 개인정보 복호화(단건)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.17 생성 </pre>
	 */
	@Operation(summary = "협력사 담당자 알림톡 수신 대상 개인정보 복호화(단건)", description = "협력사 담당자 알림톡 수신 대상 개인정보 복호화(단건)")
	@PostMapping(value = "/v1.0/getMasterPersonDetail")
	public ApiResult<MsCustDeliveryInfoPDetailPersonResDto> getMasterPersonDetail(@RequestBody @Valid MsCustDeliveryInfoPDetailPersonResDto dto) {
		return ApiResult.createResult(msCustDeliveryInfoPService.getMasterPersonDetail(dto));
	}
	/**
	 * @description : 협력사 담당자 알림톡 수신 대상 개인정보 복호화(단건)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.17 생성 </pre>
	 */
	@Operation(summary = "협력사 담당자 개인정보 복호화(단건)", description = "협력사 담당자 대상 개인정보 복호화(단건)")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<MsCustDeliveryInfoPDetailResDto> getDetailList(@RequestBody @Valid MsCustDeliveryInfoPDetailResDto dto) {
		return ApiResult.createResult(msCustDeliveryInfoPService.getDetailList(dto));
	}
}