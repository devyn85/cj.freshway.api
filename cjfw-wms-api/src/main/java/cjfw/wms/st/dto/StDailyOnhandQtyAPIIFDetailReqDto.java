package cjfw.wms.st.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.09.04 
 * @description : 외부창고 API 재고현황 인터페이스 저장 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.04    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고 API 재고현황 인터페이스 저장 요청") 
public class StDailyOnhandQtyAPIIFDetailReqDto  {	

    /** 재고일자 */
    @Schema(description = "재고일자", nullable = true, example = "")
    private String stockdate;

    /** 물류센터 */
    @Schema(description = "물류센터", nullable = true, example = "")
    private String dccode;

    /** 창고 */
    @Schema(description = "창고", nullable = true, example = "")
    private String organize;

    /** 창고명 */
    @Schema(description = "창고명", nullable = true, example = "")
    private String organizeName;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = true, example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", nullable = true, example = "")
    private String skuname;

    /** 단위*/
    @Schema(description = "단위", nullable = true, example = "")
    private String uom;
    
    /** B/L NO*/
    @Schema(description = "B/L NO", nullable = true, example = "")
    private String convserialno;

    /** 중량*/
    @Schema(description = "중량", nullable = true, example = "")
    private BigDecimal weight;

    /** 현재고 */
    @Schema(description = "현재고", nullable = true, example = "")
    private BigDecimal stqty;

    /** 차이 */
    @Schema(description = "차이", nullable = true, example = "")
    private BigDecimal diff;

    /** PLT입수 */
    @Schema(description = "PLT입수", nullable = true, example = "")
    private BigDecimal qtyperpallet;

    /** PLT수량 */
    @Schema(description = "PLT수량", nullable = true, example = "")
    private BigDecimal palletqty;

    /** 소비기한 */
    @Schema(description = "소비기한", nullable = true, example = "")
    private String usebydate;
    
    /** 제조일자 */
    @Schema(description = "제조일자", nullable = true, example = "")
    private String factorydate;

    /** 통관구분원산지 */
    @Schema(description = "통관구분원산지", nullable = true, example = "")
    private String placeoforigin;
    
}
