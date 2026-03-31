package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsLocationPrintReqDto;
import cjfw.wms.ms.dto.MsLocationPrintResDto;
import cjfw.wms.ms.service.MsLocationPrintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.24
 * @description : 기준정보 > 물류센터 정보 > 로케이션 라벨 출력 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.24 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "기준정보 > 물류센터 정보 > 로케이션 라벨 출력", description = "기준정보 > 물류센터 정보 > 로케이션 라벨 출력을 조회한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/locationPrint")
public class MsLocationPrintController {
	
	private final MsLocationPrintService msLocationPrintService;

	/**
	 * @description : 기준정보 > 물류센터 정보 > 로케이션 라벨 출력 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.24 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "기준정보 > 물류센터 정보 > 로케이션 라벨 출력 조회", description = "기준정보 > 물류센터 정보 > 로케이션 라벨 출력 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsLocationPrintResDto>> getMasterList(@RequestBody MsLocationPrintReqDto dto) {
		return ApiResult.createResult(msLocationPrintService.getMasterList(dto));
	}

}
