package cjfw.wms.kp.dto;


import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author      : YeoSeungCheol (pw6375@cj.net)
 * @date        : 2025.11.17
 * @description : 센터CAPA현황(NEW) 요청 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.17        YeoSeungCheol (pw6375@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "센터CAPA현황(NEW) 요청 DTO")
public class KpLocationCapaMonitoringNewReqDto extends CommonDto {
    @Schema(description = "물류센터 코드")
    private String fixdccode;
    
    /** 물류센터(다중검색) */
    @MultiSearch
    @Schema(description = "물류센터-다중검색")
    private List<String> fixdccodeMulti;      

    @Schema(description = "조회 기준일(YYYYMMDD)")
    private String smydt;

    @Schema(description = "존")
    private String zone;

    @Schema(description = "랙")
    private String rack;

    @Schema(description = "상태 필터(W/G/B/R)")
    private String status;
    
    @Schema(description = "상품코드")
    private String sku;
    
    @Schema(description = "저장조건")
    private String storagetype;
    
    private String lot;
    private String lottable01;
    private String lottable02;
    private String lottable03;
    private String lottable04;
    private String lottable05;
    private String fromzone;
    private String tozone;
    private String loc;
    private String stockid;
    private String stockgrade;
    
}
