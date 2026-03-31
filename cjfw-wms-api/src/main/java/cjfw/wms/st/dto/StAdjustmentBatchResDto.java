package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.09.11
 * @description : 외부비축재고조정 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "일괄재고 조정 response dto")
public class StAdjustmentBatchResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/**물류센터*/
	@Schema(description = "물류센터")
	private String dccode;

	/**고객사*/
	@Schema(description = "고객사")
	private String storerkey;

	/**창고*/
	@Schema(description = "창고")
	private String organize;

	/**재고위치 코드*/
	@Schema(description = "재고위치 코드")
	private String stocktype;

	/**재고위치 명칭*/
	@Schema(description = "재고위치 명칭")
	private String stocktypedesc;

	/**재고속성 코드*/
	@Schema(description = "재고속성 코드")
	private String stockgrade;

	/**재고속성 명칭*/
	@Schema(description = "재고속성 명칭")
	private String stockgradedesc;

	/**로케이션*/
	@Schema(description = "로케이션")
	private String loc;

	/**상품코드*/
	@Schema(description = "상품코드")
	private String sku;

	/**상품명칭*/
	@Schema(description = "상품명칭")
	private String skuname;

	/**단위*/
	@Schema(description = "단위")
	private String uom;

	/**현재고수량*/
	@Schema(description = "현재고수량")
	private BigDecimal qty;

	/**재고할당수량*/
	@Schema(description = "재고할당수량")
	private BigDecimal qtyallocated;

	/**피킹재고*/
	@Schema(description = "피킹재고")
	private BigDecimal qtypicked;

	/**shotageQty*/
	@Schema(description = "shotageQty")
	private BigDecimal shotageQty;

	/**처리수량*/
	@Schema(description = "처리수량")
	private BigDecimal tranqty;

	/**발생사유*/
	@Schema(description = "발생사유")
	private String reasoncode;

	/**유통기한임박여부*/
	@Schema(description = "유통기한임박여부")
	private String neardurationyn;

	/**기준일(유통,제조)*/
	@Schema(description = "기준일(유통,제조)")
	private String lottable01;

	/**유통기간(잔여/전체)*/
	@Schema(description = "유통기간(잔여/전체)")
	private String durationTerm;

	/**이력번호*/
	@Schema(description = "이력번호")
	private String serialno;

	/**저장조건*/
	@Schema(description = "저장조건")
	private String storagetype;

	/**로트*/
	@Schema(description = "로트")
	private String lot;

	/**재고ID*/
	@Schema(description = "재고ID")
	private String stockid;

	/**유통기한*/
	@Schema(description = "유통기한")
	private String duration;

	/**유통기한관리방법*/
	@Schema(description = "유통기한관리방법")
	private String durationtype;

	/**기타1*/
	@Schema(description = "기타1")
	private BigDecimal etcqty1;

	/**기타2*/
	@Schema(description = "기타2")
	private BigDecimal etcqty2;

	/**작업구역*/
	@Schema(description = "작업구역")
	private String area;

	/**도축장*/
	@Schema(description = "도축장")
	private String factoryname;

	/**B/L 번호*/
	@Schema(description = "B/L 번호")
	private String convserialno;

	/**도축일자*/
	@Schema(description = "도축일자")
	private String butcherydt;

	/**계약업체*/
	@Schema(description = "계약업체")
	private String contractcompany;

	/**계약업체명*/
	@Schema(description = "계약업체명")
	private String contractcompanyname;

	/**유효일자(FROM)*/
	@Schema(description = "유효일자(FROM)")
	private String fromvaliddt;

	/**유효일자(TO)*/
	@Schema(description = "유효일자(TO)")
	private String tovaliddt;

	/**pokey*/
	@Schema(description = "pokey")
	private String pokey;

	/**poline*/
	@Schema(description = "poline")
	private String poline;

	/**계약유형*/
	@Schema(description = "계약유형")
	private String contracttype;

	/**바코드*/
	@Schema(description = "바코드")
	private String barcode;

	/**평균중량*/
	@Schema(description = "평균중량")
	private BigDecimal avgweight;

	/**환산박스*/
	@Schema(description = "환산박스")
	private BigDecimal calbox;

	/**실박스예정*/
	@Schema(description = "실박스예정")
	private BigDecimal realorderbox;

	/**실박스확정*/
	@Schema(description = "실박스확정")
	private BigDecimal realcfmbox;

	/**작업박스수량*/
	@Schema(description = "작업박스수량")
	private BigDecimal tranbox;

	/**boxflag*/
	@Schema(description = "boxflag")
	private String boxflag;

	/**등록일시*/
	@Schema(description = "등록일시")
	private String adddate;

	/**등록자*/
	@Schema(description = "등록자")
	private String addwho;

	/**realYn*/
	@Schema(description = "realYn")
	private String realYn;

	/**PROCESSFLAG*/
	@Schema(description = "PROCESSFLAG")
	private String processflag;

	/**PROCESSMSG*/
	@Schema(description = "PROCESSMSG")
	private String processmsg;

	/** 제조일자 */
	@Schema(description = "제조일자")
	private String manufacturedt;

	/** 소비일자 */
	@Schema(description = "소비일자")
	private String expiredt;

}
