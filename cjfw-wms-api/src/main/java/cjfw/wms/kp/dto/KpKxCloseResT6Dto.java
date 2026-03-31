package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YoSepPark (dytpq362@cj.net)
 * @date : 2025.11.27
 * @description : 조정요청내역) DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "조정요청내역")
public class KpKxCloseResT6Dto extends CommonDto {
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
    
    @Schema(description = "SKU")
    private String sku;

    @Schema(description = "문서번호")
    private String docno;

    @Schema(description = "선택")
    private String checkyn;

    @Schema(description = "문서유형")
    private String doctype;

    @Schema(description = "KX유형")
    private String kxtype;

    @Schema(description = "일자")
    private String deliverydate;

    @Schema(description = "KX물류센터코드")
    private String kxdccode;

    @Schema(description = "KX전표번호")
    private String kxslipno;

    @Schema(description = "KX전표순번")
    private String kxsliplineno;

    @Schema(description = "KX환산물량")
    private BigDecimal kxqty;

    @Schema(description = "KX단위")
    private String kxuom;

    @Schema(description = "수량")
    private BigDecimal qty;

    @Schema(description = "단위")
    private String uom;

    @Schema(description = "문서라인")
    private String docline;

    @Schema(description = "물류센터")
    private String dccode;

    @Schema(description = "창고드")
    private String organize;
    
    @Schema(description = "처리상태")
    private String processflag;

    @Schema(description = "처리결과메시지")
    private String processmsg;
}
