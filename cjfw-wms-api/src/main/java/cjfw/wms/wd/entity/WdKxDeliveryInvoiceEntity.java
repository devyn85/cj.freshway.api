package cjfw.wms.wd.entity;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss
 * @date : 2025.12.26
 * @description : 택배송장발행(온라인) Entity
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.26 sss  생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "택배송장발행(온라인) Entity")
public class WdKxDeliveryInvoiceEntity extends CommonProcedureDto {

    /** 데이터번호 */
    @Schema(description = "데이터번호")
    private String serialkey;
    
	/**데이터번호-다중OR검색*/
	@Schema(description = "데이터번호-다중OR검색")
	private List<List<String>> serialkeyMulti;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;
    
    /** KX고객사ID */
    @Schema(description = "KX고객사ID")
    private String kxCustId;

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
    private String cost;

    /** 고객관리거래처코드 */
    @Schema(description = "고객관리거래처코드")
    private String kxCustkey;

    /** 송화인명 */
    @Schema(description = "송화인명")
    private String sendrNm;

    /** 송화인전화번호1 */
    @Schema(description = "송화인전화번호1")
    private String sendrTelNo1;

    /** 송화인전화번호2 */
    @Schema(description = "송화인전화번호2")
    private String sendrTelNo2;

    /** 송화인전화번호3 */
    @Schema(description = "송화인전화번호3")
    private String sendrTelNo3;

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

    /** 수화인전화번호1 */
    @Schema(description = "수화인전화번호1")
    private String rcvrTelNo1;

    /** 수화인전화번호2 */
    @Schema(description = "수화인전화번호2")
    private String rcvrTelNo2;

    /** 수화인전화번호3 */
    @Schema(description = "수화인전화번호3")
    private String rcvrTelNo3;

    /** 수화인휴대폰번호1 */
    @Schema(description = "수화인휴대폰번호1")
    private String rcvrCellNo1;

    /** 수화인휴대폰번호2 */
    @Schema(description = "수화인휴대폰번호2")
    private String rcvrCellNo2;

    /** 수화인휴대폰번호3 */
    @Schema(description = "수화인휴대폰번호3")
    private String rcvrCellNo3;

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

    /** 주문자전화번호1 */
    @Schema(description = "주문자전화번호1")
    private String ordrTelNo1;

    /** 주문자전화번호2 */
    @Schema(description = "주문자전화번호2")
    private String ordrTelNo2;

    /** 주문자전화번호3 */
    @Schema(description = "주문자전화번호3")
    private String ordrTelNo3;

    /** 주문자휴대폰번호1 */
    @Schema(description = "주문자휴대폰번호1")
    private String ordrCellNo1;

    /** 주문자휴대폰번호2 */
    @Schema(description = "주문자휴대폰번호2")
    private String ordrCellNo2;

    /** 주문자휴대폰번호3 */
    @Schema(description = "주문자휴대폰번호3")
    private String ordrCellNo3;

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

    /** 운송장번호 */
    @Schema(description = "운송장번호")
    private String invoiceno;

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

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

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

    /** 접수에러여부 */
    @Schema(description = "접수에러여부")
    private String rcptErrYn;

    /** 접수에러메세지 */
    @Schema(description = "접수에러메세지")
    private String rcptErrMsg;

    /** 진행상태 */
    @Schema(description = "진행상태")
    private String status;

    /** 삭제여부 */
    @Schema(description = "삭제여부")
    private String delYn;

    /** FW주문번호 */
    @Schema(description = "FW주문번호")
    private String docnoFw;

    /** FW주문번호상세 */
    @Schema(description = "FW주문번호상세")
    private String doclineFw;
    
    /** 주문번호상세 */
    @Schema(description = "주문번호상세")
    private String docline;

    /** 비용유형 */
    @Schema(description = "비용유형")
    private String expensetype;

    /** 생성일자 */
    @Schema(description = "생성일자")
    private String adddate;

    /** 수정일자 */
    @Schema(description = "수정일자")
    private String editdate;

    /** 생성자 */
    @Schema(description = "생성자")
    private String addwho;

    /** 수정자 */
    @Schema(description = "수정자")
    private String editwho;

    /** 납품요청일자 */
    @Schema(description = "납품요청일자")
    private String reqDate;

    /** 접수시간대구분 */
    @Schema(description = "접수시간대구분")
    private String rcptHourType;

    /** 배송서비스구분 */
    @Schema(description = "배송서비스구분")
    private String deliverySvcType;

    /** 제외사유코드 */
    @Schema(description = "제외사유코드")
    private String exceptReasonCd;
    
	/**N배송여부*/
	@Schema(description = "N배송여부")
	private String nDeliveryYn;
	
	/**EMP고객코드*/
	@Schema(description = "EMP고객코드")
	private String empCustkey;	

	/**박스번호*/
	@Schema(description = "박스번호")
	private String boxno;	

    
    /** 조회결과코드 */
    @Schema(description = "조회결과코드")
    private String resultCd;	
    
    /** 조회결과 */
    @Schema(description = "조회결과")
    private String resultDetail;	
    
    /** 처리건수 */	
    @Schema(description = "처리건수")
    private int processCnt;		
    
    /** 순번 */
    @Schema(description = "순번")
    private BigDecimal seq;
    
    /** 토큰번호 */
    @Schema(description = "토큰번호")
    private String tokenNum;
    
    /** 택배접수일자 */
    @Schema(description = "택배접수일자")
    private String parcelRcptDate;

    /** 택배접수시간 */
    @Schema(description = "택배접수시간")
    private String parcelRcptHour;

    /** Volumne */
    @Schema(description = "Volumne")
    private String volume;
    
	/**시리얼키-데이터번호*/
	@Schema(description = "시리얼키-데이터번호")
	private BigDecimal serialkeyEmp;
	
    /** 보안토큰유효기간 */
    @Schema(description = "보안토큰유효기간")
    private String tokenExprtnDtm;
    
    /** 그룹인덱스 */
    @Schema(description = "그룹인덱스")
    private BigDecimal groupIndex;    
    
    /** cj운송장번호 */
    @Schema(description = "운송장번호")
    private String invcNo;

    /** 분류코드 */
    @Schema(description = "분류코드")
    private String clsfcd;    
    
    /** 분류명 */
    @Schema(description = "분류명")
    private String clsfnm;

    /** 하위분류코드 */
    @Schema(description = "하위분류코드")
    private String subclsfcd;    
    
    /** 약식주소 */
    @Schema(description = "약식주소")
    private String shortaddr;

    /** 분류주소 */
    @Schema(description = "분류주소")
    private String clsfaddr;

    /** 배송지점코드 */
    @Schema(description = "배송지점코드")
    private String clldlvbrancd;

    /** 배송지점명 */
    @Schema(description = "배송지점명")
    private String clldlvbrannm;

    /** 배송지점약식명 */
    @Schema(description = "배송지점약식명")
    private String clldlcbranshortnm;

    /** 배송직원번호 */
    @Schema(description = "배송직원번호")
    private String clldlvempnum;

    /** 배송직원명 */
    @Schema(description = "배송직원명")
    private String clldlvempnm;

    /** 배송직원닉네임 */
    @Schema(description = "배송직원닉네임")
    private String clldlvempnicknm;




	
}
