package cjfw.wms.ib.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ib.dto.IbExpenseStatusPopupReqDto;
import cjfw.wms.ib.dto.IbExpenseStatusPopupResDto;
import cjfw.wms.ib.dto.IbExpenseStatusReqDto;
import cjfw.wms.ib.service.IbExpenseStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.09.02 
 * @description : 원가관리리포트 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.05   KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "정산 > 외부창고정산 > 원가관리리포트", description = "원가관리리포트 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ib/expensestatus")
public class IbExpenseStatusController {

	private final IbExpenseStatusService ibExpenseStatusService;

	/**
	 * @throws RemoteException 
	 * @description : 비용기표 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.02    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "원가관리리포트 목록 조회", description = "원가관리리포트 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<IbExpenseStatusReqDto> getMasterList(@Valid IbExpenseStatusReqDto dto) throws RemoteException {
		return ApiResult.createResult(ibExpenseStatusService.getMasterList(dto));
	}
	
	/**
     * @description : 외부창고 원가관리리포트 Popup ERP 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.02    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고 원가관리리포트 Popup ERP 조회", description = "외부창고 원가관리리포트 Popup ERP 조회")
    @PostMapping(value = "/v1.0/getPopupErpPoNoInfo")
    public  ApiResult<List<IbExpenseStatusPopupResDto>> getPopupErpPoNoInfo(@Valid IbExpenseStatusPopupReqDto dto) throws RemoteException {
        return ApiResult.createResult(ibExpenseStatusService.getPopupErpPoNoInfo(dto));
    }

    /**
     * @description : 외부창고 원가관리리포트 Popup Item 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.02    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고 원가관리리포트 Popup Item 조회", description = "외부창고 원가관리리포트 Popup Item 조회")
    @PostMapping(value = "/v1.0/getPopupItemCodeInfo")
    public  ApiResult<List<IbExpenseStatusPopupResDto>> getPopupItemCodeInfo(@Valid IbExpenseStatusPopupReqDto dto) throws RemoteException {
        return ApiResult.createResult(ibExpenseStatusService.getPopupItemCodeInfo(dto));
    }
    
    /**
     * @description : 외부창고 원가관리리포트 Popup HouseBLNo 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.02    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고 원가관리리포트 Popup HouseBLNo 조회", description = "외부창고 원가관리리포트 Popup HouseBLNo 조회")
    @PostMapping(value = "/v1.0/getPopupHouseBLNoInfo")
    public  ApiResult<List<IbExpenseStatusPopupResDto>> getPopupHouseBLNoInfo(@Valid IbExpenseStatusPopupReqDto dto) throws RemoteException {
        return ApiResult.createResult(ibExpenseStatusService.getPopupHouseBLNoInfo(dto));
    }

}