package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.10 
 * @description : 이력상품출고현황 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.10 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력상품출고현황 상세 결과")
public class WdInplanSNResDetailDto {	

		@Schema(description = "품목번호", example = "1")
	    private String docline;

	    @Schema(description = "상품코드", example = "ITEM001")
	    private String sku;

	    @Schema(description = "상품명칭", example = "아메리카노")
	    private String skuname;

	    @Schema(description = "원산국", example = "[US]미국")
	    private String countryoforigin;

	    @Schema(description = "배송수단", example = "택배")
	    private String deliverytype;

	    @Schema(description = "이력관리대상", example = "Y")
	    private String serialyn;

	    @Schema(description = "비정량여부", example = "Y")
	    private String line01;

	    @Schema(description = "사전주문조정의뢰여부", example = "Y")
	    private String beforeshortageplanyn;

	    @Schema(description = "플랜트", example = "[P1]주사업장")
	    private String plantDescr; // _가 있으므로 카멜케이스

	    @Schema(description = "저장유무", example = "저장")
	    private String channel;

	    @Schema(description = "저장조건", example = "상온")
	    private String storagetype;

	    @Schema(description = "주문수량", example = "100.00")
	    private BigDecimal orderqty;

	    @Schema(description = "분배량", example = "90.00")
	    private BigDecimal processqty;

	    @Schema(description = "피킹량", example = "80.00")
	    private BigDecimal workqty;

	    @Schema(description = "출고검수량", example = "70.00")
	    private BigDecimal inspectqty;

	    @Schema(description = "출고수량", example = "70.00")
	    private BigDecimal confirmqty;

	    @Schema(description = "판매단위", example = "EA")
	    private String uom;

	    @Schema(description = "출고중량", example = "150.50")
	    private BigDecimal confirmweight;

	    @Schema(description = "진행상태", example = "출고완료")
	    private String status;

	    @Schema(description = "기준일(소비,제조)", example = "20240101")
	    private String lottable01;
	    
	    @Schema(description = "제조일자", example = "20241231")
	    private String manufacturedt;
	    
	    @Schema(description = "소비일자", example = "20241231")
	    private String expiredt;

	    @Schema(description = "소비기간(잔여/전체)", example = "90/180")
	    private String durationTerm; // _가 있으므로 카멜케이스

	    @Schema(description = "이력번호", example = "SN123456789")
	    private String serialno;

	    @Schema(description = "바코드", example = "BARCODE123")
	    private String barcode;

	    @Schema(description = "B/L번호", example = "BL98765")
	    private String convserialno;

	    @Schema(description = "도축일자", example = "20240101")
	    private String butcherydt;

	    @Schema(description = "도축장", example = "강원도축장")
	    private String factoryname;

	    @Schema(description = "계약유형", example = "위탁")
	    private String contracttype;

	    @Schema(description = "계약업체", example = "CONTR001")
	    private String contractcompany;

	    @Schema(description = "계약업체명", example = "ABC식품")
	    private String contractcompanyname;

	    @Schema(description = "유효일자(FROM)", example = "20240101")
	    private String fromvaliddt;

	    @Schema(description = "유효일자(TO)", example = "20241231")
	    private String tovaliddt;

	    @Schema(description = "스캔예정량", example = "10.00")
	    private BigDecimal serialorderqty;

	    @Schema(description = "스캔량", example = "10.00")
	    private BigDecimal serialinspectqty;

	    @Schema(description = "스캔중량", example = "5.00")
	    private BigDecimal serialscanweight;

	    @Schema(description = "물류센터", example = "DC001")
	    private String dccode;

	    @Schema(description = "화주", example = "STORER01")
	    private String storerkey;

	    @Schema(description = "창고", example = "WH001")
	    private String organize;

	    @Schema(description = "AREA", example = "AREA01")
	    private String area;

	    @Schema(description = "문서일자", example = "20250610")
	    private String docdt;

	    @Schema(description = "문서번호", example = "DOC001")
	    private String docno;

	    @Schema(description = "문서유형", example = "OUT")
	    private String doctype;

	    @Schema(description = "전표일자", example = "20250610")
	    private String slipdt;

	    @Schema(description = "전표번호", example = "SLIP001")
	    private String slipno;

	    @Schema(description = "전표유형", example = "SALES")
	    private String sliptype;

	    @Schema(description = "전표라인", example = "1")
	    private Integer slipline;

	    @Schema(description = "이력레벨", example = "1")
	    private String seriallevel;

	    @Schema(description = "이력유형", example = "TYPE_A")
	    private String serialtype;

	    @Schema(description = "원산지", example = "서울")
	    private String placeoforigin;

	    @Schema(description = "플랜트", example = "PLANT01")
	    private String plant;

	    @Schema(description = "삭제여부", example = "N")
	    private String delYn; // _가 있으므로 카멜케이스
}
