package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2026.02.11 
 * @description : KX 재고 조회
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.11 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "KX 재고 조회")
public class KpKxCloseResT10Dto {
	
    /** 센터코드 */
    @Schema(description = "센터코드", example = "")
    private String dccode;
    
    /** 조직코드 */
    @Schema(description = "조직코드", example = "")
    private String organize;
    
    /** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;
    
    /** 보관재고상태 */
    @Schema(description = "보관재고상태", example = "")
    private String stockType;
    
    /** 창고코드 SAP 창고 혹은 별도의 창고 코드 */
    @Schema(description = "창고코드 SAP 창고 혹은 별도의 창고 코드", example = "")
    private String area;
    
    /** 로케이션 */
    @Schema(description = "로케이션", example = "")
    private String loc;
    
    /** 단위 */
    @Schema(description = "단위", example = "")
    private String uom;
    
    /** 재고수량 */
    @Schema(description = "재고수량", example = "")
    private String qty;
    
    /** 가용재고 */
    @Schema(description = "가용재고", example = "")
    private String openQty;
    
    /** 입고예정수량 */
    @Schema(description = "입고예정수량")
    private BigDecimal qtyexpected;

    
    /** 출고예약 */
    @Schema(description = "출고예약", example = "")
    private String qtyReserve;
    
    /** 재고할당수량 */
    @Schema(description = "재고할당수량", example = "")
    private String qtyAllocated;
    
    /** 피킹재고수량 */
    @Schema(description = "피킹재고수량", example = "")
    private String qtyPicked;
    
    /** 홀딩재고수량 */
    @Schema(description = "홀딩재고수량", example = "")
    private String qtyHold;
    
    /** 재고 구분 ID */
    @Schema(description = "재고 구분 ID", example = "")
    private String stockId;
    
    /** 시리얼관리여부 */
    @Schema(description = "시리얼관리여부", example = "")
    private String serialYn;
    
    /** 제품 생산라인1 */
    @Schema(description = "제품 생산라인1", example = "")
    private String line01;
    
    /** 제품 생산라인2 */
    @Schema(description = "제품 생산라인2", example = "")
    private String line02;

}