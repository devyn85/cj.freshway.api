package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.30 
 * @description : 피킹취소처리 목록 요청 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.30 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "피킹취소처리 목록 요청 결과") 
public class WdPickingCancelDetailResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	
	@Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    @Schema(description = "상품명칭", example = "신라면")
    private String skuname;
    @Schema(description = "플랜트", example = "[P001]본사")
    private String plantDescr;
    @Schema(description = "분배량", example = "100")
    private BigDecimal processqty;
    @Schema(description = "피킹량", example = "90")
    private BigDecimal workqty;
    @Schema(description = "출고검수량", example = "85")
    private BigDecimal inspectqty;
    @Schema(description = "확정수량", example = "80")
    private BigDecimal confirmqty;
    @Schema(description = "피킹취소량", example = "0")
    private BigDecimal cancelqty;
    @Schema(description = "단위", example = "EA")
    private String uom;
    @Schema(description = "피킹LOC", example = "A-01-01")
    private String loc;
    @Schema(description = "제조일자", example = "20241231")
    private String manufacturedt;
    @Schema(description = "소비일자", example = "20241231")
    private String expiredt;
    @Schema(description = "준일(소비,제조)", example = "20241231")
    private String lottable01;
    @Schema(description = "소비기간", example = "365")
    private int duration;
    @Schema(description = "취소적재LOC", example = "C-01-01")
    private String cancelloc;
    @Schema(description = "피킹작업자", example = "홍길동")
    private String picker;
    @Schema(description = "DC코드", example = "DC001")
    private String dccode;
    @Schema(description = "STORERKEY", example = "STR001")
    private String storerkey;
    @Schema(description = "ORGANIZE", example = "ORG001")
    private String organize;
    @Schema(description = "AREA", example = "AREA001")
    private String area;
    @Schema(description = "CUSTKEY", example = "CUST001")
    private String custkey;
    @Schema(description = "SLIPDT", example = "2025-06-30")
    private String slipdt;
    @Schema(description = "SLIPNO", example = "SLIP001")
    private String slipno;
    @Schema(description = "SLIPLINE", example = "1")
    private int slipline;
    @Schema(description = "DOCDT", example = "2025-06-29")
    private String docdt;
    @Schema(description = "DOCNO", example = "DOC001")
    private String docno;
    @Schema(description = "DOCLINE", example = "1")
    private String docline;
    @Schema(description = "SERIALKEY", example = "SER001")
    private String serialkey;
    @Schema(description = "TASKKEY", example = "TASK001")
    private String taskkey;
    @Schema(description = "LOT", example = "LOT001")
    private String lot;
    @Schema(description = "STOCKID", example = "STOCK001")
    private String stockid;
    @Schema(description = "STOCKGRADE", example = "A")
    private String stockgrade;
    @Schema(description = "TASKTYPE", example = "INPLAN")
    private String tasktype;
    @Schema(description = "ORDERQTY", example = "100")
    private BigDecimal orderqty;
    @Schema(description = "PLANT", example = "PLANT001")
    private String plant;
	
	
}
