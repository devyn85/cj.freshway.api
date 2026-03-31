package cjfw.wms.dv.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.10
 * @description : 부족분리스트 Response DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.10 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Schema(description = "부족분리스트 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DvPackingScarceStockResDto {

	private String hdType;

	/** 분할관리처코드 */
	@Schema(description = "분할관리처코드")
    private String mngplc;

    /**분할관리처코드명 */
    @Schema(description = "분할관리처코드명")
    private String mngplcnm;

    /*분배량*/
    @Schema(description = "분배량")
    private BigDecimal qtyallocated;

    /*피킹재고수량*/
    @Schema(description = "피킹재고수량")
    private BigDecimal qtypicked;

    /*재고수량*/
    @Schema(description = "재고수량")
    private BigDecimal stockQty;

    /*분배량 합계*/
    @Schema(description = "분배량 합계")
    private BigDecimal sumQtyallocated;

    /*피킹재고수량 합계*/
    @Schema(description = "피킹재고수량 합계")
    private BigDecimal sumQtypicked;

    /*재고수량 합계*/
    @Schema(description = "재고수량 합계")
    private BigDecimal shortQty;

    /*재고단위*/
    @Schema(description = "재고단위")
    private String stockUom;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 회사  */
    @Schema(description = "회사")
    private String storerkey;

    /** 배송그룹 */
    @Schema(description = "배송그룹")
    private String deliverygroup;

    /** 조정조건 */
    @Schema(description = "조정조건")
    private String storagetype;

    /** 주문수량 */
    @Schema(description = "주문수량")
    private BigDecimal orderqty;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 주문단위 */
    @Schema(description = "주문단위")
    private String uom;

}
