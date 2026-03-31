package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StTplOnhandQtyReqDto;
import cjfw.wms.st.dto.StTplOnhandQtyResDto;
import cjfw.wms.st.service.StTplOnhandQtyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkYoSep 
 * @date : 2025.11.18  
 * @description : 위탁재고현황 조회 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.12 ParkYoSep 생성
 */
@Tag(name = "정산 > 위탁물류 > 위탁재고현황", description = "위탁재고현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/tplOnhandQty")
public class StTplOnhandQtyController {

    
    private final StTplOnhandQtyService stTplOnhandQtyService;


    /**
     * @description : 위탁재고현황 조회 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.18 ParkYoSep 생성
     */
    @Operation(summary = "위탁정산내역현황 조회", description = "위탁정산내역현황 조회")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult< List<StTplOnhandQtyResDto>> getMasterList(@Valid @RequestBody StTplOnhandQtyReqDto reqDto) {
        return ApiResult.createResult(stTplOnhandQtyService.getMasterList(reqDto));
    }

}