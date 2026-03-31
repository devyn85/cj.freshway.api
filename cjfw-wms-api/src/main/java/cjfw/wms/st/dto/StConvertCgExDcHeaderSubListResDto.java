package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.25 
 * @description : 재고속성변경 헤더-Sub 목록 조회 Response DTO
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
public class StConvertCgExDcHeaderSubListResDto {

	 /** LOC 카테고리 */
    @Schema(description = "LOC 카테고리")
    private String locCategory;

    /** LOC 타입 */
    @Schema(description = "LOC 타입")
    private String locType;

    /** 재고 등급 */
    @Schema(description = "재고 속성")
    private String stockGrade;

    /** LOC 카테고리 명 */
    @Schema(description = "LOC 카테고리 명")
    private String locCategoryName;

    /** LOC 타입 명 */
    @Schema(description = "LOC 타입 명")
    private String locTypeName;

    /** 재고 등급 명 */
    @Schema(description = "재고 속성 명")
    private String stockGradeName;

    /** LOT 개수 */
    @Schema(description = "LOT 개수")
    private Integer lotCnt;

    /** LOC 개수 */
    @Schema(description = "LOC 개수")
    private Integer locCnt;

    /** SKU 개수 */
    @Schema(description = "SKU 개수")
    private Integer skuCnt;

    /** 재고 ID 개수 */
    @Schema(description = "재고 ID 개수")
    private Integer idCnt;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
