package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : CROSS자동보충 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "CROSS자동보충 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StMoveCrossResDto {
    /** 체크여부 */
    @Schema(description = "체크여부")
    private String checkyn;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 구역 */
    @Schema(description = "구역")
    private String area;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** 재고등급 */
    @Schema(description = "재고등급")
    private String stockgrade;

    /** 재고등급명 */
    @Schema(description = "재고등급명")
    private String stockgradenm;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storagetype;

    /** 보관유형명 */
    @Schema(description = "보관유형명")
    private String storagetypenm;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 수량 */
    @Schema(description = "수량")
    private String qty;

    /** 할당수량 */
    @Schema(description = "할당수량")
    private String qtyallocated;

    /** 피킹수량 */
    @Schema(description = "피킹수량")
    private String qtypicked;

    /** 부족수량 */
    @Schema(description = "부족수량")
    private String nqty;

    /** 유통기한 */
    @Schema(description = "유통기한")
    private String lottable01;

    /** 유통기한/기준 */
    @Schema(description = "유통기한/기준")
    private String durationTerm;

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockid;

    /** 로트 */
    @Schema(description = "로트")
    private String lot;
		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
