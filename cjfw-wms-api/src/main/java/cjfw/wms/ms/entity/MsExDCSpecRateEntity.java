package cjfw.wms.ms.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.06.16
 * @description : OO 기능을 구현한 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.16 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고 상품별 요율 정보 Entity")
public class MsExDCSpecRateEntity extends CommonDto {
    /** 상품분류 */
    @Schema(description = "상품분류")
    private String speccodenm;

    /**저장조건 */
    @Schema(description = "저장조건")
    private String storagetypenm;

    /**입고료 */
    @Schema(description = "입고료")
    private BigDecimal grpriceuppertranskg;

    /**출고료 */
    @Schema(description = "출고료")
    private BigDecimal gipriceuppertranskg;

    /**창고료 */
    @Schema(description = "창고료")
    private BigDecimal storagepriceuppertranskg;


    /** 상품분류 */
    @Schema(description = "상품분류", example = "10")
    private String sku;

    /** 상품분류명 */
    @Schema(description = "상품분류명", example = "축산물")
    private String skuName;

    /**저장방법 */
    @Schema(description = "저장방법", example = "냉동")
    private String storagetype;

    /** 단위 */
    @Schema(description = "단위", example = "BOX")
    private String baseuom;

    /** 입고료 */
    @Schema(description = "입고료", example = "120")
    private BigDecimal gipriceRank;

    /** 출고료 */
    @Schema(description = "출고료", example = "115")
    private BigDecimal grpriceRank;

    /** 창고료 */
    @Schema(description = "창고료", example = "50")
    private BigDecimal storagepriceRank;

    @Schema(description = "계약서 수")
    private String fileCnt;
}
