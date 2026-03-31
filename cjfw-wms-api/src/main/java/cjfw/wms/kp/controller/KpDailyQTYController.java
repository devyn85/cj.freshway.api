package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpDailyQTYReqDto;
import cjfw.wms.kp.dto.KpDailyQTYResDto;
import cjfw.wms.kp.service.KpDailyQTYService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.02
 * @description : 지표 > 센터 운영 > 일일 물동량 조회 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.02 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "지표 > 센터 운영 > 일일 물동량 조회", description = "지표 > 센터 운영 > 일일 물동량을 조회한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/kp/dailyQTY", "ltx/kp/dailyQTY"})
public class KpDailyQTYController {
	private final KpDailyQTYService kpDailyQTYService;

	/**
	 * @description : 지표 > 센터 운영 > 일일 물동량 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.02 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표 > 센터 운영 > 일일 물동량 조회", description = "지표 > 센터 운영 > 일일 물동량 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public  ApiResult<List<KpDailyQTYResDto>> getMasterList(@RequestBody KpDailyQTYReqDto dto) {
		return ApiResult.createResult(kpDailyQTYService.getMasterList(dto));
	}	

}
