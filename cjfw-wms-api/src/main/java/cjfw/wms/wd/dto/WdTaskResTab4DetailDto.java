package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.18 
 * @description : 피킹작업지시(차량)-조회생성 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.18 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "피킹작업지시-조회생성(차량) 상세 결과")
public class WdTaskResTab4DetailDto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * POP번호
     */
    @Schema(description = "POP번호", example = "POP001")
    private String deliverygroup;

    /**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "CUST001")
    private String toCustkey;

    /**
     * 배송인도처명
     */
    @Schema(description = "배송인도처명", example = "CJ대한통운")
    private String toCustname;

    /**
     * 주문유형
     */
    @Schema(description = "주문유형", example = "NORMAL")
    private String ordertype;

    /**
     * OMS마감여부
     */
    @Schema(description = "OMS마감여부", example = "Y")
    private String omsCloseYn;

    /**
     * OMS미마감건수
     */
    @Schema(description = "OMS미마감건수", example = "5")
    private BigDecimal omsFlag;

    /**
     * 진행예정량
     */
    @Schema(description = "진행예정량", example = "100")
    private BigDecimal processqty;

    /**
     * 처리량
     */
    @Schema(description = "처리량", example = "80")
    private BigDecimal workqty;

    /**
     * 검수량
     */
    @Schema(description = "검수량", example = "75")
    private BigDecimal inspectqty;

    /**
     * 확정수량
     */
    @Schema(description = "확정수량", example = "75")
    private BigDecimal confirmqty;

    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String uom;

    /**
     * 수량|예정량
     */
    @Schema(description = "수량|예정량", example = "100")
    private BigDecimal openqty;

    
}
