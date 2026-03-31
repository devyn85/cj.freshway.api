package cjfw.wms.kp.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.09.25
 * @description : 인수거절 미수신내역 확인 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "인수거절 미수신내역 확인")
public class KpKxCloseResT4Dto extends CommonDto {
    @Schema(description = "문서일자")
    private String docdt;

    @Schema(description = "KX 주문유형")
    private String kxordertype;

    @Schema(description = "문서구분")
    private String doctype;

    @Schema(description = "원본문서번호")
    private String sourcekey;

    @Schema(description = "원본문서라인")
    private String sourceline;

    @Schema(description = "SKU")
    private String sku;

    @Schema(description = "확정수량")
    private String confirmqty;

    @Schema(description = "단위")
    private String uom;

    @Schema(description = "출고확정여부")
    private String wdYn;

    @Schema(description = "CS전송처리여부")
    private String csIfYn;

    @Schema(description = "반품처리여부")
    private String rtYn;

    @Schema(description = "오더유형")
    private String ordertypeWd;

    @Schema(description = "등록일시")
    private String adddate;
}
