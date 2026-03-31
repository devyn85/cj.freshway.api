package cjfw.wms.ib.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.10.24
 * @description : 센터별물동량 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "센터별물동량 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbStoWeightResDto extends CommonDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** CHECKYN */
	@Schema(description = "CHECKYN")
	private String checkyn;

	/** flowType */
	@Schema(description = "flowType")
	private String flowType;

	/** YYYYMM */
	@Schema(description = "YYYYMM")
	private String yyyymm;

	/** YYYYMMDD */
	@Schema(description = "YYYYMMDD")
	private String yyyymmdd;

	/** DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/** DCNAME */
	@Schema(description = "DCNAME")
	private String dcname;

	/** FILTERKEY */
	@Schema(description = "FILTERKEY")
	private String filterkey;

	/** SKUNAME */
	@Schema(description = "SKUNAME")
	private String skuname;

	/** TODCNAME */
	@Schema(description = "TODCNAME")
	private String todcname;

	/** CUSTNAME */
	@Schema(description = "CUSTNAME")
	private String custname;

	/** PARTNRNAME */
	@Schema(description = "PARTNRNAME")
	private String partnrname;

	/** PROCESS_YN */
	@Schema(description = "PROCESS_YN")
	private String processYn;

	/** PROCESS_MSG */
	@Schema(description = "PROCESS_MSG")
	private String processMsg;

	/** ADDWHO */
	@Schema(description = "ADDWHO")
	private String addwho;

	/** ADDDATE */
	@Schema(description = "ADDDATE")
	private String adddate;

	/** EDITWHO */
	@Schema(description = "EDITWHO")
	private String editwho;

	/** EDITDATE */
	@Schema(description = "EDITDATE")
	private String editdate;

	/** MASTERKEY */
	@Schema(description = "MASTERKEY")
	private String masterkey;

	/** STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/** DESCRIPTION */
	@Schema(description = "DESCRIPTION")
	private String description;

	/** DEL_YN */
	@Schema(description = "DEL_YN")
	private String delYn;

	/** ORG_YYYYMM */
	@Schema(description = "ORG_YYYYMM")
	private String orgYyyymm;

	/** ORG_DCCODE */
	@Schema(description = "ORG_DCCODE")
	private String orgDccode;

	/** ORG_FILTERKEY */
	@Schema(description = "ORG_FILTERKEY")
	private String orgFilterkey;

	/** TO_CUSTKEY */
	@Schema(description = "TO_CUSTKEY")
	private String toCustkey;

	/** TO_CUSTNAME */
	@Schema(description = "TO_CUSTNAME")
	private String toCustname;

	/** CHANNEL */
	@Schema(description = "CHANNEL")
	private String channel;

	/** CHANNEL_NAME */
	@Schema(description = "CHANNEL_NAME")
	private String channelName;

	/** CONFIRMWEIGHT */
	@Schema(description = "CONFIRMWEIGHT")
	private String confirmweight;

	/** ETC_CONFIRMWEIGHT */
	@Schema(description = "ETC_CONFIRMWEIGHT")
	private String etcConfirmweight;

	/** ETC_CONFIRMWEIGHT_ADD */
	@Schema(description = "ETC_CONFIRMWEIGHT_ADD")
	private String etcConfirmweightAdd;

	/** dcWdConfirmweight */
	@Schema(description = "dcWdConfirmweight")
	private String dcWdConfirmweight;

	/** dcWdJejuConfirmweight */
	@Schema(description = "dcWdJejuConfirmweight")
	private String dcWdJejuConfirmweight;

	/** exCustConfirmweight */
	@Schema(description = "exCustConfirmweight")
	private String exCustConfirmweight;

	/** exSkuConfirmweight */
	@Schema(description = "exSkuConfirmweight")
	private String exSkuConfirmweight;

	/** etcEtcConfirmweight */
	@Schema(description = "etcEtcConfirmweight")
	private String etcSkuEtcConfirmweight;

	/** etcCustGunConfirmweight */
	@Schema(description = "etcCustGunConfirmweight")
	private String etcCustGunConfirmweight;

	/** ETC_CUST_ETC_CONFIRMWEIGHT */
	@Schema(description = "ETC_CUST_ETC_CONFIRMWEIGHT")
	private String etcCustEtcConfirmweight;

	/** ETC_SKU_ICE_CONFIRMWEIGHT */
	@Schema(description = "ETC_SKU_ICE_CONFIRMWEIGHT")
	private String etcSkuIceConfirmweight;

	/** etcPartnerConfirmweight */
	@Schema(description = "etcPartnerConfirmweight")
	private String etcPartnerConfirmweight;

	/** FROM_DCCODE */
	@Schema(description = "FROM_DCCODE")
	private String fromDccode;

	/** TO_DCCODE */
	@Schema(description = "TO_DCCODE")
	private String toDccode;

	/** STORAGETYPE */
	@Schema(description = "STORAGETYPE")
	private String storagetype;

	/** FLOW_QTY */
	@Schema(description = "FLOW_QTY")
	private BigDecimal flowQty;

	/** EX_ITEM_RICE_QTY */
	@Schema(description = "EX_ITEM_RICE_QTY")
	private BigDecimal exItemRiceQty;

	/** EX_ITEM_KIMCHI_QTY */
	@Schema(description = "EX_ITEM_KIMCHI_QTY")
	private BigDecimal exItemKimchiQty;

	/** EX_QTY1 */
	@Schema(description = "EX_QTY1")
	private String exQty1;

	/** EX_QTY2 */
	@Schema(description = "EX_QTY2")
	private String exQty2;

	/** EX_CUST_QTY */
	@Schema(description = "EX_CUST_QTY")
	private BigDecimal exCustQty;

	/** EXPT_CUST_QTY */
	@Schema(description = "EXPT_CUST_QTY")
	private BigDecimal exptCustQty;

	/** EX_ITEM_ICE_QTY */
	@Schema(description = "EX_ITEM_ICE_QTY")
	private BigDecimal exItemIceQty;

	/** EX_PO_CUST_QTY */
	@Schema(description = "EX_PO_CUST_QTY")
	private BigDecimal exPoCustQty;

	/** EXPT_ITEM_QTY */
	@Schema(description = "EXPT_ITEM_QTY")
	private BigDecimal exptItemQty;

	/** exPartnerConfirmweight */
	@Schema(description = "exPartnerConfirmweight")
	private String exPartnerConfirmweight;

	/** price 정산 엑셀용 */
	@Schema(description = "price")
	private String price;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 구역 */
    @Schema(description = "구역")
    private String area;

    /** 문서유형 */
    @Schema(description = "문서유형")
    private String doctype;

    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docdt;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;

    /** 문서라인 */
    @Schema(description = "문서라인")
    private String docline;

    /** 전표일자 */
    @Schema(description = "전표일자")
    private String slipdt;

    /** 전표번호 */
    @Schema(description = "전표번호")
    private String slipno;

    /** 전표라인 */
    @Schema(description = "전표라인")
    private String slipline;

    /** 오더유형 */
    @Schema(description = "오더유형")
    private String ordertype;

    /** 입출유형 */
    @Schema(description = "입출유형")
    private String iotype;

    /** 전표유형 */
    @Schema(description = "전표유형")
    private String sliptype;

    /** 재고유형명 */
    @Schema(description = "재고유형명")
    private String stocktypenm;

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;

    /** 재고등급 */
    @Schema(description = "재고등급")
    private String stockgrade;

    /** 재고등급명 */
    @Schema(description = "재고등급명")
    private String stockgradename;

    /** 존 */
    @Schema(description = "존")
    private String zone;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 로트 */
    @Schema(description = "로트")
    private String lot;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 조정수량 */
    @Schema(description = "조정수량")
    private BigDecimal tranqty;

    /** 폐기유형 */
    @Schema(description = "폐기유형")
    private String disusetype;

    /** 폐기유형명 */
    @Schema(description = "폐기유형명")
    private String disusetypename;

    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;

    /** 사유코드명 */
    @Schema(description = "사유코드명")
    private String reasoncodename;

    /** 귀책 */
    @Schema(description = "귀책")
    private String imputetype;

    /** 귀책명 */
    @Schema(description = "귀책명")
    private String imputetypename;

    /** 물류비귀책여부 */
    @Schema(description = "물류비귀책여부")
    private String processmain;

    /** 귀속부서 */
    @Schema(description = "귀속부서")
    private String costcd;

    /** 귀속부서명 */
    @Schema(description = "귀속부서명")
    private String costcdname;

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String custkey;

    /** 유통기한임박여부 */
    @Schema(description = "유통기한임박여부")
    private String neardurationyn;

    /** 유통기한 */
    @Schema(description = "유통기한")
    private String lottable01;

    /** 유통기간(잔여/전체) */
    @Schema(description = "유통기간(잔여/전체)")
    private String durationTerm;

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockid;

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialno;

    /** B/L번호 */
    @Schema(description = "B/L번호")
    private String convserialno;

    /** 이력레벨 */
    @Schema(description = "이력레벨")
    private String seriallevel;

    /** 이력타입 */
    @Schema(description = "이력타입")
    private String serialtype;

    /** 공장명 */
    @Schema(description = "공장명")
    private String factoryname;

    /** 색상명 */
    @Schema(description = "색상명")
    private String colordescr;

    /** 원산지 */
    @Schema(description = "원산지")
    private String placeoforigin;

    /** 유통기한기간 */
    @Schema(description = "유통기한기간")
    private String duration;

    /** 유통기한구분 */
    @Schema(description = "유통기한구분")
    private String durationtype;

    /** 도축일자 */
    @Schema(description = "도축일자")
    private String butcherydt;

    /** 계약거래처 */
    @Schema(description = "계약거래처")
    private String contractcompany;

    /** 계약거래처명 */
    @Schema(description = "계약거래처명")
    private String contractcompanyname;

    /** 유효일자 */
    @Schema(description = "유효일자")
    private String fromvaliddt;

    /** 유효일자2 */
    @Schema(description = "유효일자2")
    private String tovaliddt;

    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contracttype;

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;

    /** 결재상태 */
    @Schema(description = "결재상태")
    private String approvalstatus;

    /** 결재상태명 */
    @Schema(description = "결재상태명")
    private String approvalstatusname;

    /** 결재요청번호 */
    @Schema(description = "결재요청번호")
    private String approvalreqno;

    /** 결재번호 */
    @Schema(description = "결재번호")
    private String approvalno;

    /** 결재일자 */
    @Schema(description = "결재일자")
    private String approvaldate;

    /** 체크플래그 */
    @Schema(description = "체크플래그")
    private String chkflag;

    /** 상태명 */
    @Schema(description = "상태명")
    private String statusAj;

    /** 평균중량 */
    @Schema(description = "평균중량")
    private String avgweight;

    /** 환산박스 */
    @Schema(description = "환산박스")
    private String calbox;

    /** 실박스예정 */
    @Schema(description = "실박스예정")
    private String realorderbox;

    /** 실박스확정 */
    @Schema(description = "실박스확정")
    private String realcfmbox;

    /** 작업박스 */
    @Schema(description = "작업박스")
    private String tranbox;

    /** 박스처리유무 */
    @Schema(description = "박스처리유무")
    private String boxflag;

    /** 보관료 */
    @Schema(description = "보관료")
    private String reference08;

    /** 보관료확정 */
    @Schema(description = "보관료확정")
    private String reference09;

    /** 운송료 */
    @Schema(description = "운송료")
    private String reference10;

    /** 기타(센터별) */
    @Schema(description = "기타(센터별)")
    private String reference01;

    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;

    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;

    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert;

    /** 요청월 */
    @Schema(description = "요청월")
    private String requestMm;

    /** 폐기비용(총중량*420) */
    @Schema(description = "폐기비용(총중량*420)")
    private BigDecimal disuseCost;

    /** 단위중량 */
    @Schema(description = "단위중량", example = "")
    private BigDecimal netweight;

    /** SAP 단가 */
    @Schema(description = "SAP 단가", example = "")
    private BigDecimal purchaseprice;

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


	/** reasonmsg */
	@Schema(description = "reasonmsg")
	private String reasonmsg;

	/** decreasetype */
	@Schema(description = "decreasetype")
	private String decreasetype;

	/** poline */
	@Schema(description = "poline")
	private String poline;

	/** other03 */
	@Schema(description = "other03")
	private String other03;

	/** 팩당 낱개수 */
	@Schema(description = "팩당 낱개수")
	private BigDecimal qtyperbox;

	/** 비고(사유) */
	@Schema(description = "비고(사유)")
	private String other05;

	/** processflag */
	@Schema(description = "processflag")
	private String processflag;

	/** 감모유형 */
	@Schema(description = "감모유형")
	private String decreasetypename;

	/** 결재상신일자 */
	@Schema(description = "결재상신일자")
	private String approvaldt;

    /** 저장조건 */
    @Schema(description = "저장조건", example = "냉장")
    private String storagetypeName;
}
