package cjfw.wms.rt.dto;

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
@Schema(description = "외부비축반품확정(입고내역) 마스터 조회 결과")
public class RtReceiptConfirmExdcMasterResDto extends CommonProcedureDto{
	/* 체크 & 기본 */
	/** 체크여부(고정 0) */
	@Schema(description = "체크여부(고정 0)")
	private String checkYn;

	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dcCode;

	/** 센터명 */
	@Schema(description = "센터명")
	private String dcName;

	/* 문서/조직 */
	/** 출고문서번호 */
	@Schema(description = "출고문서번호")
	private String docnoWd;

	/** 조직코드 */
	@Schema(description = "조직코드")
	private String organize;

	/** 조직명 */
	@Schema(description = "조직명")
	private String organizeName;

	/** 창고코드 */
	@Schema(description = "창고코드")
	private String area;

	/** 반품문서번호 */
	@Schema(description = "반품문서번호")
	private String docNo;

	/** 확정일자 */
	@Schema(description = "확정일자")
	private String confirmDate;

	/** 고객코드 */
	@Schema(description = "고객코드")
	private String storerkey;

	/** 문서일자 */
	@Schema(description = "문서일자")
	private String docDt;

	/* 발주유형/상태 */
	/** PO 유형 */
	@Schema(description = "PO 유형")
	private String poType;

	/** PO 유형명 */
	@Schema(description = "PO 유형명")
	private String poTypeName;

	/** 상태코드 */
	@Schema(description = "상태코드")
	private String status;

	/** 상태명 */
	@Schema(description = "상태명")
	private String statusName;

	/* 라인 & 상품 */
	/** 문서라인 */
	@Schema(description = "문서라인")
	private String docLine;

	/** SKU */
	@Schema(description = "SKU")
	private String sku;

	/** SKU명 */
	@Schema(description = "SKU명")
	private String skuName;

	/** 입수량 */
	@Schema(description = "입수량")
	private Integer qtyPerBox;

	/** 채널 */
	@Schema(description = "채널")
	private String channel;

	/** 저장구분 */
	@Schema(description = "저장구분")
	private String storageType;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 분자 */
	@Schema(description = "분자")
	private String bunJa;

	/** 분모 */
	@Schema(description = "분모")
	private String bunMo;

	/** 주문수량 */
	@Schema(description = "주문수량")
	private Double orderQty;

	/** 검수수량 */
	@Schema(description = "검수수량")
	private Double inspectQty;

	/** 확정수량 */
	@Schema(description = "확정수량")
	private Double confirmQty;

	/** 결품수량 */
	@Schema(description = "결품수량")
	private Double shortageQty;

	/** 결품이동수량 */
	@Schema(description = "결품이동수량")
	private Double shortageTranQty;

	/** 작업수량 */
	@Schema(description = "작업수량")
	private Double tranQty;

	/* 재고 & LOC */
	/** To LOC */
	@Schema(description = "To LOC")
	private String toLoc;

	/** LOT */
	@Schema(description = "LOT")
	private String lotTable01;

	/** 유통기한(잔/총) */
	@Schema(description = "유통기한(잔/총)")
	private String durationTerm;

	/** 등급 */
	@Schema(description = "등급")
	private String stockGrade;

	/** 바코드 */
	@Schema(description = "바코드")
	private String stockId;

	/* 기타 코드 */
	/** 포장방법 */
	@Schema(description = "포장방법")
	private String packingMethod;

	/** 반품유형 */
	@Schema(description = "반품유형")
	private String returnType;

	/** 반품유형명 */
	@Schema(description = "반품유형명")
	private String returTypeName;

	/** 사유코드 */
	@Schema(description = "사유코드")
	private String reasonCode;

	/** 사유내용 */
	@Schema(description = "사유내용")
	private String reasonMsg;

	/** OTHER01 */
	@Schema(description = "OTHER01")
	private String other01;

	/** BLNG부서명 */
	@Schema(description = "BLNG부서명")
	private String blngDeptName;

	/* 판매 조직 */
	/** 주문유형 */
	@Schema(description = "주문유형")
	private String orderType;

	/** 주문유형명 */
	@Schema(description = "주문유형명")
	private String orderTypeName;

	/** 판매조직 */
	@Schema(description = "판매조직")
	private String saleOrganize;

	/** 판매그룹 */
	@Schema(description = "판매그룹")
	private String saleGroup;

	/** 판매부서 */
	@Schema(description = "판매부서")
	private String saleDepartment;

	/** 고객그룹 */
	@Schema(description = "고객그룹")
	private String custGroup;

	/* 고객 */
	/** 거래처코드 */
	@Schema(description = "거래처코드")
	private String fromCustKey;

	/** 거래처명 */
	@Schema(description = "거래처명")
	private String fromCustName;

	/** 정산거래처코드 */
	@Schema(description = "정산거래처코드")
	private String billToCustKey;

	/** 정산거래처명 */
	@Schema(description = "정산거래처명")
	private String billToCustName;

	/** OTHER03 */
	@Schema(description = "OTHER03")
	private String other03;

	/** OTHER04 */
	@Schema(description = "OTHER04")
	private String other04;

	/** 벤더반품여부 */
	@Schema(description = "벤더반품여부")
	private String vendorReturn;

	/** 매입처명 */
	@Schema(description = "매입처명")
	private String custName;

	/* SLIP 정보 */
	/** 문서구분 */
	@Schema(description = "문서구분")
	private String docType;

	/** SLIP 일자 */
	@Schema(description = "SLIP 일자")
	private String slipDt;

	/** SLIP 번호 */
	@Schema(description = "SLIP 번호")
	private String slipNo;

	/** SLIP 라인 */
	@Schema(description = "SLIP 라인")
	private String slipLine;

	/* Serial / 유통 */
	/** Serial 관리여부 */
	@Schema(description = "Serial 관리여부")
	private String serialYn;

	/** 유통기간 */
	@Schema(description = "유통기간")
	private Integer duration;

	/** 유통기간 TYPE */
	@Schema(description = "유통기간 TYPE")
	private String durationType;

	/* PLANT */
	/** Plant 코드 */
	@Schema(description = "Plant 코드")
	private String plant;

	/** Plant 명 */
	@Schema(description = "Plant 명")
	private String plantDescr;

	/* Serial Info */
	/** 검수 Serial Key */
	@Schema(description = "검수 Serial Key")
	private String inspectSerialKey;

	/** 강제검수여부 */
	@Schema(description = "강제검수여부")
	private String forceInspect;

	/* 이력/비정량 */
	/** 이력/비정량 여부 */
	@Schema(description = "이력/비정량 여부")
	private String line01;

	/** 평균중량 */
	@Schema(description = "평균중량")
	private Double avgWeight;

	/** 환산박스 */
	@Schema(description = "환산박스")
	private Double calBox;

	/** 실박스예정 */
	@Schema(description = "실박스예정")
	private Double realOrderBox;

	/** 실박스확정 */
	@Schema(description = "실박스확정")
	private Double realCfmBox;

	/** 작업박스 */
	@Schema(description = "작업박스")
	private Double tranBox;

	/** Box Flag */
	@Schema(description = "Box Flag")
	private String boxFlag;

	/* 가시화용 */
	/** 글자색 */
	@Schema(description = "글자색")
	private String fontColor;

	/** 배경색 */
	@Schema(description = "배경색")
	private String bgColor;

	/** 이력번호(Serial No.) */
	@Schema(description = "이력번호(Serial No.)")
	private String serialNo;

	/** 바코드 */
	@Schema(description = "바코드")
	private String barcode;

	/** B/L 번호(수입 이력) */
	@Schema(description = "B/L 번호(수입 이력)")
	private String convSerialNo;

	/** 도축일자(YYYYMMDD) */
	@Schema(description = "도축일자(YYYYMMDD)")
	private String butcheryDt;

	/** 도축장 명칭 */
	@Schema(description = "도축장 명칭")
	private String factoryName;

	/** 계약유형 코드 */
	@Schema(description = "계약유형 코드")
	private String contractType;

	/** 계약업체 코드 */
	@Schema(description = "계약업체 코드")
	private String contractCompany;

	/** 계약업체명 */
	@Schema(description = "계약업체명")
	private String contractCompanyName;
	
	private String fixDcCode;
    
    
}
