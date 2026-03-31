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
 * @description : 일배입출차이현황 Response DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.14 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Schema(description = "일배입출차이현황 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DvDataviewMultiSpResDto {
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;			

	 /** 물류센터코드 */
    @Schema(description = "물류센터코드")
    private String dccode;

    /** 물류센터코드 */
    @Schema(description = "물류센터코드명칭")
    private String dccodeName;

    // 플랜트
    @Schema(description = "플랜트")
    private String plant;

    // 배송일자
    @Schema(description = "배송일자")
    private String deliverydate;

    // 협력사코드
    @Schema(description = "협력사코드")
    private String custkey;
    
    // 협력사명
    @Schema(description = "협력사명")
    private String custname;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명칭 */
    @Schema(description = "상품명칭")
    private String skuname;

    
    // 플랜트명
    @Schema(description = "플랜트명")
    private String plantDescr;


    /** 주문(예정)수량 */
    @Schema(description = "주문(예정)수량")
    private BigDecimal orderqtyDaily;
    
    // 입고수량
    @Schema(description = "입고수량")
    private BigDecimal qtyDp;

    // 출고수량
    @Schema(description = "출고수량")
    private BigDecimal qtyWd;

    // 입출고차이
    @Schema(description = "입출고차이")
    private String qtyDiff;

    // 일배 구분
    @Schema(description = "일배구분")
    private String putawaytype;

    // 경유센터
    @Schema(description = "경유센터")
    private String deliveryroute;

    // 경유센터
    @Schema(description = "경유센터")
    private String cdeliveryrouteName;
    
    // 광역검수수량
    @Schema(description = "광역검수수량")
    private String routeinspectqty;

}
