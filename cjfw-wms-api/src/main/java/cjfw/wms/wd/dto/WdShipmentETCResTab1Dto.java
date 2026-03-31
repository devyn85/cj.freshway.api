package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 고혜미
 * @date : 2025.10.15 
 * @description : 기타출고 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 고혜미 생성 </pre>
 */
@Data
@Schema(description = "기타출고 목록 요청") 
public class WdShipmentETCResTab1Dto extends CommonProcedureDto {
	
	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 창고 */
	@Schema(description = "창고")
	private String organize;

	/** 재고위치 */
	@Schema(description = "재고위치")
	private String stocktype;
	
	/** 재고위치명 */
	@Schema(description = "재고위치명")
	private String stocktypenm;	

	/** 재고속성 */
	@Schema(description = "재고속성")
	private String stockgrade;
	
	/** 재고속성 */
	@Schema(description = "재고속성")
	private String stockgradename;

	/** 피킹존 */
	@Schema(description = "피킹존")
	private String zone;

	/** 로케이션 */
	@Schema(description = "로케이션")
	private String loc;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 상품명칭 */
	@Schema(description = "상품명칭")
	private String skuname;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 현재고수량 */
	@Schema(description = "현재고수량")
	private BigDecimal qty;

	/** 가용재고수량 */
	@Schema(description = "가용재고수량")
	private BigDecimal openqty;

	/** 재고할당수량 */
	@Schema(description = "재고할당수량")
	private BigDecimal qtyallocated;

	/** 피킹재고 */
	@Schema(description = "피킹재고")
	private BigDecimal qtypicked;

	/** 처리수량 */
	@Schema(description = "처리수량")
	private BigDecimal etcqty;
	
	/** 금액 */
	@Schema(description = "금액")
	private BigDecimal disposeAmount;

	/** 처리유형 */
	@Schema(description = "처리유형")
	private String potype;

	/** 처리사유 */
	@Schema(description = "처리사유")
	private String reasoncode;

	/** 세부사유 */
	@Schema(description = "세부사유")
	private String reasonmsg;

	/** 물류귀책배부 */
	@Schema(description = "물류귀책배부")
	private String other05;

	/** 귀속부서 */
	@Schema(description = "귀속부서")
	private String costcd;

	/** 귀속부서명 */
	@Schema(description = "귀속부서명")
	private String costcdname;

	/** 거래처 */
	@Schema(description = "거래처")
	private String custkey;

	/** 거래처명 */
	@Schema(description = "거래처명")
	private String custname;

	/** 기타 */
	@Schema(description = "기타")
	private String other03;

	/** 유통기한임박여부 */
	@Schema(description = "유통기한임박여부")
	private String neardurationyn;

	/** 기준일(소비,제조) */
	@Schema(description = "기준일(소비,제조)")
	private String lottable01;

	/** 소비기간(잔여/전체) */
	@Schema(description = "소비기간(잔여/전체)")
	private String durationTerm;

	/** 이력번호 */
	@Schema(description = "이력번호")
	private String serialno;

	/** 바코드 */
	@Schema(description = "바코드")
	private String barcode;

	/** B/L번호 */
	@Schema(description = "B/L번호")
	private String convserialno;

	/** 도축일자 */
	@Schema(description = "도축일자")
	private String butcherydt;

	/** 도축장 */
	@Schema(description = "도축장")
	private String factoryname;

	/** 계약유형 */
	@Schema(description = "계약유형")
	private String contracttype;

	/** 계약업체 */
	@Schema(description = "계약업체")
	private String contractcompany;

	/** 계약업체명 */
	@Schema(description = "계약업체명")
	private String contractcompanyname;

	/** 유효일자(FROM) */
	@Schema(description = "유효일자(FROM)")
	private String fromvaliddt;

	/** 유효일자(TO) */
	@Schema(description = "유효일자(TO)")
	private String tovaliddt;

	/** 로트 */
	@Schema(description = "로트")
	private String lot;

	/** 주문유형 */
	@Schema(description = "주문유형")
	private String ordertype;

	/** 소비기간 */
	@Schema(description = "소비기간")
	private String duration;

	/** 소비기한관리방법 */
	@Schema(description = "소비기한관리방법")
	private String durationtype;

	/** 개체식별/소비이력 */
	@Schema(description = "개체식별/소비이력")
	private String stockid;

	/** 창고마스터키 */
	@Schema(description = "창고마스터키")
	private String storerkey;

	/** 지역 */
	@Schema(description = "지역")
	private String area;

	/** 처리주체 */
	@Schema(description = "처리주체")
	private String other01;

	/** 등급 */
	@Schema(description = "등급")
	private String seriallevel;

	/** 규격 */
	@Schema(description = "규격")
	private String serialtype;

	/** 부위명 */
	@Schema(description = "부위명")
	private String colordescr;

	/** 원산지 */
	@Schema(description = "원산지")
	private String placeoforigin;
	
	/** 매각등록일 */
	@Schema(description = "매각등록일")
	private String disposeDate;
	
	/** 매각등록일 */
	@Schema(description = "매각등록일")
	private String wdDate;
	
	/** 매각등록일 */
	@Schema(description = "매각등록일")
	private String listNo;
	
	/** 매각등록일 */
	@Schema(description = "매각등록일")
	private String iotype;
	
	/** 매각등록일 */
	@Schema(description = "매각등록일")
	private String flag;

	
}
