package cjfw.wms.rt.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.18 
 * @description : 외부비축반품확정 마스터 조회 기능을 구현한 resDto Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 ParkJinWoo 생성
 */
@Data
@Schema(description = " 외부비축반품확정(출고내역) 마스터 조회 결과")
public class RtReceiptConfirmExdcMasterSubResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


 
    /* 체크 & 기본 */
    /** 체크여부(고정 0) */
    @Schema(description = "체크여부(고정 0)")
    private String checkYn;              // CHECKYN

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;               // DCCODE

    /** 센터명 */
    @Schema(description = "센터명")
    private String dcName;               // DCNAME

    /* 문서/조직 */
    /** 고객주문번호 */
    @Schema(description = "출고문서번호")
    private String docnoWd;              // DOCNO_WD

    /** 조직코드 */
    @Schema(description = "조직코드")
    private String organize;             // ORGANIZE

    /** 조직명 */
    @Schema(description = "조직명")
    private String organizeName;         // ORGANIZENAME

    /** 창고코드 */
    @Schema(description = "창고코드")
    private String area;                 // AREA

    /** 고객반품문서번호 */
    @Schema(description = "반품문서번호")
    private String docNo;                // DOCNO

    /** 확정일자 */
    @Schema(description = "확정일자")
    private String confirmDate;          // CONFIRMDATE

    /** 화주코드 */
    @Schema(description = "고객코드")
    private String storerkey;            // STORERKEY

    /** 문서일자(YYYYMMDD) */
    @Schema(description = "문서일자")
    private String docDt;                // DOCDT

    /* 발주유형/상태 */
    /** PO 유형 */
    @Schema(description = "PO 유형")
    private String poType;               // POTYPE

    /** PO 유형명 */
    @Schema(description = "PO 유형명")
    private String poTypeName;           // POTYPENAME

    /** 상태코드 */
    @Schema(description = "상태코드")
    private String status;               // STATUS

    /** 상태명 */
    @Schema(description = "상태명")
    private String statusName;           // STATUSNAME

    /* 라인 & 상품 */
    /** 문서라인 */
    @Schema(description = "문서라인")
    private String docLine;              // DOCLINE

    /** 상품코드 */
    @Schema(description = "SKU")
    private String sku;                  // SKU

    /** 상품명칭 */
    @Schema(description = "SKU명")
    private String skuName;              // SKUNAME

    /** 박스당 입수량 */
    @Schema(description = "입수량")
    private BigDecimal qtyPerBox;           // QTYPERBOX

    /** 저장유무 */
    @Schema(description = "채널")
    private String channel;              // CHANNEL

    /** 저장조건 */
    @Schema(description = "저장구분")
    private String storageType;          // STORAGETYPE

    /** 단위 */
    @Schema(description = "단위")
    private String uom;                  // UOM

    /** 분자 */
    @Schema(description = "분자")
    private BigDecimal bunja;               // BUNJA

    /** 분모 */
    @Schema(description = "분모")
    private BigDecimal bunmo;               // BUNMO

    /** 고객반품주문수량 */
    @Schema(description = "주문수량")
    private BigDecimal orderQty;            // ORDERQTY

    /** 반품검수수량 */
    @Schema(description = "검수수량")
    private BigDecimal inspectQty;          // INSPECTQTY

    /** 반품확정수량 */
    @Schema(description = "확정수량")
    private BigDecimal confirmQty;          // CONFIRMQTY

    /** 미회수수량 */
    @Schema(description = "결품수량")
    private BigDecimal shortageQty;         // SHORTAGEQTY

    /** 결품이동수량 */
    @Schema(description = "결품이동수량")
    private BigDecimal shortageTranQty;     // SHORTAGETRANQTY

    /** 반품작업수량 */
    @Schema(description = "작업수량")
    private BigDecimal tranQty;             // TRANQTY

    /* 재고 & LOC */
    /** 입고위치 */
    @Schema(description = "To LOC")
    private String toLoc;                // TOLOC

    /** 소비기한(유통/제조) */
    @Schema(description = "LOT")
    private String lotTable01;           // LOTTABLE01

    /** 소비기한(잔여/전체) */
    @Schema(description = "유통기한(잔/총)")
    private String durationTerm;         // DURATION_TERM

    /** 재고등급 */
    @Schema(description = "등급")
    private String stockGrade;           // STOCKGRADE

    /** 재고ID(바코드) */
    @Schema(description = "바코드")
    private String stockId;              // STOCKID

    /* 기타 코드 */
    /** 포장방법 */
    @Schema(description = "포장방법")
    private String packingMethod;        // PACKINGMETHOD

    /** 반품유형 */
    @Schema(description = "반품유형")
    private String returnType;           // RETURNTYPE

    /** 반품유형명 */
    @Schema(description = "반품유형명")
    private String returnTypeName;       // RETURTYPENNAME

    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasonCode;           // REASONCODE

    /** 사유내용 */
    @Schema(description = "사유내용")
    private String reasonMsg;            // REASONMSG

    /** 기타구분1 */
    @Schema(description = "OTHER01")
    private String other01;              // OTHER01

    /** 귀속부서명 */
    @Schema(description = "BLNG부서명")
    private String blngDeptName;         // BLNGDEPTNAME

    /* 판매 조직 */
    /** 주문유형 */
    @Schema(description = "주문유형")
    private String orderType;            // ORDERTYPE

    /** 주문유형명 */
    @Schema(description = "주문유형명")
    private String orderTypeName;        // ORDERTYPENAME

    /** 판매조직 */
    @Schema(description = "판매조직")
    private String saleOrganize;         // SALEORGANIZE

    /** 판매그룹 */
    @Schema(description = "판매그룹")
    private String saleGroup;            // SALEGROUP

    /** 판매부서 */
    @Schema(description = "판매부서")
    private String saleDepartment;       // SALEDEPARTMENT

    /** 고객그룹 */
    @Schema(description = "고객그룹")
    private String custGroup;            // CUSTGROUP

    /* 고객 */
    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String fromCustkey;          // FROM_CUSTKEY

    /** 거래처명 */
    @Schema(description = "거래처명")
    private String fromCustname;         // FROM_CUSTNAME

    /** 정산거래처코드 */
    @Schema(description = "정산거래처코드")
    private String billToCustkey;        // BILLTOCUSTKEY

    /** 정산거래처명 */
    @Schema(description = "정산거래처명")
    private String billToCustname;       // BILLTOCUSTNAME

    /** 기타구분3 */
    @Schema(description = "OTHER03")
    private String other03;              // OTHER03

    /** 기타구분4 */
    @Schema(description = "OTHER04")
    private String other04;              // OTHER04

    /** 협력사반품여부 */
    @Schema(description = "벤더반품여부")
    private String vendoReturn;         // VENDORETURN

    /** 구매처명 */
    @Schema(description = "매입처명")
    private String custName;             // CUSTNAME

    /* SLIP 정보 */
    /** 문서구분 */
    @Schema(description = "문서구분")
    private String docType;              // DOCTYPE

    /** SLIP 일자 */
    @Schema(description = "SLIP 일자")
    private String slipDt;               // SLIPDT

    /** SLIP 번호 */
    @Schema(description = "SLIP 번호")
    private String slipNo;               // SLIPNO

    /** SLIP 라인 */
    @Schema(description = "SLIP 라인")
    private String slipLine;             // SLIPLINE

    /* Serial / 유통 */
    /** Serial 관리여부 */
    @Schema(description = "Serial 관리여부")
    private String serialYn;             // SERIALYN

    /** 유통기간 */
    @Schema(description = "유통기간")
    private BigDecimal duration;            // DURATION

    /** 유통기간 TYPE */
    @Schema(description = "유통기간 TYPE")
    private String durationType;         // DURATIONTYPE

    /* PLANT */
    /** Plant 코드 */
    @Schema(description = "Plant 코드")
    private String plant;                // PLANT

    /** Plant 명 */
    @Schema(description = "Plant 명")
    private String plantDescr;           // PLANT_DESCR

    /* Serial Info */
    /** 검수 Serial Key */
    @Schema(description = "검수 Serial Key")
    private String inspectSerialKey;     // INSPECTSERIALKEY

    /** 강제검수여부 */
    @Schema(description = "강제검수여부")
    private String forceInspect;         // FORCEINSPECT

    /* 이력/비정량 */
    /** 이력/비정량 여부 */
    @Schema(description = "이력/비정량 여부")
    private String line01;               // LINE01

    /** 평균중량 */
    @Schema(description = "평균중량")
    private Double avgWeight;            // AVGWEIGHT

    /** 환산박스 */
    @Schema(description = "환산박스")
    private Double calBox;               // CALBOX

    /** 실박스예정 */
    @Schema(description = "실박스예정")
    private Double realOrderBox;         // REALORDERBOX

    /** 실박스확정 */
    @Schema(description = "실박스확정")
    private Double realCfmBox;           // REALCFMBOX

    /** 작업박스 */
    @Schema(description = "작업박스")
    private Double tranBox;              // TRANBOX

    /** Box Flag */
    @Schema(description = "Box Flag")
    private String boxFlag;              // BOXFLAG

    /* 가시화용 */
    /** 글자색 */
    @Schema(description = "글자색")
    private String fontColor;            // FONTCOLOR

    /** 배경색 */
    @Schema(description = "배경색")
    private String bgColor;              // BGCOLOR

    /* 상품이력 정보 */
    /** 이력번호(Serial No.) */
    @Schema(description = "이력번호(Serial No.)")
    private String serialNo;             // SERIALNO

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;              // BARCODE

    /** B/L 번호(수입 이력) */
    @Schema(description = "B/L 번호(수입 이력)")
    private String convSerialNo;         // CONVSERIALNO

    /** 도축일자(YYYYMMDD) */
    @Schema(description = "도축일자(YYYYMMDD)")
    private String butcheryDt;           // BUTCHERYDT
   

    /** 도축장 */
    @Schema(description = "도축장 명칭")
    private String factoryName;

    /** 계약유형 코드 */
    @Schema(description = "계약유형 코드")
    private String contractType;

    /** 계약업체 코드 코드 */
    @Schema(description = "계약업체 코드")
    private String contractCompany;

    /** 계약업체명 */
    @Schema(description = "계약업체명")
    private String contractCompanyName;
    
	private String fixDcCode;
}
