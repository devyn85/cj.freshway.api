package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 퀵요청 Tab2 마스터 리스트 DTO
 */
@Data
public class WdQuickRequestResVOCDto extends CommonProcedureDto {	
    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;   
    
    /** 센터접수번호 */
    @Schema(description = "센터접수번호")
    private String rcptNo;

    /** VOC번호 */
    @Schema(description = "VOC번호")
    private String vocno;
    
    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** VOC순번 */
    @Schema(description = "VOC순번")
    private String vocseq;

    /** VSR번호 */
    @Schema(description = "VSR번호")
    private String vsrno;

    /** VSR유형 */
    @Schema(description = "VSR유형")
    private String vsrtype;
    
    /** VSR유형명 */
    @Schema(description = "VSR유형명")
    private String vsrtypenm;

    /** 주문번호 */
    @Schema(description = "주문번호")
    private String docno;

    /** 주문순번 */
    @Schema(description = "주문순번")
    private String docline;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;
    

    /** 상품내역 */
    @Schema(description = "상품내역")
    private String skuInfo;

    /** 주문량 */
    @Schema(description = "주문량")
    private BigDecimal orderqty;

    /** 주문단위 */
    @Schema(description = "주문단위")
    private String orderuom;

    /** voc단위 */
    @Schema(description = "voc단위")
    private String vocUom;
    
    /** VOC단위명 */
    @Schema(description = "VOC단위명")
    private String vocUomnm;

    /** voc량 */
    @Schema(description = "voc량")
    private BigDecimal vocQty;

    /** 시리얼키 (NEXTVAL) */
    @Schema(description = "시리얼키 (NEXTVAL)")
    private String serialkey;

    /** IF데이터번호 */
    @Schema(description = "IF데이터번호")
    private String ifSerialkey;

    /** 퀵주문번호 */
    @Schema(description = "퀵주문번호")
    private String quickDocno;

    /** 진행상태 */
    @Schema(description = "진행상태")
    private String status;
    
    /** 진행상태명 */
    @Schema(description = "진행상태명")
    private String statusnm;  

    /** VSR순번 */
    @Schema(description = "VSR순번")
    private String vsrSeq;
    

    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode;

    /** 납품일 */
    @Schema(description = "납품일")
    private String deliverydate;

    /** 담당부서코드 */
    @Schema(description = "담당부서코드")
    private String department;

    /** 판매처코드 */
    @Schema(description = "판매처코드")
    private String slstId;

    /** 판매처명 */
    @Schema(description = "판매처명")
    private String slstNm;

    /** 관리처코드 */
    @Schema(description = "관리처코드")
    private String mgmtCustkey;

    /** 관리처명 */
    @Schema(description = "관리처명")
    private String mgmtCustname;

    /** 상품단위 */
    @Schema(description = "상품단위")
    private String uom;

    /** 집하지방문순서 */
    @Schema(description = "집하지방문순서")
    private BigDecimal gthSeq;

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

    /** 작성자 */
    @Schema(description = "작성자")
    private String writer;

    /** 작성일자 */
    @Schema(description = "작성일자")
    private String writedate;

    /** 작성시간 */
    @Schema(description = "작성시간")
    private String writetime;

    /** 요청부서 */
    @Schema(description = "요청부서")
    private String reqDepartment;

    /** 요청자 */
    @Schema(description = "요청자")
    private String reqId;
    
    /** 요청자명 */
    @Schema(description = "요청자명")
    private String reqNm;    

    /** 도착지 방문순서 */
    @Schema(description = "도착지 방문순서")
    private String toSeq;

    /** 고객주소 */
    @Schema(description = "고객주소")
    private String address;
    
    /** 고객주소상세 */
    @Schema(description = "고객주소상세")
    private String address2;

    /** 고객전화번호 */
    @Schema(description = "고객전화번호")
    private String phone;

    /** 비고 */
    @Schema(description = "비고")
    private String rmk;

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

    /** 전달사항 */
    @Schema(description = "전달사항")
    private String memo;
    
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
    

    /** 플랜트 */
    @Schema(description = "플랜트")
    private String plant;
    
	/** 센터접수일자 */
	@Schema(description = "센터접수일자")
	private String rcptDate;
	
	/** 센터접수시간 */
	@Schema(description = "센터접수시간")
	private String rcptHour;    
	
	private static final long serialVersionUID = 1L;

	/** 아이템수량 */
	@Schema(description = "아이템수량")
	private BigDecimal itemCnt;

	/** 퀵주문접수일자 */
	@Schema(description = "퀵주문접수일자")
	private String quickOrderRcptDate;

	/** 퀵주문접수시간 */
	@Schema(description = "퀵주문접수시간")
	private String quickOrderRcptHour;
	
	/** 긴급여부 */
	@Schema(description = "긴급여부")
	private String urgentYn;
	
    /** 배송메시지 */
    @Schema(description = "배송메시지")
    private String deliverymemo;  

}
