package cjfw.wms.tm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : Claude Assistant
 * @date : 2026.01.26
 * @description : 분할배송 상품 정보 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.26 Claude Assistant      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "분할배송 상품 정보 DTO")
public class TmSplitItemDto {

    @Schema(description = "센터코드")
    private String dccode;

    @Schema(description = "거래처키")
    private String custkey;

    @Schema(description = "거래처명")
    private String custname;

    @Schema(description = "상품설명")
    private String skuDescr;

    @Schema(description = "전표일자")
    private String slipdt;

    @Schema(description = "전표번호")
    private String slipno;

    @Schema(description = "전표라인")
    private String slipline;

    @Schema(description = "체적")
    private String cube;

    @Schema(description = "주문수량")
    private String orderQty;

    @Schema(description = "중량")
    private String weight;

    @Schema(description = "상품코드")
    private String sku;

    @Schema(description = "보관유형")
    private String storagetypedesc;

    @Schema(description = "단위")
    private String uom;

    @Schema(description = "분할배송 순번")
    private String splitDeliverySeq;
}
