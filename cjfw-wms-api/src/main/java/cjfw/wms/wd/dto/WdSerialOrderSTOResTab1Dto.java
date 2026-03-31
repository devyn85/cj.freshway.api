package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.29 
 * @description : 이력STO출고처리-STO생성 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.29 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력STO출고처리-STO생성 목록 결과")
public class WdSerialOrderSTOResTab1Dto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-09-29")
    private String wdSlipdt;
    /**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-09-29")
    private String slipdt;
    /**
     * 공급센터-물류센터
     */
    @Schema(description = "공급센터-물류센터", example = "DC01")
    private String fromDccode;
    /**
     * 공급센터-물류센터명
     */
    @Schema(description = "공급센터-물류센터명", example = "CJ대한통운")
    private String fromDcname;
    /**
     * 공급받는센터-물류센터
     */
    @Schema(description = "공급받는센터-물류센터", example = "DC02")
    private String toDccode;
    /**
     * 공급받는센터-물류센터명
     */
    @Schema(description = "공급받는센터-물류센터명", example = "CJ프레시웨이")
    private String toDcname;
    /**
     * 고객-관리처코드
     */
    @Schema(description = "고객-관리처코드", example = "CUST001")
    private String toCustkey;
    /**
     * 고객-관리처명
     */
    @Schema(description = "고객-관리처명", example = "CJ제일제당")
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
     * 수량
     */
    @Schema(description = "수량", example = "100")
    private BigDecimal orderqty;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "BOX")
    private String uom;
    /**
     * storerkey
     */
    @Schema(description = "storerkey", example = "STORER01")
    private String storerkey;
    /**
     * serialkey
     */
    @Schema(description = "serialkey", example = "SERIAL01")
    private String serialkey;
}
