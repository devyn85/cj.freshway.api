package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.26 
 * @description :외부창고요율관리 저장 요청 DTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.26 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "외부창고요율관리 저장 요청 DTO")
public class MsExDcRateSaveReqDto extends CommonDto {

	/** 데이터번호 */
	@Schema(description = "데이터번호", nullable = true, example = "10001")
	private String serialKey;

	private String fixDcCode;
//	/** 고객코드 */
//	@Schema(description = "고객코드", nullable = true, example = "ST001")
//	private String storerKey;

	/** 고객코드 */
	@Schema(description = "고객코드", nullable = true, example = "ST001")
	private String storerkey;

	/** 시작일자 */
	@Schema(description = "시작일자", nullable = true, example = "2025-06-01")
	private String fromDate;

	/** 종료일자 */
	@Schema(description = "종료일자", nullable = true, example = "2025-06-30")
	private String toDate;

	/** 물류센터 */
	@Schema(description = "물류센터", nullable = true, example = "DC01")
	private String dcCode;

	/** 창고 */
	@Schema(description = "창고", nullable = true, example = "2170-A1")
	private String organize;

	/** 상품스펙코드 */
	@Schema(description = "상품스펙코드", nullable = true, example = "SPC001")
	private String specCode;

	/** 상품코드 */
	@Schema(description = "상품코드", nullable = true, example = "SKU12345")
	private String sku;

	/** 거래처코드 */
	@Schema(description = "거래처코드", nullable = true, example = "CUST01")
	private String custKey;

	/** 입출고비정산구분 */
	@Schema(description = "입출고비정산구분", nullable = true, example = "01")
	private String expenseType;

	/** 1순위 입고비 */
	@Schema(description = "1순위 입고비", nullable = true, example = "100")
	private BigDecimal grPrice;

	/** 1순위 출고비 */
	@Schema(description = "1순위 출고비", nullable = true, example = "200")
	private BigDecimal giPrice;

	/** 1순위 창고료 */
	@Schema(description = "1순위 창고료", nullable = true, example = "300")
	private BigDecimal storagePrice;

	/** 1순위 이체입고료 */
	@Schema(description = "1순위 이체입고료", nullable = true, example = "150")
	private BigDecimal stoGrPrice;

	/** 1순위 이체출고료 */
	@Schema(description = "1순위 이체출고료", nullable = true, example = "160")
	private BigDecimal stoGiPrice;

	/** 2순위 입고비 */
	@Schema(description = "2순위 입고비", nullable = true, example = "90")
	private BigDecimal grPriceLower;

	/** 2순위 출고비 */
	@Schema(description = "2순위 출고비", nullable = true, example = "180")
	private BigDecimal giPriceLower;

	/** 2순위 창고료 */
	@Schema(description = "2순위 창고료", nullable = true, example = "280")
	private BigDecimal storagePriceLower;

	/** 2순위 이체입고료 */
	@Schema(description = "2순위 이체입고료", nullable = true, example = "130")
	private BigDecimal stoGrPriceLower;

	/** 2순위 이체출고료 */
	@Schema(description = "2순위 이체출고료", nullable = true, example = "140")
	private BigDecimal stoGiPriceLower;

	/** 우선순위 */
	@Schema(description = "우선순위", nullable = true, example = "10")
	private String exDcRateRank;

	/** 저장조건 */
	@Schema(description = "저장조건", nullable = true, example = "냉동")
	private String storageType;

	/** 삭제여부 */
	@Schema(description = "삭제여부", nullable = true, example = "N")
	private String delYn;

	/** 등록자 ID */
	@Schema(description = "등록자 ID", nullable = true, example = "admin")
	private String addWho;

	/** 수정자 ID */
	@Schema(description = "수정자 ID", nullable = true, example = "admin")
	private String editWho;

	/** 작업료 */
	@Schema(description = "작업료", nullable = true, example = "0")
	private BigDecimal workPrice;

	/** 용차료 */
	@Schema(description = "용차료", nullable = true, example = "0")
	private BigDecimal truckPrice;

	/** 행 상태 (I:신규, U:수정, D:삭제) */
	@Schema(description = "행 상태 (I:신규, U:수정, D:삭제)", nullable = true, example = "I")
	private String rowStatus;

	/** 창고단가 UOM */
	@Schema(description = "창고단가 UOM", nullable = true, example = "")
	private String areaPriceUom;

	/** 계근료 */
	@Schema(description = "계근료", nullable = true, example = "")
	private BigDecimal wghPrice;
	
	/** 상태값 */
	@Schema(description = "상태값", nullable = true, example = "")
	private String state;
	
	/** 팔렛트단가 */
	@Schema(description = "팔렛트단가", nullable = true, example = "")
	private String pltPrice;
	
	/** 팔렛트단가 */
	@Schema(description = "팔렛트단가", nullable = true, example = "")
	private String storageTypeSku;
}
