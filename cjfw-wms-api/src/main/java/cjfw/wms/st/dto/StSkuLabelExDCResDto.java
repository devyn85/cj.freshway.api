package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : baechan (c_bae@cj.net)
 * @date : 2025.09.03
 * @description : 상품이력번호등록(재고생성) Master Response DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.03 baechan (c_bae@cj.net) 생성
 *         </pre>
 */
@Schema(description = "상품이력번호등록(재고생성) Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StSkuLabelExDCResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


    
    /** 체크여부 */
    @Schema(description = "체크여부", nullable = false, example = "0")
    private String checkyn;
    
    /** 등록타입 */
    @Schema(description = "등록타입", nullable = false, example = "0")
    private String regtype;
    
    /** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;
    
    /** 화주코드 */
    @Schema(description = "화주코드", nullable = false, example = "")
    private String storerkey;
    
    /** 창고코드 */
    @Schema(description = "창고코드", nullable = false, example = "")
    private String organize;
    
    /** 창고명 */
    @Schema(description = "창고명", nullable = true, example = "")
    private String organizename;
    
    /** 구역코드 */
    @Schema(description = "구역코드", nullable = false, example = "1000")
    private String area;
    
    /** 전표일자 */
    @Schema(description = "전표일자", nullable = false, example = "")
    private String slipdt;
    
    /** 전표번호 */
    @Schema(description = "전표번호", nullable = false, example = "")
    private String slipno;
    
    /** 전표라인 */
    @Schema(description = "전표라인", nullable = false, example = "")
    private String slipline;
    
    /** 입고전표일자 */
    @Schema(description = "입고전표일자", nullable = false, example = "")
    private String dpSlipdt;
    
    /** 입고전표번호 */
    @Schema(description = "입고전표번호", nullable = false, example = "")
    private String dpSlipno;
    
    /** 입고전표라인 */
    @Schema(description = "입고전표라인", nullable = false, example = "")
    private String dpSlipline;
    
    /** 문서일자 */
    @Schema(description = "문서일자", nullable = false, example = "")
    private String docdt;
    
    /** 문서라인 */
    @Schema(description = "문서라인", nullable = false, example = "")
    private String docline;
    
    /** 상태코드 */
    @Schema(description = "상태코드", nullable = false, example = "")
    private String status;
    
    /** 상태명 */
    @Schema(description = "상태명", nullable = false, example = "")
    private String statusname;
    
    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;
    
    /** 상품명 */
    @Schema(description = "상품명", nullable = false, example = "")
    private String skuname;
    
    /** 출력수량 */
    @Schema(description = "출력수량", nullable = false, example = "1")
    private Integer printedqty;
    
    /** 박스당수량 */
    @Schema(description = "박스당수량", nullable = false, example = "")
    private Integer qtyperbox;
    
    /** 단위 */
    @Schema(description = "단위", nullable = false, example = "")
    private String uom;
    
    /** 주문수량 */
    @Schema(description = "주문수량", nullable = false, example = "")
    private Integer orderqty;
    
    /** 유통기한 */
    @Schema(description = "유통기한", nullable = false, example = "")
    private String lottable01;
    
    /** 유통기한표시 */
    @Schema(description = "유통기한표시", nullable = false, example = "")
    private String durationTerm;
    
    /** 시리얼번호 */
    @Schema(description = "시리얼번호", nullable = false, example = "")
    private String serialno;
    
    /** 변환시리얼번호 */
    @Schema(description = "변환시리얼번호", nullable = false, example = "")
    private String convserialno;
    
    /** 변환로트 */
    @Schema(description = "변환로트", nullable = false, example = "")
    private String convertlot;
    
    /** 시리얼레벨 */
    @Schema(description = "시리얼레벨", nullable = false, example = "")
    private String seriallevel;
    
    /** 시리얼타입 */
    @Schema(description = "시리얼타입", nullable = false, example = "")
    private String serialtype;
    
    /** 제조업체명 */
    @Schema(description = "제조업체명", nullable = false, example = "")
    private String factoryname;
    
    /** 색상설명 */
    @Schema(description = "색상설명", nullable = false, example = "")
    private String colordescr;
    
    /** 원산지 */
    @Schema(description = "원산지", nullable = false, example = "")
    private String placeoforigin;
    
    /** 총중량 */
    @Schema(description = "총중량", nullable = false, example = "")
    private Double grossweight;
    
    /** 순중량 */
    @Schema(description = "순중량", nullable = false, example = "")
    private Double netweight;
    
    /** 입고문서타입 */
    @Schema(description = "입고문서타입", nullable = false, example = "DP")
    private String dpDoctype;
    
    /** 출력여부 */
    @Schema(description = "출력여부", nullable = false, example = "N")
    private String printYn;
    
    /** 바코드 */
    @Schema(description = "바코드", nullable = false, example = "")
    private String barcode;
    
    /** 유통기한타입 */
    @Schema(description = "유통기한타입", nullable = false, example = "")
    private String durationtype;
    
    /** 유통기한 */
    @Schema(description = "유통기한", nullable = false, example = "")
    private Integer duration;
    
    /** 유통기한비율 */
    @Schema(description = "유통기한비율", nullable = false, example = "0")
    private Integer durationRate;
    
    /** 채널 */
    @Schema(description = "채널", nullable = false, example = "1")
    private Integer channel;
    
    /** 마지막유통기한 */
    @Schema(description = "마지막유통기한", nullable = false, example = "")
    private String lastlottable01;
    
    /** 재고유통기한 */
    @Schema(description = "재고유통기한", nullable = false, example = "")
    private String stockLottable01;
    
    /** 계약고객키 */
    @Schema(description = "계약고객키", nullable = false, example = "")
    private String contractcustkey;
    
    /** 고객명 */
    @Schema(description = "고객명", nullable = false, example = "")
    private String wdCustname;
    
    /** 계약타입 */
    @Schema(description = "계약타입", nullable = false, example = "")
    private String contracttype;
    
    /** 도축일자 */
    @Schema(description = "도축일자", nullable = false, example = "")
    private String butcherydt;
    
    /** 유효시작일 */
    @Schema(description = "유효시작일", nullable = false, example = "")
    private String fromvaliddt;
    
    /** 유효종료일 */
    @Schema(description = "유효종료일", nullable = false, example = "")
    private String tovaliddt;
    
    /** 로케이션 */
    @Schema(description = "로케이션", nullable = false, example = "")
    private String loc;
    
    /** PO키 */
    @Schema(description = "PO키", nullable = false, example = "")
    private String pokey;
    
    /** PO라인 */
    @Schema(description = "PO라인", nullable = false, example = "")
    private String poline;
    
    /** 실물여부 */
    @Schema(description = "실물여부", nullable = false, example = "")
    private String realYn;
    
    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;       
    
    /** PROCESSFLAG */
    @Schema(description = "PROCESSFLAG", example = "")
    private String processflag;
    
    /** PROCESSMSG */
    @Schema(description = "PROCESSMSG", example = "")
    private String processmsg;

	/** * 오픈수량 */
	@Schema(description = "* 오픈수량")
	private String openqty;

	/** * 시리얼검사수량 */
	@Schema(description = "* 시리얼검사수량")
	private String serialinspectqty;

	/** * 시리얼스캔중량 */
	@Schema(description = "* 시리얼스캔중량")
	private String serialscanweight;

	/** * 공급업체 */
	@Schema(description = "* 공급업체")
	private String vendor;

	/** * 입고예외여부 */
	@Schema(description = "* 입고예외여부")
	private String excptYn;
	private String excptYnOri;

	/** * 기안번호 */
	@Schema(description = "* 기안번호")
	private String draftNo;

	/** * 최종변경자 */
	@Schema(description = "* 최종변경자")
	private String editwho;

	/** * 최종변경자 이름 */
	@Schema(description = "* 최종변경자 이름")
	private String editwhoName;

	/** * 제조 */
	@Schema(description = "* 제조")
	private String lotManufacture;

	/** * 유통 */
	@Schema(description = "* 유통")
	private String lotExpire;

	/** * 라벨시리얼번호 */
	@Schema(description = "* 라벨시리얼번호")
	private String lblSerialno;

	/** * 라벨중량 */
	@Schema(description = "* 라벨중량")
	private String lblWeight;

	/** * 라벨변환로트 */
	@Schema(description = "* 라벨변환로트")
	private String lblConvertlot;

	/** * 보관유형 */
	@Schema(description = "* 보관유형")
	private String storagetype;

	/** * 보관유형 */
	@Schema(description = "* 보관유형")
	private String storagetypeCode;

	/** * 방수 */
	@Schema(description = "* 방수")
	private String boxperlayer;

	/** * 단수 */
	@Schema(description = "* 단수")
	private String layerperplt;
}
