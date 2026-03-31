package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.core.utility.MessageUtil;
import cjfw.wms.tm.dto.TmClaimListReqDto;
import cjfw.wms.tm.dto.TmClaimListResDto;
import cjfw.wms.tm.dto.TmCustDlvInfoPointReqDto;
import cjfw.wms.tm.dto.TmOrderListReqDto;
import cjfw.wms.tm.dto.TmOrderListResDto;
import cjfw.wms.tm.service.TmOrderListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 정종문 (loters@cj.net)
 * @date : 2025.08.20
 * @description : TM 주문 목록 조회 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.20 정종문 (loters@cj.net) 생성 </pre>
 */
@Tag(name = "TM > 주문관리", description = "TM 주문 목록 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/tm/orderList", "ltx/tm/orderList"})
public class TmOrderListController {

    private final TmOrderListService tmOrderListService;

    /**
     * @description : 주문 목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.20 정종문 (loters@cj.net) 생성 </pre>
     */
    @Operation(summary = "주문 목록 조회")
    @GetMapping(value = "/v1.0/getOrderList")
    public ApiResult<List<TmOrderListResDto>> getOrderList(@Valid TmOrderListReqDto tmOrderListReqDto) {
        List<TmOrderListResDto> result = tmOrderListService.getOrderList(tmOrderListReqDto);
        return ApiResult.createResult(result);
    }
    
    /**
     * @description : 거래처 좌표 대량 업데이트 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.24 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     * 2025.11.26 JuSungbin (wntjdqls9818@cj.net) 좌표 갱신시 센터좌표도 갱신 </pre>
     */
	@Operation(summary = "거래처 좌표 대량 업데이트", description = "검색조건에 따른 좌표값이 없는 거래처 갱신")
	@PostMapping(value = "/v1.0/updateBulkCustDlvInfoPoint")
	public ApiResult<String> updateBulkCustDlvInfoPoint(@RequestBody TmOrderListReqDto dto) {
		final int failCount = tmOrderListService.updateBulkCustDlvInfoPoint(dto);
		String message = failCount == 0 ? CanalFrameConstants.MSG_COM_SUC_CODE : "거래처 좌표 업데이트 오류가 " + failCount + "건 발생했습니다. 주소 확인 후 수동 업데이트 해주세요."; //  + failCount + "건 있습니다.";
		message = String.valueOf(failCount);
        int status = failCount == 0 ? 200 : 500;
		return ApiResult.createResult(message, status);
	}
	
    /**
     * @description : 거래처 좌표 단건 업데이트 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.24 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
	@Operation(summary = "거래처 좌표 단건 업데이트", description = "거래처 위/경도 사용자 지정 데이터 갱신 좌표값이 없는 거래처 갱신")
	@PostMapping(value = "/v1.0/updateCustDlvInfoPoint")
	public ApiResult<String> updateCustDlvInfoPoint(@RequestBody TmCustDlvInfoPointReqDto dto) {
		return ApiResult.createResult(tmOrderListService.updateCustDlvInfoPoint(dto));
	}

    /**
     * @description : 배송 클레임 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.20 Auto Generated 생성 </pre>
     */
    @Operation(summary = "배송 클레임 조회")
    @PostMapping(value = "/v1.0/getClaimList")
    public ApiResult<Page<TmClaimListResDto>> getClaimList(@RequestBody TmClaimListReqDto tmClaimListReqDto) {

        log.info("클레임 내역 조회 요청 - Request Parameters: deliverydtFrom=[{}], deliverydtTo=[{}], dccode=[{}], custCode=[{}]",
                tmClaimListReqDto.getDeliverydtFrom(),
                tmClaimListReqDto.getDeliverydtTo(),
                tmClaimListReqDto.getDccode(),
                tmClaimListReqDto.getCustkey());

        Page<TmClaimListResDto> result = tmOrderListService.getClaimList(tmClaimListReqDto);

        log.info("클레임 내역 조회 결과: {}", result);

        return ApiResult.createResult(result);
    }

}
