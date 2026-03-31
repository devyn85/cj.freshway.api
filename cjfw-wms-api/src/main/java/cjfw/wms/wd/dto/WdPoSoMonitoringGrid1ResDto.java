package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.23
 * @description : 일배PO/SO연결모니터링 Grid1 List DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.23 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Schema(description = "일배PO/SO연결모니터링 Grid1 List DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WdPoSoMonitoringGrid1ResDto {

	/** 인도처 */
    @Schema(description = "인도처")
    private String toCustkey;

    /** 인도처명 */
    @Schema(description = "인도처명")
    private String toCustname;

    /** 물류센터코드/명 */
    @Schema(description = "물류센터코드/명")
    private String dccode;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;

    /** 품목번호 */
    @Schema(description = "품목번호")
    private BigDecimal docline;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 이력유무 */
    @Schema(description = "이력유무")
    private String serialyn;

    /** 비고정가여부 */
    @Schema(description = "비고정가여부")
    private String skunotfixedamountyn;

    /** 이력구분값 */
    @Schema(description = "이력구분값")
    private String serialtypeSn;

    /** 상품명칭 */
    @Schema(description = "상품명칭")
    private String skuname;

    /** 결품수량 */
    @Schema(description = "결품수량")
    private BigDecimal cancelqty;
    
    /** 주문수량 */
    @Schema(description = "주문수량")
    private BigDecimal openqty;
    
    /** 출고수량 */
    @Schema(description = "출고수량")
    private BigDecimal confirmqty;

    /** 판매담당자 */
    @Schema(description = "판매담당자")
    private String mdcodeKp;
}
