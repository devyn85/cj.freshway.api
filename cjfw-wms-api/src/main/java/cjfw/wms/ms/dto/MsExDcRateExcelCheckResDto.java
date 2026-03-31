package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.06.04 
 * @description : 외부창고 요율 평균치 체크 응답 DTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.04 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "외부창고 요율 평균치 체크 응답 DTO")
public class MsExDcRateExcelCheckResDto extends CommonDto{
	  /** 체크 여부 */
    @Schema(description = "체크 여부", nullable = true, example = "")
    private String checkYn;
    private String fixDcCode;
    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = true, example = "")
    private Long serialKey;

    /** 고객사코드 */
    @Schema(description = "고객사코드", nullable = true, example = "")
    private String storerKey;

    /** 시작일자 */
    @Schema(description = "시작일자", nullable = true, example = "")
    private String fromDate;

    /** 종료일자 */
    @Schema(description = "종료일자", nullable = true, example = "")
    private String toDate;

    /** 물류센터 */
    @Schema(description = "물류센터", nullable = true, example = "")
    private String dcCode;

    /** 센터명 */
    @Schema(description = "센터명", nullable = true, example = "")
    private String dcName;

    /** 창고 */
    @Schema(description = "창고", nullable = true, example = "")
    private String organize;

    /** 창고명 */
    @Schema(description = "창고명", nullable = true, example = "")
    private String organizeNm;

    /** 상품스펙코드 */
    @Schema(description = "상품스펙코드", nullable = true, example = "")
    private String specCode;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = true, example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", nullable = true, example = "")
    private String skuName;

    /** 기본단위 */
    @Schema(description = "기본단위", nullable = true, example = "")
    private String baseUom;

    /** 고객코드 */
    @Schema(description = "고객코드", nullable = true, example = "")
    private String custKey;

    /** 고객명 */
    @Schema(description = "고객명", nullable = true, example = "")
    private String custname;

    /** 입출고비정산구분 */
    @Schema(description = "입출고비정산구분", nullable = true, example = "")
    private String expenseType;

    /** 입고료 */
    @Schema(description = "입고료", nullable = true, example = "")
    private BigDecimal grPrice;

    /** 출고료 */
    @Schema(description = "출고료", nullable = true, example = "")
    private BigDecimal giPrice;

    /** 창고료 */
    @Schema(description = "창고료", nullable = true, example = "")
    private BigDecimal storagePrice;

    /** 이체입고비 */
    @Schema(description = "이체입고비", nullable = true, example = "")
    private BigDecimal stoGrPrice;

    /** 이체출고비 */
    @Schema(description = "이체출고비", nullable = true, example = "")
    private BigDecimal stoGiPrice;

    /** 작업료 */
    @Schema(description = "작업료", nullable = true, example = "")
    private BigDecimal workPrice;

    /** 용차료 */
    @Schema(description = "용차료", nullable = true, example = "")
    private BigDecimal truckPrice;

    /** 상태 */
    @Schema(description = "상태", nullable = true, example = "")
    private String status;

    /** 삭제여부 */
    @Schema(description = "삭제여부", nullable = true, example = "")
    private String delYn;

    /** 등록일시 */
    @Schema(description = "등록일시", nullable = true, example = "")
    private String addDate;

    /** 수정일시 */
    @Schema(description = "수정일시", nullable = true, example = "")
    private String editDate;

    /** 등록자 ID */
    @Schema(description = "등록자 ID", nullable = true, example = "")
    private String addWho;

    /** 수정자 ID */
    @Schema(description = "수정자 ID", nullable = true, example = "")
    private String editWho;

    /** 1순위 입고비 */
    @Schema(description = "1순위 입고비", nullable = true, example = "")
    private BigDecimal grPriceUpper;

    /** 1순위 출고비 */
    @Schema(description = "1순위 출고비", nullable = true, example = "")
    private BigDecimal giPriceUpper;

    /** 1순위 창고료 */
    @Schema(description = "1순위 창고료", nullable = true, example = "")
    private BigDecimal storagePriceUpper;

    /** 1순위 이체입고료 */
    @Schema(description = "1순위 이체입고료", nullable = true, example = "")
    private BigDecimal stoGrPriceUpper;

    /** 1순위 이체출고료 */
    @Schema(description = "1순위 이체출고료", nullable = true, example = "")
    private BigDecimal stoGiPriceUpper;

    /** 2순위 입고비 */
    @Schema(description = "2순위 입고비", nullable = true, example = "")
    private BigDecimal grPriceLower;

    /** 2순위 출고비 */
    @Schema(description = "2순위 출고비", nullable = true, example = "")
    private BigDecimal giPriceLower;

    /** 2순위 창고료 */
    @Schema(description = "2순위 창고료", nullable = true, example = "")
    private BigDecimal storagePriceLower;

    /** 2순위 이체입고료 */
    @Schema(description = "2순위 이체입고료", nullable = true, example = "")
    private BigDecimal stoGrPriceLower;

    /** 2순위 이체출고료 */
    @Schema(description = "2순위 이체출고료", nullable = true, example = "")
    private BigDecimal stoGiPriceLower;

    /** 우선순위 */
    @Schema(description = "우선순위", nullable = true, example = "")
    private String exDcRateRank;

    /** 실중량 */
    @Schema(description = "실중량", nullable = true, example = "")
    private BigDecimal netWeight;

    /** 박스당 낱개수 */
    @Schema(description = "박스당 낱개수", nullable = true, example = "")
    private BigDecimal qtyPerBox;

    /** 저장조건 */
    @Schema(description = "저장조건", nullable = true, example = "")
    private String storageType;

    /** 저장조건(SKU) */
    @Schema(description = "저장조건(SKU)", nullable = true, example = "")
    private String storageTypeSku;

    /** 1순위 입고비 환산 */
    @Schema(description = "1순위 입고비 환산", nullable = true, example = "")
    private BigDecimal grPriceUpperTrans;

    /** 1순위 출고비 환산 */
    @Schema(description = "1순위 출고비 환산", nullable = true, example = "")
    private BigDecimal giPriceUpperTrans;

    /** 1순위 창고료 환산 */
    @Schema(description = "1순위 창고료 환산", nullable = true, example = "")
    private BigDecimal storagePriceUpperTrans;

    /** 1순위 이체입고료 환산 */
    @Schema(description = "1순위 이체입고료 환산", nullable = true, example = "")
    private BigDecimal stoGrPriceUpperTrans;

    /** 1순위 이체출고료 환산 */
    @Schema(description = "1순위 이체출고료 환산", nullable = true, example = "")
    private BigDecimal stoGiPriceUpperTrans;

    /** 2순위 입고비 환산 */
    @Schema(description = "2순위 입고비 환산", nullable = true, example = "")
    private BigDecimal grPriceLowerTrans;

    /** 2순위 출고비 환산 */
    @Schema(description = "2순위 출고비 환산", nullable = true, example = "")
    private BigDecimal giPriceLowerTrans;

    /** 2순위 창고료 환산 */
    @Schema(description = "2순위 창고료 환산", nullable = true, example = "")
    private BigDecimal storagePriceLowerTrans;

    /** 2순위 이체입고료 환산 */
    @Schema(description = "2순위 이체입고료 환산", nullable = true, example = "")
    private BigDecimal stoGrPriceLowerTrans;

    /** 2순위 이체출고료 환산 */
    @Schema(description = "2순위 이체출고료 환산", nullable = true, example = "")
    private BigDecimal stoGiPriceLowerTrans;

    /** 팔렛당 박스수 */
    @Schema(description = "팔렛당 박스수", nullable = true, example = "")
    private BigDecimal boxPerPlt;

    /** 팔렛당 층수 */
    @Schema(description = "팔렛당 층수", nullable = true, example = "")
    private BigDecimal layerPerPlt;

    /** 창고단가 UOM */
    @Schema(description = "창고단가 UOM", nullable = true, example = "")
    private String areaPriceUom;

    /** 계근료 */
    @Schema(description = "계근료", nullable = true, example = "")
    private BigDecimal wghPrice;
    
    /** 파렛트단가 */
    @Schema(description = "파렛트단가", nullable = true, example = "")
    private BigDecimal pltPrice;
	    
	/** 업로드확인 */
	@Schema(description = "업로드 확인", nullable = true, example = "")
	private String uploadFlag;

	/** 업로드확인 */
	@Schema(description = "업로드 확인", nullable = true, example = "")
	private String state;

	/** 업로드메시지 */
	@Schema(description = "업로드 메시지", nullable = true, example = "")
	private String uploadMsg;

	private String errMsg;
	
	private String idx;

}
