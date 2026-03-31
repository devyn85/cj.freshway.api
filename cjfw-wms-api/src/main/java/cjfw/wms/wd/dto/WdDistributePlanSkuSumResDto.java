package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net) 
 * @date : 2025.05.19 
 * @description :미출 예정 확인 (상품별 합계) 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.19 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "미출 예정 확인 (상품별 합계) 응답 DTO")
public class WdDistributePlanSkuSumResDto extends CommonDto {
	
    /** 선택 여부 */
    @Schema(description = "선택 여부", example = "0")
    private String checkyn;

    /** 고객사코드 */
    @Schema(description = "고객사코드(회사)", example = "")
	private String storerkey;

    /** 센터코드 */
    @Schema(description = "센터코드", example = "")
    private String dccode;

    /** 출고일자 */
    @Schema(description = "출고일자", example = "20240519")
    private String slipdt;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;

    /** 상품명칭 */
    @Schema(description = "상품명칭", example = "")
    private String skuname;

    /** 보관 유형 */
    @Schema(description = "배송채널", example = "")
    private String channel;

    /** 주문수량 */
    @Schema(description = "주문수량", example = "100")
    private BigDecimal orderqty;

    /** 현재고수량 */
    @Schema(description = "수량", example = "30")
    private BigDecimal qty;

    /** 가용재고수량 */
    @Schema(description = "가용재고수량", example = "20")
    private BigDecimal stOpenqty;

    /** shortageqty */
    @Schema(description = "shortageqty", example = "5")
    private BigDecimal shortageqty;

    /** 진행예정수량 */
    @Schema(description = "진행예정수량", example = "50")
    private BigDecimal openqty;

    /** 재고할당수량 */
    @Schema(description = "재고할당수량", example = "70")
    private BigDecimal qtyallocated;

    /** 피킹수량 합계 */
    @Schema(description = "피킹재고량", example = "60")
    private BigDecimal qtypicked;

    /** 작업수량 */
    @Schema(description = "작업수량", example = "100")
    private BigDecimal tranqty;

    /** 단위 */
    @Schema(description = "단위", example = "EA/BOX")
    private String uom;

    /** 입고예정량 */
    @Schema(description = "입고예정량", example = "15")
    private BigDecimal dpQty;

    /** C/D타입 */
    @Schema(description = "C/D타입", example = "CROSS")
    private String crossdocktype;
    
}