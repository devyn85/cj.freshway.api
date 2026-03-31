package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.29 
 * @description : 이력STO출고처리-출고대상 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.29 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력STO출고처리-출고대상 상세 결과")
public class WdSerialOrderSTOResTab2WdDto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	 /**
     * 광역주문번호
     */
    @Schema(description = "광역주문번호", example = "1234567890")
    private String docnoSto;
    /**
     * 품목번호
     */
    @Schema(description = "품목번호", example = "1")
    private String doclineSto;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "백설 하얀설탕")
    private String skuname;
    /**
     * 주문수량
     */
    @Schema(description = "주문수량", example = "100")
    private BigDecimal orderqty;
    /**
     * 결품수량
     */
    @Schema(description = "결품수량", example = "10")
    private BigDecimal cancelqty;
    /**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "CUST001")
    private String toCustkey;
    /**
     * 관리처명
     */
    @Schema(description = "관리처명", example = "CJ제일제당")
    private String toCustname;
    /**
     * 주문번호
     */
    @Schema(description = "주문번호", example = "1234567890")
    private String docno;
    /**
     * 품목번호
     */
    @Schema(description = "품목번호", example = "1")
    private String docline;
    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "출고확정")
    private String status;
    /**
     * 피킹량
     */
    @Schema(description = "피킹량", example = "90")
    private BigDecimal pickedqty;
    /**
     * 확정수량
     */
    @Schema(description = "확정수량", example = "90")
    private BigDecimal confirmqty;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "BOX")
    private String uom;
    /**
     * 작업수량
     */
    @Schema(description = "작업수량", example = "5")
    private BigDecimal tranqty;
    /**
     * 이력번호
     */
    @Schema(description = "이력번호", example = "SN12345")
    private String serialno;
    /**
     * 객체식별/소비이력
     */
    @Schema(description = "객체식별/소비이력", example = "SID001")
    private String stockid;
    /**
     * tasksystem
     */
    @Schema(description = "tasksystem", example = "CONFIRM")
    private String tasksystem;
    /**
     * slipdt
     */
    @Schema(description = "slipdt", example = "2025-09-29")
    private String slipdt;
    /**
     * serialkey
     */
    @Schema(description = "serialkey", example = "SERIALKEY01")
    private String serialkey;
    /**
     * groupnum
     */
    @Schema(description = "groupnum", example = "1")
    private String groupnum;
}
