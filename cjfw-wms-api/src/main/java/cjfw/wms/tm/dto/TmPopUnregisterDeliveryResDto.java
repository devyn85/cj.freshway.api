package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.02
 * @description : 거래처별 배송이력 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "거래처별 배송이력 조회 결과")
public class TmPopUnregisterDeliveryResDto {

	/** row 번호 */
    @Schema(description = "row 번호", nullable = false, example = "")
    private Integer rowcnt;

    /** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;

    /** 고객사 */
    @Schema(description = "고객사", nullable = false, example = "")
    private String storerkey;
    
    /** 거래처코드 */
    @Schema(description = "거래처코드", nullable = false, example = "")
    private String custkey;
    
    /** 거래처유형코드 */
    @Schema(description = "거래처유형코드", nullable = false, example = "")
    private String custtype;
    
    /** 배송일자 */
    @Schema(description = "배송일자", nullable = false, example = "")
    private String deliverydate;    

    /** POP번호 */
    @Schema(description = "POP번호", nullable = false, example = "")
    private String popno;
    
    /** 차량번호 */
    @Schema(description = "차량번호", nullable = false, example = "")
    private String carno;

    /** 주문중량 */
    @Schema(description = "주문중량", nullable = false, example = "")
    private java.math.BigDecimal orderweight;
    
    /** 주문량(CBM)  */
    @Schema(description = "주문량(CBM) ", nullable = false, example = "")
    private java.math.BigDecimal ordercbm;
    
    /** 평균중량 */
    @Schema(description = "평균중량", nullable = false, example = "")
    private java.math.BigDecimal avgweight;
    
    /**  평균 물량(CBM) */
    @Schema(description = " 평균 물량(CBM)", nullable = false, example = "")
    private java.math.BigDecimal avgcbm;
    
    /** 롤테이너 */
    @Schema(description = "롤테이너", nullable = false, example = "")
    private String rolltainerNo;
    
}
