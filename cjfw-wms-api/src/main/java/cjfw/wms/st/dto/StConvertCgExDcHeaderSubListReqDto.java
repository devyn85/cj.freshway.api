package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.25 
 * @description : 재고속성변경 헤더-Sub 목록 조회 Request DTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.25 ParkJinWoo 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StConvertCgExDcHeaderSubListReqDto extends CommonDto {

    /** ─────────── 조회 조건 ─────────── */

    /** SKU 다중 선택 */
    @Schema(description = "SKU 다중 선택", example = "[\"SKU001\",\"SKU002\"]")
    private String sku;

//    /** 조직(창고) 다중 선택 */
//    @Schema(description = "창고 다중 선택", example = "[\"2600\",\"2601\"]")
//    private String organize;

    /** 존 코드 (LIKE) */
    @Schema(description = "ZONE", example = "A%")
    private String zone;

    /** 창고 Area (LIKE) */
    @Schema(description = "창고 Area", example = "A%")
    private String whArea;

    /** 층 (LIKE) */
    @Schema(description = "창고 층", example = "B%")
    private String whAreaFloor;

    /** LOC 시작값 (>=) */
    @Schema(description = "From LOC", example = "A01001")
    private String fromLoc;

    /** LOC 종료값 (<=) */
    @Schema(description = "To LOC", example = "A01999")
    private String toLoc;

    /** BL 존재 여부 (Y/N) */
    @Schema(description = "BL 존재 여부", allowableValues = {"Y","N"}, example = "Y")
    private String searchBl;

    /** 재고등급 (LIKE) */
    @Schema(description = "재고 등급", example = "A%")
    private String stockGrade;

    /** BL 번호 (LIKE) */
    @Schema(description = "BL 번호", example = "%BL202406%")
    private String blNo;
    
    @MultiSearch
    @Schema(description = "BL 번호(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> blNoMulti;
    /** 창고(조직) 코드 (=) */
    @Schema(description = "창고", example = "2600")
    private String organize;
    
    private String dcCode;
}
