package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.06.04 
 * @description :외부창고 요율 평균치 체크 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.17 ParkJinWoo 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StConvertCgExDcHeaderListReqDto extends CommonDto {   // gUserId 등은 CommonDto에서 상속
	 // ──────────────── 검색·필터용 파라미터 ────────────────
	   /** SKU 다중 선택 (IN) */
    @Schema(description = "SKU 다중 선택", example = "[\"SKU0001\",\"SKU0002\"]")
    private String sku;

    /** 창고 Area (LIKE) */
    @Schema(description = "창고 Area", example = "A%")
    private String whArea;

    /** ZONE 시작 값 (>=) */
    @Schema(description = "From ZONE", example = "A01")
    private String fromZone;

    /** ZONE 종료 값 (<=) */
    @Schema(description = "To ZONE", example = "A99")
    private String toZone;

    /** LOC 시작 값 (>=) */
    @Schema(description = "From LOC", example = "A01001")
    private String fromLoc;

    /** LOC 종료 값 (<=) */
    @Schema(description = "To LOC", example = "A01999")
    private String toLoc;

    /** BL 존재여부(Y/N) */
    @Schema(description = "BL 존재 여부", allowableValues = {"Y","N"}, example = "Y")
    private String searchBl;

    /** 재고등급 (LIKE) */
    @Schema(description = "재고 등급", example = "A%")
    private String stockGrade;

    /** BL 번호 (LIKE) */
    @Schema(description = "BL 번호", example = "%BL202406%")
    private String blNo;

    /** 창고(조직) 코드 (=) */
    @Schema(description = "창고", nullable = false, example = "2600")
    private String organize;
    
    private String dcCode;

//    /** 조직 다중 선택 (IN) – 문자열 리스트 */
//    @Schema(description = "창고 다중 선택", example = "[\"2600\",\"2601\"]")
//    private String multiOrganize;
}