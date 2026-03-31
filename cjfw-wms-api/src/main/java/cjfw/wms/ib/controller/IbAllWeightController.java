package cjfw.wms.ib.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.dataaccess.largedata.LargeExcelUtil;
import cjfw.wms.wd.dto.WdInplanReqDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ib.dto.IbAllWeightReqDto;
import cjfw.wms.ib.service.IbAllWeightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.11.12
 * @description : 센터별물동량 정산 Controller Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "IbAllWeightController API", description = "IbAllWeightController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ib/allWeight")
public class IbAllWeightController {
    private final IbAllWeightService ibAllWeightService;

    /**
     * @description : 센터별물동량 정산 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "센터별물동량 정산 조회 List", description = "센터별물동량 정산 조회 List")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List> getMasterList(@RequestBody IbAllWeightReqDto reqDto, HttpServletRequest req) throws IOException {
        List a = ibAllWeightService.getMasterList(reqDto);
        return ApiResult.createResult(ibAllWeightService.getMasterList(reqDto));
    }

    /**
     * @description : 센터별물동량 정산 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "센터별물동량 정산2 조회 List", description = "센터별물동량 정산2 조회 List")
    @PostMapping(value = "/v1.0/getMasterList2")
    public ApiResult<List> getMasterList2(@RequestBody IbAllWeightReqDto reqDto) {
        return ApiResult.createResult(ibAllWeightService.getMasterList2(reqDto));
    }

    /**
     * @description : 센터별물동량 정산 저장
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
	@Operation(summary = "저장", description = "저장")
	@PostMapping(value = "/v1.0/saveMasterList2")
	public ApiResult<String> saveMasterList2(@RequestBody IbAllWeightReqDto dto) throws Exception {
		return ApiResult.createResult(ibAllWeightService.saveMasterList2(dto));
	}

    /**
     * @description : 센터별물동량 정산 복사
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
	@Operation(summary = "복사", description = "복사")
	@PostMapping(value = "/v1.0/copyMasterList2")
	public ApiResult<String> copyMasterList2(@RequestBody IbAllWeightReqDto dto) throws Exception {
		return ApiResult.createResult(ibAllWeightService.copyMasterList2(dto));
	}

    /**
     * @description : 센터별물동량 정산 엑셀
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @PostMapping(value = "/v1.0/getExcel")
	public void saveExcel(@RequestBody IbAllWeightReqDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ibAllWeightService.getExcel(dto, request, response);
	}
}
