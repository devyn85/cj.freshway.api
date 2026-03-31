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
public class StConvertCgExDcDetailListReqDto extends CommonDto {

	 /** SKU 다중 선택 (쉼표 구분) */
    @Schema(description = "SKU 다중 선택", example = "SKU001,SKU002")
    private String sku;

    // @Schema(description = "창고(ORGANIZE) 다중 선택", example = "2600,2601")
    // private String multiOrganize;

    /* ─── 위치·존·층·LOC 조건 ─── */

    /** ZONE (LIKE) */
    @Schema(description = "ZONE (LIKE)", example = "A%")
    private String zone;

    /** 창고 Area (LIKE) */
    @Schema(description = "창고 Area (LIKE)", example = "A%")
    private String whArea;

    /** 창고 층 (LIKE) */
    @Schema(description = "창고 층 (LIKE)", example = "B%")
    private String whAreaFloor;

    /** From LOC (>=) */
    @Schema(description = "From LOC (>=)", example = "A01001")
    private String fromLoc;

    /** To LOC (<=) */
    @Schema(description = "To LOC (<=)", example = "A01999")
    private String toLoc;

    /** LOC Category (LIKE) */
    @Schema(description = "LOC Category (LIKE)", example = "A%")
    private String locCategory;

    /** LOC Type (LIKE) */
    @Schema(description = "LOC Type (LIKE)", example = "B%")
    private String locType;

    /* ─── 품목·재고 조건 ─── */

    /** 재고 등급 (LIKE) */
    @Schema(description = "재고 등급 (LIKE)", example = "A%")
    private String stockGrade;

    /** 재고 타입 (LIKE) */
    @Schema(description = "재고 타입 (LIKE)", example = "01%")
    private String stockType;

    /* ─── BL / 정렬 ─── */

    /** BL 존재 여부(Y/N) */
    @Schema(description = "BL 존재 여부(Y/N)", allowableValues = {"Y","N"}, example = "Y")
    private String searchBl;

    /** BL 번호 (LIKE) */
    @Schema(description = "BL 번호 (LIKE)", example = "%BL202406%")
    private String blNo;

    @MultiSearch
    @Schema(description = "BL 번호(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> blNoMulti;
    
    /** 창고(ORGANIZE) 코드 */
    @Schema(description = "창고(ORGANIZE) 코드", example = "2600")
    private String organize;

    /** 정렬 키(LOC 또는 DATE) */
    @Schema(description = "정렬 키(LOC 또는 DATE)", allowableValues = {"LOC","DATE"}, example = "LOC")
    private String sortKey;
    
    private String dcCode;
}
