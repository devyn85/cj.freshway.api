package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.annotation.MaskingAddress;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.12.26
 * @description : 택배송장발행(온라인) response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.26 sss  생성 </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "택배송장발행(온라인) response dto")
public class WdKxDeliveryInvoiceResDto extends CommonProcedureDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "0")
	private String chk = "0";
	
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;	
	
	/** 일자(From) */
	@Schema(description = "일자(From)")
	private String dt1;
	/** 일자(To) */
	@Schema(description = "일자(To)")
	private String dt2;

	/**시리얼키*/
	@Schema(description = "시리얼키")
	private String serialkey;
	
	/**시리얼키-데이터번호*/
	@Schema(description = "시리얼키-데이터번호")
	private BigDecimal serialkeyEmp;
	
	/**상품 체적 설명 길이(Length)-너비(Width)-높이(Height) 단위 mm*/
	@Schema(description = "상품 체적 설명 길이(Length)-너비(Width)-높이(Height) 단위 mm")
	private String cubedescr;
	
	/**체적*/
	@Schema(description = "체적")
	private BigDecimal cube;

	/**DC코드*/
	@Schema(description = "DC코드")
	private String dccode;

	/**고객ID*/
	@Schema(description = "고객ID")
	private String custkey;

	/**접수일자*/
	@Schema(description = "접수일자")
	private String docdt;

	/**접수일자(표시용)*/
	@Schema(description = "접수일자(표시용)")
	private String docdt2;

	/**주문고유번호(고객사용번호 아님)*/
	@Schema(description = "주문고유번호(고객사용번호 아님)")
	private String docno;

	/**사이트주문번호*/
	@Schema(description = "사이트주문번호")
	private String empCustDocno;

	/**접수구분*/
	@Schema(description = "접수구분")
	private String ordertype;
	
	/**접수구분명*/
	@Schema(description = "접수구분명")
	private String ordertypenm;	

	/**작업구분코드*/
	@Schema(description = "작업구분코드")
	private String worktype;
	
	/**작업구분명*/
	@Schema(description = "작업구분명")
	private String worktypenm;
	

	/**요청구분코드*/
	@Schema(description = "요청구분코드")
	private String requesttype;

	/**저장조건)*/
	@Schema(description = "저장조건")
	private String storagetype;
	
	/**보관유형명*/
	@Schema(description = "보관유형명")
	private String storagetypenm;

	/**패키지키(없으면 기본값 생성) [TODO: 생성규칙 확인]*/
	@Schema(description = "패키지키(없으면 기본값 생성) [TODO: 생성규칙 확인]")
	private String packagekey;

	/**패키지 라인(기본값)*/
	@Schema(description = "패키지 라인(기본값)")
	private BigDecimal packagekeyLine;

	/**비용유형*/
	@Schema(description = "비용유형")
	private String expensetype;

	/**요금유형*/
	@Schema(description = "요금유형")
	private String feetype;

	/**요금유형 설명*/
	@Schema(description = "요금유형 설명")
	private String feetypeDesc;

	/**계약유형*/
	@Schema(description = "계약유형")
	private String contracttype;

	/**박스타입*/
	@Schema(description = "박스타입")
	private String boxtype;

	/**박스수량*/
	@Schema(description = "박스수량")
	private BigDecimal boxqty;
	
	/**총재고*/
	@Schema(description = "총재고")
	private BigDecimal totstockqty;
	
	/**누적주문수량*/
	@Schema(description = "누적주문수량")
	private BigDecimal cumOrderQty;
	

	/**운임*/
	@Schema(description = "운임")
	private BigDecimal cost;
	

	/**고객관리거래처코드*/
	@Schema(description = "고객관리거래처코드")
	private String kxCustkey;

	/**송화인명*/
	@MaskingName
	@Schema(description = "송화인명")
	private String sendrNm;

	/**송화인 전화번호*/
	@MaskingTelno
	@Schema(description = "송화인 전화번호")
	private String sendrTelNo;

	/**송화인 휴대폰번호*/
	@MaskingTelno
	@Schema(description = "송화인 휴대폰번호")
	private String sendrCellNo;

	/**송화인 안심번호*/
	@MaskingTelno
	@Schema(description = "송화인 안심번호")
	private String sendrSafeNo;

	/**송화인 우편번호*/
	@Schema(description = "송화인 우편번호")
	private String sendrZipNo;

	/**송화인 주소*/
	@MaskingAddress
	@Schema(description = "송화인 주소")
	private String sendrAddr;

	/**수화인명*/
	@MaskingName
	@Schema(description = "수화인명")
	private String rcvrNm;

	/**수화인명(마스킹)*/
	@MaskingName
	@Schema(description = "수화인명(마스킹)")
	private String rcvrNm2;

	/**수화인 전화번호*/
	@MaskingTelno
	@Schema(description = "수화인 전화번호")
	private String rcvrTelNo;

	/**수화인 전화번호(마스킹)*/
	@MaskingTelno
	@Schema(description = "수화인 전화번호(마스킹)")
	private String rcvrTelNo2;

	/**수화인 휴대폰번호*/
	@MaskingTelno
	@Schema(description = "수화인 휴대폰번호")
	private String rcvrCellNo;

	/**수화인 휴대폰번호(마스킹)*/
	@MaskingTelno
	@Schema(description = "수화인 휴대폰번호(마스킹)")
	private String rcvrCellNo2;

	/**수화인 안심번호*/
	@MaskingTelno
	@Schema(description = "수화인 안심번호")
	private String rcvrSafeNo;

	/**수화인 우편번호*/
	@Schema(description = "수화인 우편번호")
	private String rcvrZipNo;

	/**수화인 주소*/
	@MaskingAddress
	@Schema(description = "수화인 주소")
	private String rcvrAddr;
	
	/**원수화인 주소*/
	@MaskingAddress
	@Schema(description = "원수화인 주소")
	private String rcvrAddrOri;	

	/**주문자명*/
	@MaskingName
	@Schema(description = "주문자명")
	private String ordrNm;

	/**주문자 전화번호*/
	@MaskingTelno
	@Schema(description = "주문자 전화번호")
	private String ordrTelNo;

	/**주문자 휴대폰번호*/
	@MaskingTelno
	@Schema(description = "주문자 휴대폰번호")
	private String ordrCellNo;

	/**주문자 안심번호*/
	@MaskingTelno
	@Schema(description = "주문자 안심번호")
	private String ordrSafeNo;

	/**주문자 우편번호*/
	@Schema(description = "주문자 우편번호")
	private String ordrZipNo;

	/**주문자 주소*/
	@MaskingAddress
	@Schema(description = "주문자 주소")
	private String ordrAddr;

	/**운송장번호*/
	@Schema(description = "운송장번호")
	private String invoiceno;

	/**운송장번호(하이픈 제거)*/
	@Schema(description = "운송장번호(하이픈 제거)")
	private String invoiceno2;

	/**운송장번호(포맷팅)*/
	@Schema(description = "운송장번호(포맷팅)")
	private String invoiceno3;

	/**출력상태*/
	@Schema(description = "출력상태")
	private String prtSt;
	
	/**출력상태명*/
	@Schema(description = "출력상태명")
	private String prtStnm;
	

	/**상품코드*/
	@Schema(description = "상품코드")
	private String sku;

	/**상품명*/
	@Schema(description = "상품명")
	private String description;

	/**보관유형명 + 상품명 + 수량(표시용 문자열)*/
	@Schema(description = "보관유형명 + 상품명 + 수량(표시용 문자열)")
	private String descriptionQty;

	/**상품수량*/
	@Schema(description = "상품수량")
	private BigDecimal orderqty;

	/**재고(2600)*/
	@Schema(description = "재고(2600)")
	private BigDecimal qty2600;

	/**재고(2900)*/
	@Schema(description = "재고(2900)")
	private BigDecimal qty2900;

	/**택배구분*/
	@Schema(description = "택배구분")
	private String dlvDv;

	/**접수에러여부*/
	@Schema(description = "접수에러여부")
	private String rcptErrYn;

	/**접수에러메시지*/
	@Schema(description = "접수에러메시지")
	private String rcptErrMsg;

	/**상태(00이면 10으로 치환)*/
	@Schema(description = "상태(00이면 10으로 치환)")
	private String status;
	
	/**상태명*/
	@Schema(description = "상태명")
	private String statusnm;	

	/**삭제여부*/
	@Schema(description = "삭제여부")
	private String delYn;
	
	/**FW주문번호항번*/
	@Schema(description = "FW주문번호항번")
	private String docline;

	/**FW주문번호*/
	@Schema(description = "FW주문번호")
	private String docnoFw;

	/**FW주문번호항번*/
	@Schema(description = "FW주문번호항번")
	private String doclineFw;

	/**사이트구분*/
	@Schema(description = "사이트구분")
	private String siteType;

	/**비고*/
	@Schema(description = "비고")
	private String bigo;
	
	/**EMP고객코드*/
	@Schema(description = "EMP고객코드")
	private String empCustkey;

	/**EMP고객명*/
	@Schema(description = "EMP고객명")
	private String empCustnm;

	/**배송메시지*/
	@Schema(description = "배송메시지")
	private String deliveryMsg;

	/**납품요청일자*/
	@Schema(description = "납품요청일자")
	private String reqDate;

	/**납품요청일자*/
	@Schema(description = "납품요청일자")
	private String reqDate2;

	/**접수시간대구분*/
	@Schema(description = "접수시간대구분")
	private String rcptHourType;
	
	/**접수시간대구분명*/
	@Schema(description = "접수시간대구분명")
	private String rcptHourTypenm;	

	/**배송서비스구분*/
	@Schema(description = "배송서비스구분")
	private String deliverySvcType;

	/**제외사유*/
	@Schema(description = "제외사유")
	private String exceptReasonCd;

	/**등록사원ID*/
	@Schema(description = "등록사원ID")
	private String addwho;

	/**등록일시*/
	@Schema(description = "등록일시")
	private String adddate;



	/******************** 택배기준 *************************/
	/**물류센터*/
	@Schema(description = "박스번호")
	private String boxno;

	/**물류센터*/
	@Schema(description = "박스명")
	private String boxnm;

	/**물류센터*/
	@Schema(description = "박스내경길이")
	private BigDecimal innerLength;

	/**물류센터*/
	@Schema(description = "박스내경폭")
	private BigDecimal innerWidth;

	/**물류센터*/
	@Schema(description = "박스내경높이")
	private BigDecimal innerHeight;

	/**물류센터*/
	@Schema(description = "박스외경길이")
	private BigDecimal outerLength;

	/**물류센터*/
	@Schema(description = "박스외경폭")
	private BigDecimal outerWidth;

	/**물류센터*/
	@Schema(description = "박스외경높이")
	private BigDecimal outerHeight;

	/**단가*/
	@Schema(description = "단가")
	private BigDecimal price;

	/**사용여부*/
	@Schema(description = "사용여부")
	private String useYn;
	
	/**단위*/
	@Schema(description = "단위")
	private String uom;
	
	/**토큰*/
	@Schema(description = "토큰")
	private String tokenNum;
	
	/**토큰만료일시*/
	@Schema(description = "토큰만료일시")
	private String tokenExprtnDtm;
	
    /** 보안토큰 */
    @Schema(description = "보안토큰")
    private String accessToken;		
    
    /** 순번 */
    @Schema(description = "순번")
    private BigDecimal seq;
    
    /** 부피 */
    @Schema(description = "부피")
	public int volume;
    
    /** 현재 사용 중인 용량 */
    @Schema(description = "현재 사용 중인 용량")
    private Integer used;
    
    /** 사용추가 */
    @Schema(description = "사용추가")
    private int addUsed;
    
    /** 박스 총 용량 */
    @Schema(description = "박스 총 용량")
    private int capacity;
    
    
    /** 고객번호 */
    @Schema(description = "고객번호")
    private String clntnum;

    /** 관리고객코드 */
    @Schema(description = "관리고객코드")
    private String clntmgmcustcd;

    /** 분류구분코드 */
    @Schema(description = "분류구분코드")
    private String prngdivcd;

    /** 상태 */
    @Schema(description = "상태")
    private String cgosts;

    /** 주소 */
    @Schema(description = "주소")
    private String address;

    /** 우편번호 */
    @Schema(description = "우편번호")
    private String zipnum;

    /** 우편ID */
    @Schema(description = "우편ID")
    private String zipid;

    /** 구주소 */
    @Schema(description = "구주소")
    private String oldaddress;

    /** 구주소상세 */
    @Schema(description = "구주소상세")
    private String oldaddressdtl;

    /** 신주소 */
    @Schema(description = "신주소")
    private String newaddress;

    /** 신주소상세 */
    @Schema(description = "신주소상세")
    private String nesaddressdtl;

    /** 기타주소 */
    @Schema(description = "기타주소")
    private String etcaddr;

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

    /** 분류코드 */
    @Schema(description = "분류코드")
    private String clsfcd;

    /** 분류명 */
    @Schema(description = "분류명")
    private String clsfnm;

    /** 하위분류코드 */
    @Schema(description = "하위분류코드")
    private String subclsfcd;

    /** 응답구분 */
    @Schema(description = "응답구분")
    private String rspsdiv;

    /** 신주소여부 */
    @Schema(description = "신주소여부")
    private String newaddryn;

	/**출력횟수*/
	@Schema(description = "출력횟수")
	private BigDecimal printCnt;
	
    /** 택배접수일자 */
    @Schema(description = "택배접수일자")
    private String parcelRcptDate;

    /** 택배접수시간 */
    @Schema(description = "택배접수시간")
    private String parcelRcptHour;
        
    /** 조회결과코드 */
    @Schema(description = "조회결과코드")
    private String resultCd;	
    
    /** 조회결과 */
    @Schema(description = "조회결과")
    private String resultDetail;    
    
    // P2P코드
    @Schema(description = "P2P코드")
    private String p2pCd;
    
	/**충부피*/
	@Schema(description = "충부피")
	private BigDecimal totvolume;
	
	/**N상품여부*/
	@Schema(description = "N상품여부")
	private String nskuyn;	
	
	/**운송장대체번호*/
	@Schema(description = "운송장대체번호")
	private String remark2;	
    
}
