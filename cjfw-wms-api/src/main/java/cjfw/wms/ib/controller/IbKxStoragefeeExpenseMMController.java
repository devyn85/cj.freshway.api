package cjfw.wms.ib.controller;

import java.rmi.RemoteException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ib.dto.IbExpensePopupReqDto;
import cjfw.wms.ib.dto.IbExpenseReqDto;
import cjfw.wms.ib.dto.IbKxStoragefeeExpenseMMReqDto;
import cjfw.wms.ib.dto.IbKxStoragefeeExpenseReqDto;
import cjfw.wms.ib.service.IbKxStoragefeeExpenseMMService;
import cjfw.wms.ib.service.IbKxStoragefeeExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : ParkYoSep (dytpq362@cj.net)
 * @date        : 2025.12.29
 * @description : 비용기표(1000센터) 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.29         ParkYoSep (dytpq362@cj.net)      생성
 */
@Tag(name = "정산 > 1000센터 정산 > 비용기표(1000센터)", description = "비용기표(1000센터)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ib/kxStoragefeeExpenseMM")
public class IbKxStoragefeeExpenseMMController {
	private final IbKxStoragefeeExpenseMMService ibKxStoragefeeExpenseMMService;

	/**
	 * @description :  보관료 마감 계산 조회 기능을 구현한 Method 
	 * @issues : 
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = " 비용기표(1000센터) 조회 List", description = " 비용기표(1000센터 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@RequestBody IbKxStoragefeeExpenseMMReqDto dto) {
		return ApiResult.createResult(ibKxStoragefeeExpenseMMService.getMasterList(dto));
	}
    /**
     * @description : 비용기표(1000센터) 확정
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.05       생성
     */
    @Operation(summary = "비용기표 확정", description = "비용기표 확정")
    @PostMapping(value = "/v1.0/saveConfirm")
    public ApiResult<String> saveConfirm(@RequestBody IbKxStoragefeeExpenseMMReqDto dto)  {
        return ApiResult.createResult(ibKxStoragefeeExpenseMMService.saveConfirm(dto));
    }    
    /**
     * @description : 비용기표(1000센터) 확정취소
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.05       생성
     */
    @Operation(summary = "비용기표 확정취소", description = "비용기표 확정취소")
    @PostMapping(value = "/v1.0/saveConfirmCancel")
    public ApiResult<String> saveConfirmCancel(@RequestBody IbKxStoragefeeExpenseMMReqDto dto)  {
        return ApiResult.createResult(ibKxStoragefeeExpenseMMService.saveConfirmCancel(dto));
    }    
    /**
     * @throws RemoteException 
     * @description : 비용기표(1000) Posting
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.12       생성
     */
    @Operation(summary = "비용기표 Posting", description = "비용기표 Posting")
    @PostMapping(value = "/v1.0/savePosting")
    public ApiResult<String> savePosting(@RequestBody IbKxStoragefeeExpenseMMReqDto dto) throws RemoteException  {
        return ApiResult.createResult(ibKxStoragefeeExpenseMMService.posting(dto));
    }
    
    /**
     * @throws RemoteException 
     * @description : 비용기표(1000) Posting 취소
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.12       생성
     */
    @Operation(summary = "비용기표 Posting", description = "비용기표 Posting")
    @PostMapping(value = "/v1.0/savePostingCancel")
    public ApiResult<String> savePostingCancel(@RequestBody IbKxStoragefeeExpenseMMReqDto dto) throws RemoteException  {
        return ApiResult.createResult(ibKxStoragefeeExpenseMMService.postingCancel(dto));
    }
}
