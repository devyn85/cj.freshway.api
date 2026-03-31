package cjfw.wms.dv.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.14
 * @description : 일배입출차이현황 Grid1 Response DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.14 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Schema(description = "일배입출차이현황 Grid1 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DvDataviewMultiSpResGrid1Dto {

    /** 문서유형 */
    @Schema(description = "문서유형")
    private String doctype;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;

    /** 주문유형 */
    @Schema(description = "주문유형")
    private String ordertype;

    /** 고객사코드 */
    @Schema(description = "고객사코드")
    private String toCustkey;

    /** 고객사명 */
    @Schema(description = "고객사명")
    private String toCustname;

    /** 플랜트 */
    @Schema(description = "플랜트")
    private String plant;    

    // 플랜트명
    @Schema(description = "플랜트명")
    private String plantDescr;

    /** 주문단위 */
    @Schema(description = "주문단위")
    private String uom;

    /** 주문(예정)수량 */
    @Schema(description = "주문(예정)수량")
    private BigDecimal orderqtyDaily;

    /** 입고량 */
    @Schema(description = "입고량")
    private BigDecimal qtyDp;

    /** 출고량 */
    @Schema(description = "출고량")
    private BigDecimal qtyWd;

    /** 결품수량 */
    @Schema(description = "결품수량")
    private BigDecimal shortageqty;

    /** 결품사유 */
    @Schema(description = "결품사유")
    private String reasoncodeRt;
}
