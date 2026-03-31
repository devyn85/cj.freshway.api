package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.24 
 * @description : 지정취소 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "지정취소 상세 결과")
public class WdAllocationCancelDetailSubResDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	@Schema(description = "물류센터", example = "DC001")
    private String dccode;
    @Schema(description = "창고", example = "WH001")
    private String organize;
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    @Schema(description = "상품명칭", example = "상품A")
    private String skuname;
    @Schema(description = "분배장소", example = "LOC001")
    private String loc;
    @Schema(description = "현재고수량", example = "100")
    private BigDecimal qty;
    @Schema(description = "가용재고수량", example = "50")
    private BigDecimal openqty;
    @Schema(description = "분배량", example = "30")
    private BigDecimal processqty;
    @Schema(description = "피킹재고수량", example = "20")
    private BigDecimal workqty;
    @Schema(description = "취소량", example = "0")
    private BigDecimal cancelqty;
    @Schema(description = "단위", example = "EA")
    private String uom;
    @Schema(description = "재고위치", example = "정상")
    private String stocktype;
    @Schema(description = "기준일(소비,제조)", example = "2024-12-31")
    private String lottable01;
    @Schema(description = "이력번호", example = "SN001")
    private String serialno;
    @Schema(description = "박스바코드", example = "BOX001")
    private String boxbarcode;
    @Schema(description = "SERIALKEY", example = "SERIALKEY001")
    private String serialkey;
    @Schema(description = "TASKKEY", example = "TASKKEY001")
    private String taskkey;
    @Schema(description = "STORERKEY", example = "STORERKEY001")
    private String storerkey;
    @Schema(description = "AREA", example = "AREA001")
    private String area;
    @Schema(description = "CUSTKEY", example = "CUSTKEY001")
    private String custkey;
    @Schema(description = "DOCDT", example = "2025-01-01")
    private String docdt;
    @Schema(description = "DOCNO", example = "DOCNO001")
    private String docno;
    @Schema(description = "DOCLINE", example = "1")
    private String docline;
    @Schema(description = "SLIPDT", example = "2025-01-01")
    private String slipdt;
    @Schema(description = "SLIPNO", example = "SLIPNO001")
    private String slipno;
    @Schema(description = "SLIPLINE", example = "1")
    private String slipline;
    @Schema(description = "LOT", example = "LOT001")
    private String lot;
    @Schema(description = "MC", example = "01")
    private String mc;
    @Schema(description = "STOCKID", example = "STOCKID001")
    private String stockid;
    @Schema(description = "STOCKGRADE", example = "A")
    private String stockgrade;
    @Schema(description = "BOXTYPE", example = "CARTON")
    private String boxtype;
    @Schema(description = "SERIALYN", example = "Y")
    private String serialyn;
}
