package cjfw.wms.kp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.07 
 * @description : 결품대응현황 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                 MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.07    공두경 (medstorm@cj.net) 생성 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "결품대응현황 Entity") 
public class KpWdRequestCancelqtyEntity extends CommonDto {
	
    /**상태*/
    @Schema(description = "상태")
    private String reqStatus;
    
    /**배송일자 */
    @Schema(description = "배송일자")
    private String deliverydate;
    
    /**문서일자 */
    @Schema(description = "문서일자")
    private String docdt;
    
    /**문서일자 */
    @Schema(description = "문서일자")
    private String missDocdt;
    
	/**
	 * fromDccode
	 */
	@Schema(description = "fromDccode", example = "2600")
	private String fromDccode;
	/**
	 * dlvLabel
	 */
	@Schema(description = "dlvLabel", example = "")
	private String dlvLabel;
    /**
     * 일부대응수량
     */
    @Schema(description = "일부대응수량", example = "5")
    private BigDecimal confirmqty;

    /**저장품누락요청센터 */
    @Schema(description = "저장품누락요청센터", example = "DC01")
    private String missDccode;

    /**요청번호 */
    @Schema(description = "요청번호", example = "S12345")
    private String serialkey;
    /**
     * docno
     */
    @Schema(description = "docno", example = "DOC001")
    private String docno;
    /**
     * docline
     */
    @Schema(description = "docline", example = "1")
    private String docline;
    /**
     * moveDt
     */
    @Schema(description = "moveDt", example = "20251001")
    private String moveDt;
    /**
     * sku
     */
    @Schema(description = "sku", example = "오뚜기 진라면")
    private String sku;
    /**
     * uom
     */
    @Schema(description = "uom", example = "EA")
    private String uom;
    /**
     * deliverygroup
     */
    @Schema(description = "deliverygroup", example = "R12")
    private String deliverygroup;
    /**
     * memo
     */
    @Schema(description = "memo", example = "")
    private String memo;
    /**
     * statusCode
     */
    @Schema(description = "statusCode", example = "")
    private String statusCode;
    /**
     * moveQty
     */
    @Schema(description = "moveQty", example = "10")
    private BigDecimal moveQty;
    /**
     * missOrderqty
     */
    @Schema(description = "missOrderqty", example = "10")
    private BigDecimal missOrderqty;
    /**
     * stoOrderqty
     */
    @Schema(description = "stoOrderqty", example = "10")
    private BigDecimal stoOrderqty;

    /** savetime */
    @Schema(description = "savetime", nullable = false, example = "")
    private Timestamp savetime;
}
