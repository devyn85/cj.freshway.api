package cjfw.wms.mg.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.11 
 * @description :외부비축재고변경사유현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축재고변경사유현황 조회 결과 DTO")
public class MgModifyLogExDcDetailListResDto {

	/* ───── 기본 정보 ───────────────────────────────────── */
	/** 데이터번호 */
	@Schema(description = "데이터번호", nullable = true, example = "")
	private int serialKey;

	/** 수정일자(YYYYMMDD) */
	@Schema(description = "수정일자(YYYYMMDD)", nullable = true, example = "")
	private String modifyDate;

	/** 고객사코드 */
	@Schema(description = "고객사코드", nullable = true, example = "")
	private String storerKey;

	/** 상품코드 */
	@Schema(description = "상품코드", nullable = true, example = "")
	private String sku;

	/** 상품명 */
	@Schema(description = "상품명", nullable = true, example = "")
	private String skuName;

	/** 단위(UOM) */
	@Schema(description = "단위(UOM)", nullable = true, example = "")
	private String uom;

	/* ───── FROM 정보 ──────────────────────────────────── */
	/** FROM 시리얼번호 */
	@Schema(description = "FROM 시리얼번호", nullable = true, example = "")
	private String fromSerialNo;

	/** FROM BL 번호 */
	@Schema(description = "FROM BL 번호", nullable = true, example = "")
	private String fromConvSerialNo;

	/** FROM 시리얼레벨 */
	@Schema(description = "FROM 시리얼레벨", nullable = true, example = "")
	private String fromSerialLevel;

	/** FROM 시리얼타입 */
	@Schema(description = "FROM 시리얼타입", nullable = true, example = "")
	private String fromSerialType;

	/** FROM 공장명 */
	@Schema(description = "FROM 공장명", nullable = true, example = "")
	private String fromFactoryName;

	/** FROM 원산지 */
	@Schema(description = "FROM 원산지", nullable = true, example = "")
	private String fromPlaceOfOrigin;

	/** FROM 색상명 */
	@Schema(description = "FROM 색상명", nullable = true, example = "")
	private String fromColorDescr;

	/** FROM 유효시작일 */
	@Schema(description = "FROM 유효시작일", nullable = true, example = "")
	private String fromFromValidDt;

	/** FROM 유효종료일 */
	@Schema(description = "FROM 유효종료일", nullable = true, example = "")
	private String fromToValidDt;

	/** FROM 도축일 */
	@Schema(description = "FROM 도축일", nullable = true, example = "")
	private String fromButcheryDt;

	/** FROM 계약사코드 */
	@Schema(description = "FROM 계약사코드", nullable = true, example = "")
	private String fromContractCompany;

	/** FROM 계약사명 */
	@Schema(description = "FROM 계약사명", nullable = true, example = "")
	private String fromContractCompanyName;

	/** FROM 계약유형 */
	@Schema(description = "FROM 계약유형", nullable = true, example = "")
	private String fromContractType;

	/* ───── TO 정보 ────────────────────────────────────── */
	/** TO 시리얼번호 */
	@Schema(description = "TO 시리얼번호", nullable = true, example = "")
	private String toSerialNo;

	/** TO BL 번호 */
	@Schema(description = "TO BL 번호", nullable = true, example = "")
	private String toConvSerialNo;

	/** TO 시리얼레벨 */
	@Schema(description = "TO 시리얼레벨", nullable = true, example = "")
	private String toSerialLevel;

	/** TO 시리얼타입 */
	@Schema(description = "TO 시리얼타입", nullable = true, example = "")
	private String toSerialType;

	/** TO 공장명 */
	@Schema(description = "TO 공장명", nullable = true, example = "")
	private String toFactoryName;

	/** TO 원산지 */
	@Schema(description = "TO 원산지", nullable = true, example = "")
	private String toPlaceOfOrigin;

	/** TO 색상명 */
	@Schema(description = "TO 색상명", nullable = true, example = "")
	private String toColorDescr;

	/** TO 유효시작일 */
	@Schema(description = "TO 유효시작일", nullable = true, example = "")
	private String toFromValidDt;

	/** TO 유효종료일 */
	@Schema(description = "TO 유효종료일", nullable = true, example = "")
	private String toToValidDt;

	/** TO 도축일 */
	@Schema(description = "TO 도축일", nullable = true, example = "")
	private String toButcheryDt;

	/** TO 계약사코드 */
	@Schema(description = "TO 계약사코드", nullable = true, example = "")
	private String toContractCompany;

	/** TO 계약사명 */
	@Schema(description = "TO 계약사명", nullable = true, example = "")
	private String toContractCompanyName;

	/** TO 계약유형 */
	@Schema(description = "TO 계약유형", nullable = true, example = "")
	private String toContractType;

	/* ───── ETC ───────────────────────────────────────── */
	/** 수정일시 */
	@Schema(description = "수정일시", nullable = true, example = "")
	private String editDate;

	/** 수정자 */
	@Schema(description = "수정자", nullable = true, example = "")
	private String editWho;

	/** 사용자명 */
	@Schema(description = "사용자명", nullable = true, example = "")
	private String userName;

	/** 사유코드 */
	@Schema(description = "사유코드", nullable = true, example = "")
	private String reasonCode;

	/** 사유메시지 */
	@Schema(description = "사유메시지", nullable = true, example = "")
	private String reasonMsg;

	/** 바코드 */
	@Schema(description = "바코드", nullable = true, example = "")
	private String barcode;

}
