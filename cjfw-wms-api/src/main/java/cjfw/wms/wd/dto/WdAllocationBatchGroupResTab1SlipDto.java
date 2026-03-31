package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.08 
 * @description : 자동분배-전표별 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "자동분배-전표별 상세 결과")
public class WdAllocationBatchGroupResTab1SlipDto extends CommonDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 전표번호
     */
    @Schema(description = "전표번호", example = "SLIP001")
    private String slipno;

    /**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "CUST001")
    private String toCustkey;

    /**
     * 배송인도처명
     */
    @Schema(description = "배송인도처명", example = "ABC물류")
    private String toCustname;

    /**
     * 주문유형
     */
    @Schema(description = "주문유형", example = "일반")
    private String ordertypeDescr;

    /**
     * OMS미마감건수
     */
    @Schema(description = "OMS미마감건수", example = "3")
    private String omsFlag;

    /**
     * 주문량
     */
    @Schema(description = "주문량", example = "100")
    private BigDecimal orderqty;

    /**
     * 분배량
     */
    @Schema(description = "분배량", example = "80")
    private BigDecimal processqty;

    /**
     * 피킹량
     */
    @Schema(description = "피킹량", example = "70")
    private BigDecimal workqty;

    /**
     * 상차스캔량
     */
    @Schema(description = "상차스캔량", example = "60")
    private BigDecimal inspectqty;

    /**
     * 출고확정량
     */
    @Schema(description = "출고확정량", example = "50")
    private BigDecimal confirmqty;

    /**
     * DCCODE
     */
    @Schema(description = "DCCODE", example = "DC01")
    private String dccode;

    /**
     * STORERKEY
     */
    @Schema(description = "STORERKEY", example = "STR01")
    private String storerkey;

    /**
     * ORDERTYPE
     */
    @Schema(description = "ORDERTYPE", example = "ORD01")
    private String ordertype;

    /**
     * UOM
     */
    @Schema(description = "UOM", example = "BOX")
    private String uom;

    /**
     * OPENQTY
     */
    @Schema(description = "OPENQTY", example = "20")
    private BigDecimal openqty;

    /**
     * PLANT
     */
    @Schema(description = "PLANT", example = "P100")
    private String plant;

    /**
     * STORAGETYPE
     */
    @Schema(description = "STORAGETYPE", example = "DRY")
    private String storagetype;

    /**
     * DISTANCETYPE
     */
    @Schema(description = "DISTANCETYPE", example = "LOCAL")
    private String distancetype;

    /**
     * BATCHGROUP
     */
    @Schema(description = "BATCHGROUP", example = "BG001")
    private String batchgroup;

    /**
     * SLIPDT
     */
    @Schema(description = "SLIPDT", example = "2025-07-08")
    private String slipdt;

    /**
     * SERIALYN
     */
    @Schema(description = "SERIALYN", example = "Y")
    private String serialyn;
}
