package cjfw.wms.wd.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.12.26
 * @description : 택배송장발행(온라인) Entity
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.26 JiHoPark  생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "택배송장발행(온라인) Entity")
public class WdKxDeliveryInvoiceEmpEntity extends CommonProcedureDto {

	/** 주문항번 */
    @Schema(description = "주문항번")
    private String docline;

    /** EMP주문번호 */
    @Schema(description = "EMP주문번호")
    private String empDocno;

    /** EMP고객코드 */
    @Schema(description = "EMP고객코드")
    private String empCustkey;

    /** EMP고객명 */
    @Schema(description = "EMP고객명")
    private String empCustnm;

    /** 구매자명 */
    @Schema(description = "구매자명")
    private String orderName;

    /** 구매자 전화번호 */
    @Schema(description = "구매자 전화번호")
    private String orderTel;

    /** 구매자 휴대폰번호 */
    @Schema(description = "구매자 휴대폰번호")
    private String orderPhone;

    /** 배송지명 */
    @Schema(description = "배송지명")
    private String one;

    /** 배송지 전화번호 */
    @Schema(description = "배송지 전화번호")
    private String deliveryTel;

    /** 배송지 휴대폰번호 */
    @Schema(description = "배송지 휴대폰번호")
    private String deliveryPhone;

    /** 배송지 우편번호 */
    @Schema(description = "배송지 우편번호")
    private String deliveryZipcode;

    /** 배송지 주소 */
    @Schema(description = "배송지 주소")
    private String deliveryAddr;

    /** 배송 메시지 */
    @Schema(description = "배송 메시지")
    private String deliveryMsg;

    /** EMP 고객주문번호 */
    @Schema(description = "EMP 고객주문번호")
    private String empCustDocno;

    /** 개인정보삭제예정일자 */
    @Schema(description = "개인정보삭제예정일자")
    private String delScdYmd;

    /** 적용여부 */
    @Schema(description = "적용여부")
    private String applyYn;

    /** 납품요청 일자 */
    @Schema(description = "납품요청 일자")
    private String reqDate;

    /** 여유컬럼1 */
    @Schema(description = "여유컬럼1")
    private String other01;

    /** 여유컬럼2 */
    @Schema(description = "여유컬럼2")
    private String other02;

    /** 여유컬럼3 */
    @Schema(description = "여유컬럼3")
    private String other03;

    /** 여유컬럼4 */
    @Schema(description = "여유컬럼4")
    private String other04;

    /** 여유컬럼5 */
    @Schema(description = "여유컬럼5")
    private String other05;

    /** reference01 */
    @Schema(description = "reference01")
    private String reference01;

    /** reference02 */
    @Schema(description = "reference02")
    private String reference02;

    /** reference03 */
    @Schema(description = "reference03")
    private String reference03;

    /** reference04 */
    @Schema(description = "reference04")
    private String reference04;

    /** reference05 */
    @Schema(description = "reference05")
    private String reference05;

    /** 상태 */
    @Schema(description = "상태")
    private String status;

    /** 삭제여부 */
    @Schema(description = "삭제여부")
    private String delYn;

    /** 데이터흐름제어 */
    @Schema(description = "데이터흐름제어")
    private String trafficcop;

    /** 아카이브제어 */
    @Schema(description = "아카이브제어")
    private String archivecop;

    /** 운송장번호 */
    @Schema(description = "운송장번호")
    private String invoiceno;

    /** 접수에러여부 */
    @Schema(description = "접수에러여부")
    private String rcptErrYn;

    /** 접수에러메세지 */
    @Schema(description = "접수에러메세지")
    private String rcptErrMsg;

    /** 온라인구분 */
    @Schema(description = "온라인구분")
    private String siteType;

    /** 비고 */
    @Schema(description = "비고")
    private String bigo;

    /** 최초등록일자 */
    @Schema(description = "최초등록일자")
    private String adddate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간")
    private String editdate;

    /** 최초등록자 */
    @Schema(description = "최초등록자")
    private String addwho;

    /** 최종변경자 */
    @Schema(description = "최종변경자")
    private String editwho;

    /** 접수시간대구분 */
    @Schema(description = "접수시간대구분")
    private String rcptHourType;

    /** 배송서비스구분 */
    @Schema(description = "배송서비스구분")
    private String deliverySvcType;

    /** 제외사유코드 */
    @Schema(description = "제외사유코드")
    private String exceptReasonCd;

    /** N배송여부 */
    @Schema(description = "N배송여부")
    private String nDeliveryYn;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 송화인전화번호 */
    @Schema(description = "송화인전화번호")
    private String sendrTelNo;    

    /** 송화인전화번호1 */
    @Schema(description = "송화인전화번호1")
    private String sendrTelNo1;

    /** 송화인전화번호2 */
    @Schema(description = "송화인전화번호2")
    private String sendrTelNo2;

    /** 송화인전화번호3 */
    @Schema(description = "송화인전화번호3")
    private String sendrTelNo3;
    
    /** 송화인휴대폰번호 */
    @Schema(description = "송화인휴대폰번호")
    private String sendrCellNo;    

    /** 송화인휴대폰번호1 */
    @Schema(description = "송화인휴대폰번호1")
    private String sendrCellNo1;

    /** 송화인휴대폰번호2 */
    @Schema(description = "송화인휴대폰번호2")
    private String sendrCellNo2;

    /** 송화인휴대폰번호3 */
    @Schema(description = "송화인휴대폰번호3")
    private String sendrCellNo3;

    /** 송화인안심번호1 */
    @Schema(description = "송화인안심번호1")
    private String sendrSafeNo1;

    /** 송화인안심번호2 */
    @Schema(description = "송화인안심번호2")
    private String sendrSafeNo2;

    /** 송화인안심번호3 */
    @Schema(description = "송화인안심번호3")
    private String sendrSafeNo3;

    /** 송화인우편번호 */
    @Schema(description = "송화인우편번호")
    private String sendrZipNo;

    /** 송화인주소 */
    @Schema(description = "송화인주소")
    private String sendrAddr;

    /** 송화인상세주소 */
    @Schema(description = "송화인상세주소")
    private String sendrDetailAddr;

    /** 수화인명 */
    @Schema(description = "수화인명")
    private String rcvrNm;
    
    /** 수화인전화번호 */
    @Schema(description = "수화인전화번호")
    private String rcvrTelNo;    

    /** 수화인전화번호1 */
    @Schema(description = "수화인전화번호1")
    private String rcvrTelNo1;

    /** 수화인전화번호2 */
    @Schema(description = "수화인전화번호2")
    private String rcvrTelNo2;

    /** 수화인전화번호3 */
    @Schema(description = "수화인전화번호3")
    private String rcvrTelNo3;
    
    /** 수화인휴대폰번호 */
    @Schema(description = "수화인휴대폰번호")
    private String rcvrCellNo;    

    /** 수화인휴대폰번호1 */
    @Schema(description = "수화인휴대폰번호1")
    private String rcvrCellNo1;

    /** 수화인휴대폰번호2 */
    @Schema(description = "수화인휴대폰번호2")
    private String rcvrCellNo2;

    /** 수화인휴대폰번호3 */
    @Schema(description = "수화인휴대폰번호3")
    private String rcvrCellNo3;
    
    /** 수화인안심번호 */
    @Schema(description = "수화인안심번호")
    private String rcvrSafeNo;    

    /** 수화인안심번호1 */
    @Schema(description = "수화인안심번호1")
    private String rcvrSafeNo1;

    /** 수화인안심번호2 */
    @Schema(description = "수화인안심번호2")
    private String rcvrSafeNo2;

    /** 수화인안심번호3 */
    @Schema(description = "수화인안심번호3")
    private String rcvrSafeNo3;

    /** 수화인우편번호 */
    @Schema(description = "수화인우편번호")
    private String rcvrZipNo;

    /** 수화인주소 */
    @Schema(description = "수화인주소")
    private String rcvrAddr;

    /** 수화인상세주소 */
    @Schema(description = "수화인상세주소")
    private String rcvrDetailAddr;

    /** 주문자명 */
    @Schema(description = "주문자명")
    private String ordrNm;
    
    /** 주문자전화번호 */
    @Schema(description = "주문자전화번호")
    private String ordrTelNo;    

    /** 주문자전화번호1 */
    @Schema(description = "주문자전화번호1")
    private String ordrTelNo1;

    /** 주문자전화번호2 */
    @Schema(description = "주문자전화번호2")
    private String ordrTelNo2;

    /** 주문자전화번호3 */
    @Schema(description = "주문자전화번호3")
    private String ordrTelNo3;
    
    /** 주문자휴대폰번호 */
    @Schema(description = "주문자휴대폰번호")
    private String ordrCellNo;    

    /** 주문자휴대폰번호1 */
    @Schema(description = "주문자휴대폰번호1")
    private String ordrCellNo1;

    /** 주문자휴대폰번호2 */
    @Schema(description = "주문자휴대폰번호2")
    private String ordrCellNo2;

    /** 주문자휴대폰번호3 */
    @Schema(description = "주문자휴대폰번호3")
    private String ordrCellNo3;
    
    /** 주문자안심번호 */
    @Schema(description = "주문자안심번호")
    private String ordrSafeNo;    

    /** 주문자안심번호1 */
    @Schema(description = "주문자안심번호1")
    private String ordrSafeNo1;

    /** 주문자안심번호2 */
    @Schema(description = "주문자안심번호2")
    private String ordrSafeNo2;

    /** 주문자안심번호3 */
    @Schema(description = "주문자안심번호3")
    private String ordrSafeNo3;

    /** 주문자우편번호 */
    @Schema(description = "주문자우편번호")
    private String ordrZipNo;

    /** 주문자주소 */
    @Schema(description = "주문자주소")
    private String ordrAddr;

    /** 주문자상세주소 */
    @Schema(description = "주문자상세주소")
    private String ordrDetailAddr;

    /** 원운송장번호 */
    @Schema(description = "원운송장번호")
    private String oriInvoiceno;

    /** 원주문번호 */
    @Schema(description = "원주문번호")
    private String oriOrdNo;

    /** 집화예정일자 */
    @Schema(description = "집화예정일자")
    private String colctExpctYmd;

    /** 집화예정시간 */
    @Schema(description = "집화예정시간")
    private String colctExpctHour;

    /** 배송예정일자 */
    @Schema(description = "배송예정일자")
    private String shipExpctYmd;

    /** 배송예정시간 */
    @Schema(description = "배송예정시간")
    private String shipExpctHour;

    /** 출력상태 */
    @Schema(description = "출력상태")
    private String prtSt;

    /** 물품가액 */
    @Schema(description = "물품가액")
    private String articleAmt;

    /** 비고1 */
    @Schema(description = "비고1")
    private String remark1;

    /** 비고2 */
    @Schema(description = "비고2")
    private String remark2;

    /** 비고3 */
    @Schema(description = "비고3")
    private String remark3;

    /** COD여부 */
    @Schema(description = "COD여부")
    private String codYn;

    /** 상품명 */
    @Schema(description = "상품명")
    private String description;

    /** 상품수량 */
    @Schema(description = "상품수량")
    private String orderqty;

    /** 단품코드 */
    @Schema(description = "단품코드")
    private String unitCd;

    /** 단품명 */
    @Schema(description = "단품명")
    private String unitNm;

    /** 상품가액 */
    @Schema(description = "상품가액")
    private String gdsAmt;

    /** 기타1 */
    @Schema(description = "기타1")
    private String etc1;

    /** 기타2 */
    @Schema(description = "기타2")
    private String etc2;

    /** 비고 */
    @Schema(description = "비고")
    private String etc3;

    /** 배송제외여부 */
    @Schema(description = "배송제외여부")
    private String etc4;

    /** 합배송여부 */
    @Schema(description = "합배송여부")
    private String etc5;

    /** 택배구분 */
    @Schema(description = "택배구분")
    private String dlvDv;

    /** FW주문번호 */
    @Schema(description = "FW주문번호")
    private String docnoFw;

    /** FW주문번호상세 */
    @Schema(description = "FW주문번호상세")
    private String doclineFw;

    /** 비용유형 */
    @Schema(description = "비용유형")
    private String expensetype;

    /** 데이터번호 */
    @Schema(description = "데이터번호")
    private BigDecimal serialkey;
    
    /** 데이터번호Next */
    @Schema(description = "데이터번호Next")
    private BigDecimal serialkeyNext;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 고객코드 */
    @Schema(description = "고객코드")
    private String custkey;

    /** 접수일자 */
    @Schema(description = "접수일자")
    private String docdt;

    /** 택배문서번호 */
    @Schema(description = "택배문서번호")
    private String docno;

    /** 접수구분 01: 일반, 02: 반품 */
    @Schema(description = "접수구분 01: 일반, 02: 반품")
    private String ordertype;

    /** 작업구분코드 01: 일반, 02 : 교환, 03 : A/S */
    @Schema(description = "작업구분코드 01: 일반, 02 : 교환, 03 : A/S")
    private String worktype;

    /** 요청구분코드 01: 요청, 02: 취소 */
    @Schema(description = "요청구분코드 01: 요청, 02: 취소")
    private String requesttype;

    /** 합포장키 */
    @Schema(description = "합포장키")
    private String packagekey;

    /** 합포장순번 */
    @Schema(description = "합포장순번")
    private String packagekeyLine;

    /** 운임구분코드 */
    @Schema(description = "운임구분코드")
    private String feetype;

    /** 계약품목코드 */
    @Schema(description = "계약품목코드")
    private String contracttype;

    /** 박스타입코드 */
    @Schema(description = "박스타입코드")
    private String boxtype;

    /** 박스수량 */
    @Schema(description = "박스수량")
    private BigDecimal boxqty;

    /** 운임 */
    @Schema(description = "운임")
    private BigDecimal cost;

    /** 고객관리거래처코드 */
    @Schema(description = "고객관리거래처코드")
    private String kxCustkey;

    /** 송화인명 */
    @Schema(description = "송화인명")
    private String sendrNm;

	/**단위*/
	@Schema(description = "단위")
	private String uom;
	
	/** 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;	
	
    /** 순번 */
    @Schema(description = "운임")
    private BigDecimal seq;
    
    /** 부피 */
    @Schema(description = "부피")
	public int volume;
    
    /** 현재 사용 중인 용량 */
    @Schema(description = "현재 사용 중인 용량")
    private int used;
    
    /** 사용추가 */
    @Schema(description = "사용추가")
    private int addUsed;
    
    /** 박스 총 용량 */
    @Schema(description = "박스 총 용량")
    private int capacity;
    
	/**시리얼키-데이터번호*/
	@Schema(description = "시리얼키-데이터번호")
	private BigDecimal serialkeyEmp;
	
	/**물류센터*/
	@Schema(description = "박스번호")
	private String boxno;
	
	 /** CLSFCD 도착지 코드 */
    @Schema(description = "도착지 코드")
    private String clsfcd;

    /** SUBCLSFCD 도착지 서브 코드 */
    @Schema(description = "도착지 서브 코드")
    private String subclsfcd;

    /** CLSFADDR 주소 약칭 */
    @Schema(description = "주소 약칭")
    private String clsfaddr;

    /** CLLDLVBRANNM 배송집배점 명 */
    @Schema(description = "배송집배점 명")
    private String clldlvbrannm;

    /** CLLDLVEMPNM 배송SM명 */
    @Schema(description = "배송SM명")
    private String clldlvempnm;

    /** CLLDLVEMPNICKNM SM분류코드 */
    @Schema(description = "SM분류코드")
    private String clldlvempnicknm;

    /** RSPSDIV 권역 구분 */
    @Schema(description = "권역 구분")
    private String rspsdiv;

    /** P2PCD P2P코드 */
    @Schema(description = "P2P코드")
    private String p2pcd;	
	
}
