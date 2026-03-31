package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.17 
 * @description : 이력상품입고현황 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.17 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력상품입고현황 상세 결과")
public class DpInplanSNDetailResDto {
	@Schema(description = "품목번호", example = "1")
    private String docline;
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    @Schema(description = "상품명칭", example = "생수")
    private String skuname;
    @Schema(description = "원산국", example = "[KR]대한민국")
    private String countryoforigin;
    @Schema(description = "플랜트", example = "[P001]포천공장")
    private String plantDescr;
    @Schema(description = "저장유무", example = "저장")
    private String channelname;
    @Schema(description = "저장조건", example = "상온")
    private String storagetypename;
    @Schema(description = "박스입수", example = "20")
    private String qtyperbox;
    @Schema(description = "구매단위", example = "EA")
    private String uom;
    @Schema(description = "구매수량", example = "100.0")
    private String orderqty;
    @Schema(description = "발주수량", example = "90.0")
    private String purchaseQty;
    @Schema(description = "입고예정량", example = "100.0")
    private String inplanqty;
    @Schema(description = "입고예정확정량", example = "80.0")
    private String openqty;
    @Schema(description = "입고검수량", example = "70.0")
    private String inspectqty;
    @Schema(description = "입고확정량", example = "70.0")
    private String confirmqty;
    @Schema(description = "결품수량", example = "0")
    private String shortageqty;
    @Schema(description = "입고중량", example = "150.5")
    private String weight;
    @Schema(description = "진행상태", example = "완료")
    private String statusName;
    @Schema(description = "기준일(소비,제조)", example = "20240617")
    private String lottable01;
    @Schema(description = "소비기간(잔여/전체)", example = "90/180")
    private String durationTerm;
    @Schema(description = "이력번호", example = "SN20240001")
    private String serialno;
    @Schema(description = "바코드", example = "BC123456789")
    private String stockid;
    @Schema(description = "B/L 번호", example = "BL-001")
    private String convserialno;
    @Schema(description = "도축일자", example = "20240615")
    private String butcherydt;
    @Schema(description = "도축장", example = "강원도축장")
    private String factoryname;
    @Schema(description = "계약유형", example = "정기계약")
    private String contracttype;
    @Schema(description = "계약업체", example = "CUST002")
    private String contractcompany;
    @Schema(description = "계약업체명", example = "영업유통")
    private String contractcompanyname;
    @Schema(description = "FROM", example = "20240101")
    private String fromvalidDt;
    @Schema(description = "TO", example = "20241231")
    private String tovalidDt;
    @Schema(description = "스캔예정량", example = "100.0")
    private String serialorderqty;
    @Schema(description = "스캔량", example = "70.0")
    private String serialinspectqty;
    @Schema(description = "스캔중량", example = "150.5")
    private String serialscanweight;
    @Schema(description = "", example = "1")
    private String seriallevel;
    @Schema(description = "", example = "NORMAL")
    private String serialtype;
    @Schema(description = "", example = "RED")
    private String colordescr;
    @Schema(description = "", example = "한국")
    private String placeoforigin;
    @Schema(description = "", example = "DC001")
    private String dccode;
    @Schema(description = "", example = "STR001")
    private String storerkey;
    @Schema(description = "", example = "ORG001")
    private String organize;
    @Schema(description = "", example = "AREA001")
    private String area;
    @Schema(description = "", example = "2025-06-17")
    private String docdt;
    @Schema(description = "", example = "DOC001")
    private String docno;
    @Schema(description = "", example = "PO")
    private String doctype;
    @Schema(description = "", example = "180")
    private String duration;
    @Schema(description = "", example = "DAY")
    private String durationtype;
    @Schema(description = "", example = "PLANT001")
    private String plant;
    @Schema(description = "", example = "CHANNEL001")
    private String channel;
    @Schema(description = "", example = "STORAGE001")
    private String storagetype;
    @Schema(description = "", example = "COMPLETED")
    private String status;

    /** * 제조 */
    @Schema(description = "* 제조")
    private String lotManufacture;

    /** * 유통 */
    @Schema(description = "* 유통")
    private String lotExpire;
    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
