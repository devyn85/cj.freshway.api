package cjfw.wms.ib.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : 고혜미 (laksjd0606@cj.net)
 * @date : 2025.09.25
 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 결과 DTO
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25 고혜미 (laksjd0606@cj.net) 생성
 *         </pre>
 */
@Schema(description = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbConsignMastResT1Dto extends CommonProcedureDto {
	
	/** =====================기준정보TAB===============================*/	
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode;
    
    /** 협력사코드 */
    @Schema(description = "협력사코드")
    private String custkey;    
    
    /** 협력사코드명 */
    @Schema(description = "협력사코드명")
    private String custName;      
    
    /** 재고월 */
    @Schema(description = "재고월")
    private String stockmonth;
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품명 */
    @Schema(description = "상품명")
    private String skunm;
    
    /** 단위 */
    @Schema(description = "단위")
    private String baseuom;
    
    /** 저장조건 */
    @Schema(description = "저장조건")
    private String storagetype;    
    
    /** 박스입수 */
    @Schema(description = "박스입수")
    private BigDecimal qtyperbox;
    
    /** 현재고수량 */
    @Schema(description = "현재고수량")
    private BigDecimal stock;
    
    /** 현재고박스수량 */
    @Schema(description = "현재고박스수량")
    private BigDecimal stockBox;
    
    /** 현재고EA수량 */
    @Schema(description = "현재고EA수량")
    private BigDecimal stockEa;

    /** 총입고수량 */
    @Schema(description = "총입고수량")
    private BigDecimal grConfirmqty;
    
    /** 총출고수량 */
    @Schema(description = "총출고수량")
    private BigDecimal giConfirmqty;
    
    /** 입고료 */
    @Schema(description = "입고료")
    private BigDecimal grTotal;
    
    /** 출고료 */
    @Schema(description = "출고료")
    private BigDecimal giTotal;
    
    /** 창고료 */
    @Schema(description = "창고료")
    private BigDecimal stTotal;

  /** 계근료 */
  @Schema(description = "계근료")
  private BigDecimal wghTotal;

  /** 작업료 */
  @Schema(description = "작업료")
  private BigDecimal workTotal;
  
  /** 보관료합계 */
  @Schema(description = "보관료합계")
  private BigDecimal total;
  
  /** 비고 */
  @Schema(description = "비고")
  private String rmk;  
	
}
