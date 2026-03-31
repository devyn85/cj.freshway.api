package cjfw.wms.wd.dto;
import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 퀵접수(VSR)및처리 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "퀵접수(VSR)및처리 Master Response DTO")
@Data
public class WdQuickRequestResDto extends CommonProcedureDto {
    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;   
    
	/** IF데이터번호 */
	@Schema(description = "IF데이터번호")
	private String ifSerialkey;
	/** 일자(From) */
	@Schema(description = "일자(From)")
	private String dt1;
	/** 일자(To) */
	@Schema(description = "일자(To)")
	private String dt2;	
	
	/** 플랜트 */
	@Schema(description = "플랜트")
    private String plant;
	
	/** 로케이션 */
	@Schema(description = "로케이션")
    private String loc;
	
    /** voc량 */
    @Schema(description = "voc량")
    private BigDecimal vocQty;	
	
	/** 물류센터 */
	@Schema(description = "물류센터 코드")
	private String dccode;
	
    /** 진행상태 */
    @Schema(description = "진행상태")
    private String status;
    
    /** VOC단위명 */
    @Schema(description = "VOC단위명")
    private String vocUomnm;
    
    /** 진행상태명 */
    @Schema(description = "진행상태명")
    private String statusnm;    

    /** 의뢰일-클레임작성일자 */
    @Schema(description = "의뢰일-클레임작성일자")
    private String writedate;

    /** VOC번호 */
    @Schema(description = "VOC번호")
    private String vocno;

    /** VSR유형 */
    @Schema(description = "VSR유형")
    private String vsrtype;

    /** VSR유형명 */
    @Schema(description = "VSR유형명")
    private String vsrtypenm;

    /** 요청부서코드 */
    @Schema(description = "요청부서코드")
    private String reqDepartment;

    /** 요청부서명 */
    @Schema(description = "요청부서명")
    private String reqDepartmentnm;

    /** 협력사코드 */
    @Schema(description = "협력사코드")
    private String custkey;

    /** 협력사명 */
    @Schema(description = "협력사명")
    private String custkeynm;

    /** 관리처코드 */
    @Schema(description = "관리처코드")
    private String custkeymng;

    /** 관리처명 */
    @Schema(description = "관리처명")
    private String custkeynmmng;

    /** 요청자 */
    @Schema(description = "요청자")
    private String reqId;

    /** 요청자명 */
    @Schema(description = "요청자명")
    private String reqIdnm;

    /** 제목-클레임제목 */
    @Schema(description = "제목-클레임제목")
    private String title;

    /** 전달사항-클레임세부내역 */
    @Schema(description = "전달사항-클레임세부내역")
    private String memo;

    /** 고객주소 */
    @Schema(description = "고객주소")
    private String address;

    /** 고객전화번호 */
    @Schema(description = "고객전화번호")
    private String phone;

    /** 센터접수번호 */
    @Schema(description = "센터접수번호")
    private String rcptNo;

    /** 퀵주문번호 */
    @Schema(description = "퀵주문번호")
    private String quickDocno;
    
    /** 긴급여부 */
    @Schema(description = "긴급여부")
    private String urgentYn;

    /** VOC순번 */
    @Schema(description = "VOC순번")
    private String vocseq;

    /** VSR번호 */
    @Schema(description = "VSR번호")
    private String vsrno;

    /** 협력사연락처 */
    @Schema(description = "협력사연락처")
    private String custPhone;

    /** 납품일자 */
    @Schema(description = "납품일자")
    private String deliverydate;

    /** 오더번호 */
    @Schema(description = "오더번호")
    private String docno;

    /** 품목번호 */
    @Schema(description = "품목번호")
    private String docline;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
	/** 상품명 */
	@Schema(description = "상품명")
	private String skuname;    

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 주문UOM */
    @Schema(description = "주문UOM")
    private String orderuom;

    /** 클레임UOM */
    @Schema(description = "클레임UOM")
    private String claimuom;

    /** 주문수량 */
    @Schema(description = "주문수량")
    private BigDecimal orderqty;

    /** 클레임수량 */
    @Schema(description = "클레임수량")
    private BigDecimal claimqty;

    /** 클레임작성자 */
    @Schema(description = "클레임작성자")
    private String writer;

    /** 클레임작성시간 */
    @Schema(description = "클레임작성시간")
    private String writetime;

    /** 클레임진행상태 */
    @Schema(description = "클레임진행상태")
    private String claimstatus;

    /** 클레임대분류ID */
    @Schema(description = "클레임대분류ID")
    private String claimdtlidl;

    /** 클레임중분류ID */
    @Schema(description = "클레임중분류ID")
    private String claimdtlidm;

    /** 클레임소분류ID */
    @Schema(description = "클레임소분류ID")
    private String claimdtlids;

    /** 클레임세분류ID */
    @Schema(description = "클레임세분류ID")
    private String claimdtlid;

    /** 정상 여부 */
    @Schema(description = "정상 여부")
    private String skugoodyn;

    /** 실물여부 */
    @Schema(description = "실물여부")
    private String packingmethod;

    /** 귀책 부서 구분 코드 */
    @Schema(description = "귀책 부서 구분 코드")
    private String imputeDevcd;

    /** 귀책 모호 여부 */
    @Schema(description = "귀책 모호 여부")
    private String inputeyn;

    /** 귀속 부서 구분 코드 */
    @Schema(description = "귀속 부서 구분 코드")
    private String blngdeptcd;

    /** 비고 */
    @Schema(description = "비고")
    private String rmk;

    /** 삭제여부 */
    @Schema(description = "삭제여부")
    private String delYn;

    /** IF_ID */
    @Schema(description = "IF_ID")
    private String ifId;

    /** IF 들어온 TIMESTAMP */
    @Schema(description = "IF 들어온 TIMESTAMP")
    private String ifTimestamp;

    /** I/F구분 */
    @Schema(description = "I/F구분")
    private String ifFlag;

    /** 전송시간 */
    @Schema(description = "전송시간")
    private String ifDate;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간")
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

    /** VSR순번 */
    @Schema(description = "VSR순번")
    private String vsrSeq;
    
    /** 집하지방문순서 */
    @Schema(description = "집하지방문순서")
    private String gthSeq;

    /** 담당부서코드 */
    @Schema(description = "담당부서코드")
    private String department;

    /** 판매처코드 */
    @Schema(description = "판매처코드")
    private String slstId;

    /** 요청자명 */
    @Schema(description = "요청자명")
    private String reqNm;    
    
	/** 센터접수일자 */
	@Schema(description = "센터접수일자")
	private String rcptDate; 
	
	/** 센터접수시간 */
	@Schema(description = "센터접수시간")
	private String rcptHour;
	
	/** 배송방법 */
    @Schema(description = "배송방법")
    private String deliverytype;

    /** 배송수단 */
    @Schema(description = "배송수단")
    private String deliveryMethod;

    /** 배송선택 */
    @Schema(description = "배송선택")
    private String deliveryOption;

    /** 물품종류 */
    @Schema(description = "물품종류")
    private String articleType;

    /** 지급구분 */
    @Schema(description = "지급구분")
    private String payType;
    
    /** 예약일시 */
    @Schema(description = "예약일시")
    private String reservedate;    

    /** 예약일 */
    @Schema(description = "예약일")
    private String reservedate1;	
    

    /** 상품내역 */
    @Schema(description = "상품내역")
    private String skuInfo;

    /** voc단위 */
    @Schema(description = "voc단위")
    private String vocUom;


    /** 시리얼키 (NEXTVAL) */
    @Schema(description = "시리얼키 (NEXTVAL)")
    private String serialkey;



    /** 판매처명 */
    @Schema(description = "판매처명")
    private String slstNm;

    /** 관리처코드 */
    @Schema(description = "관리처코드")
    private String mgmtCustkey;

    /** 관리처명 */
    @Schema(description = "관리처명")
    private String mgmtCustname;


    /** 집하지코드 */
    @Schema(description = "집하지코드")
    private String gthCd;

    /** 집하지명 */
    @Schema(description = "집하지명")
    private String gthNm;

    /** 집하지주소 */
    @Schema(description = "집하지주소")
    private String gthAddr;
    
    /** 집하지주소상세 */
    @Schema(description = "집하지주소상세")
    private String gthAddrdtl;    

    /** 집하지담당자명 */
    @Schema(description = "집하지담당자명")
    private String gthEmpNm;

    /** 집하지연락처 */
    @Schema(description = "집하지연락처")
    private String gthTel;

    /** 협력사코드 */
    @Schema(description = "협력사코드")
    private String poCustkey;

    /** 협력사연락처 */
    @Schema(description = "협력사연락처")
    private String poCustTel;

    /** 도착지 방문순서 */
    @Schema(description = "도착지 방문순서")
    private String toSeq;

    
    /** 예약시분 */
    @Schema(description = "예약시분")
    private String reservedate2;    

    /** 귀책부서 */
    @Schema(description = "귀책부서")
    private String respDept;

    /** 귀책사유 */
    @Schema(description = "귀책사유")
    private String respReason;

    /** 귀책담당자 */
    @Schema(description = "귀책담당자")
    private String respEmp;

    /** 2600센터 재고수량 */
    @Schema(description = "2600센터 재고수량")
    private BigDecimal qty2600;

    /** 2620센터 재고수량 */
    @Schema(description = "2620센터 재고수량")
    private BigDecimal qty2620;

    /** 2630센터 재고수량 */
    @Schema(description = "2630센터 재고수량")
    private BigDecimal qty2630;

    /** 2650센터 재고수량 */
    @Schema(description = "2650센터 재고수량")
    private BigDecimal qty2650;

    /** 2660센터 재고수량 */
    @Schema(description = "2660센터 재고수량")
    private BigDecimal qty2660;

    /** 2230센터 재고수량 */
    @Schema(description = "2230센터 재고수량")
    private BigDecimal qty2230;

    /** 2260센터 재고수량 */
    @Schema(description = "2260센터 재고수량")
    private BigDecimal qty2260;

    /** 고객주소상세 */
    @Schema(description = "고객주소상세")
    private String address2;
    
    /** 퀵주문접수일자 */
    @Schema(description = "퀵주문접수일자")
    private String quickOrderRcptDate;

    /** 퀵주문접수시간 */
    @Schema(description = "퀵주문접수시간")
    private String quickOrderRcptHour;
    
    /** 퀵주문접수일자 */
    @Schema(description = "퀵주문접수일자")
    private String quickDate;
    
    /** 퀵주문접수시간 */
    @Schema(description = "퀵주문접수시간")
    private String quickHour;    
    
    /** 배송메시지 */
    @Schema(description = "배송메시지")
    private String deliverymemo;  	
    
	/** 퀵사용자id */
	@Schema(description = "퀵사용자id")
	private String quickUserId; 
	
	
	/** 퀵사용자id */
	@Schema(description = "센터명")
	private String dcname;
}
