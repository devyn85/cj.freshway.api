package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.07.02
 * @description : OO 기능을 구현한 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.02 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축재고폐기요청처리 Response DTO")
public class StAdjustmentRequestProcessResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


	/**물류센터*/
    @Schema(description = "물류센터")
    private String dccode;

    /**회사*/
    @Schema(description = "회사")
    private String storerkey;

    /**창고*/
    @Schema(description = "창고")
    private String organize;

    /**작업구역*/
    @Schema(description = "작업구역")
    private String area;

    /**문서유형*/
    @Schema(description = "문서유형")
    private String doctype;

    /**문서일자*/
    @Schema(description = "문서일자")
    private String docdt;

    /**문서번호*/
    @Schema(description = "문서번호")
    private String docno;

    /**문서항번*/
    @Schema(description = "문서항번")
    private String docline;

    /**전표일자*/
    @Schema(description = "전표일자")
    private String slipdt;

    /**전표번호*/
    @Schema(description = "전표번호")
    private String slipno;

    /**전표항번*/
    @Schema(description = "전표항번")
    private String slipline;

    /**주문유형*/
    @Schema(description = "주문유형")
    private String ordertype;

    /**입출고유형*/
    @Schema(description = "입출고유형")
    private String iotype;

    /**전표유형*/
    @Schema(description = "전표유형")
    private String sliptype;

    /**재고위치명*/
    @Schema(description = "재고위치명")
    private String stocktypenm;

    /**재고위치*/
    @Schema(description = "재고위치")
    private String stocktype;

    /**재고속성*/
    @Schema(description = "재고속성")
    private String stockgrade;

    /**재고속성명*/
    @Schema(description = "재고속성명")
    private String stockgradename;

    /**피킹존*/
    @Schema(description = "피킹존")
    private String zone;

    /**로케이션*/
    @Schema(description = "로케이션")
    private String loc;

    /**상품코드*/
    @Schema(description = "상품코드")
    private String sku;

    /**상품명칭*/
    @Schema(description = "상품명칭")
    private String skuname;

    /**로트*/
    @Schema(description = "로트")
    private String lot;

    /**단위*/
    @Schema(description = "단위")
    private String uom;

    /**작업수량*/
    @Schema(description = "작업수량")
    private BigDecimal tranqty;

    /**감모유형*/
    @Schema(description = "감모유형")
    private String decreasetype;

    /**감모유형명*/
    @Schema(description = "감모유형명")
    private String decreasetypename;

    /**사유코드*/
    @Schema(description = "사유코드")
    private String reasoncode;

    /**사유코드명*/
    @Schema(description = "사유코드명")
    private String reasoncodename;

    /**투입유형*/
    @Schema(description = "투입유형")
    private String imputetype;

    /**투입유형명*/
    @Schema(description = "투입유형명")
    private String imputetypename;

    /**처리상태*/
    @Schema(description = "처리상태")
    private String processmain;

    /**귀속부서*/
    @Schema(description = "귀속부서")
    private String costcd;

    /**귀속부서명*/
    @Schema(description = "귀속부서명")
    private String costcdname;

    /**유통기한임박여부*/
    @Schema(description = "유통기한임박여부")
    private String neardurationyn;

    /**기준일(유통,제조)*/
    @Schema(description = "기준일(유통,제조)")
    private String lottable01;

    /**유통기간(잔여/전체)*/
    @Schema(description = "유통기간(잔여/전체)")
    private String duration_term;

    /**개체식별/유통이력*/
    @Schema(description = "개체식별/유통이력")
    private String stockid;

    /**이력번호*/
    @Schema(description = "이력번호")
    private String serialno;

    /**유통이력번호*/
    @Schema(description = "유통이력번호")
    private String convserialno;

    /**등급*/
    @Schema(description = "등급")
    private String seriallevel;

    /**규격*/
    @Schema(description = "규격")
    private String serialtype;

    /**도축장*/
    @Schema(description = "도축장")
    private String factoryname;

    /**부위명*/
    @Schema(description = "부위명")
    private String colordescr;

    /**원산지*/
    @Schema(description = "원산지")
    private String placeoforigin;

    /**유통기간*/
    @Schema(description = "유통기간")
    private String duration;

    /**유통기한관리방법*/
    @Schema(description = "유통기한관리방법")
    private String durationtype;

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

    /**계약유형*/
    @Schema(description = "계약유형")
    private String contracttype;

    /**바코드*/
    @Schema(description = "바코드")
    private String barcode;

    /**결재진행상태*/
    @Schema(description = "결재진행상태")
    private String approvalstatus;

    /**결재진행상태명*/
    @Schema(description = "결재진행상태명")
    private String approvalstatusname;

    /**결재요청일시*/
    @Schema(description = "결재요청일시")
    private String approvaldate;

    /**전자문서번호*/
    @Schema(description = "전자문서번호")
    private String approvalreqno;

    /**결재번호*/
    @Schema(description = "결재번호")
    private String approvalno;

    /**결재일자*/
    @Schema(description = "결재일자")
    private String approvaldt;

    /**확인플래그*/
    @Schema(description = "확인플래그")
    private String chkflag;

    /**처리상태명*/
    @Schema(description = "처리상태명")
    private String status_aj;

    /**평균중량*/
    @Schema(description = "평균중량")
    private String avgweight;

    /**환산박스*/
    @Schema(description = "환산박스")
    private String calbox;

    /**실박스예정*/
    @Schema(description = "실박스예정")
    private String realorderbox;

    /**실박스확정*/
    @Schema(description = "실박스확정")
    private String realcfmbox;

    /**작업박스*/
    @Schema(description = "작업박스")
    private String tranbox;

    /**박스여부*/
    @Schema(description = "박스여부")
    private String boxflag;

    /**등록일자*/
    @Schema(description = "등록일자")
    private String adddate;

    /**생성인*/
    @Schema(description = "생성인")
    private String addwho;

    /**거래처*/
    @Schema(description = "거래처")
    private String custkey;

    /**거래처명*/
    @Schema(description = "거래처명")
    private String custname;

    /**비고*/
    @Schema(description = "비고")
    private String reference01;

	}