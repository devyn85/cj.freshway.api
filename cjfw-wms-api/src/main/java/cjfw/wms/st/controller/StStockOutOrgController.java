package cjfw.wms.st.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dp.dto.DpReceiptExDCReqDto;
import cjfw.wms.st.dto.StStockOutOrgReqDto;
import cjfw.wms.st.service.StStockOutOrgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.01 
 * @description : OO 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.01 ParkJinWoo 생성
 */
@Tag(name = "재고 > 재고현황 > 외부비축재고조회", description = "외부비축재고조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/stockOutOrg")
public class StStockOutOrgController {

	private final StStockOutOrgService stStockOutOrgService;

	/**
	 * @description : 외부비축재고조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.01 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축재고조회", description = "외부비축재고조회")
	@PostMapping(value = "/v1.0/getDataHeaderList")
	public ApiResult<Object> getDataHeaderList(@Valid @RequestBody StStockOutOrgReqDto stStockOutOrgReqDto) {
		return ApiResult.createResult(stStockOutOrgService.getDataHeaderList(stStockOutOrgReqDto));
	}
	
    /**
     * @throws Exception
     * @description : 이메일 발송
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "이메일 발송", description = "이메일 발송")
    @PostMapping(value = "/v1.0/sendEmail")
    public ApiResult<String> sendEmail(@RequestBody StStockOutOrgReqDto dto) {
        return ApiResult.createResult(stStockOutOrgService.sendEmail(dto));
    }
    
    /**
     * @throws Exception
     * @description : 이메일 발송
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "이메일 발송", description = "이메일 발송")
    @PostMapping(value = "/v1.0/test")
    public ApiResult<String> test() {
        return ApiResult.createResult(stStockOutOrgService.saveSkuAmount());
    }

}