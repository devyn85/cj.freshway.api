package cjfw.wms.om.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.24 
 * @description : 저장품자동발주관리 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OmStockReocationReqDto extends CommonProcedureDto {
	
	/** 작업 고유 식별자 */
	@Schema(description = "작업 고유 식별자", example = "YYYYMMDDHHmm")
    private String reqNo;
	
	/** 배송일 */
	@Schema(description = "배송일", example = "")
	private String deliverydate;
	
	/** 상세코드 */
	@Schema(description = "상세코드", example = "")
	private String confCd;
	
	/** 기타설정구분코드(1-대상고객,2-대상마감유형,3-대상원거리유형) */
	@Schema(description = "기타설정구분코드(1-대상고객,2-대상마감유형,3-대상원거리유형)", example = "")
	private String purchaseConfCd;
	
	/** 상품코드 */
	@Schema(description = "상품코드", example = "")
	private String sku;
	
	/** 상품코드 */
	@MultiSearch
	@Schema(description = "상품코드", example = "")
	private List<String> skuMulti;
	
	/** ai req dto */
	@Schema(description = "ai req dto", example = "")
	private ApiRequestDto aiDto;
	
	@Data
	public static class ApiRequestDto {
		
		/** 작업 고유 식별자 */
		@Schema(description = "작업 고유 식별자", example = "YYYYMMDDHHmm")
	    private String jobId;
		
		/** 분석 기준일자 */
		@Schema(description = "분석기준일자", example = "YYYYMMDD")
	    private String dateStr;
		
		/** 호출환경 */
		@Schema(description = "호출환경", example = "dev,qa,prod")
	    private String environment;
				
		/** 최적화 엔진 설정값 */
		@Schema(description = "최적화 엔진 설정값", example = "")
	    private FilterDto filters;
	}

	@Data
	public static class FilterDto {
		
		/** 분석 대상 상품코드 */
		@Schema(description = "분석 대상 상품코드", example = "")
	    private String selectItems = "";
		
		/** 이체량(kg) 최소값 */
		@Schema(description = "이체량(kg) 최소값", example = "")
	    private Number flowLow = 0;
		
		/** 이체량(kg) 최대값 */
		@Schema(description = "이체량(kg) 최대값", example = "")
	    private Number flowHigh = 10000;
		
		/** 수송비 가중치 */
		@Schema(description = "수송비 가중치", example = "")
	    private Float transportWeight;
		
		/** 재고(PLT) 가중치 */
		@Schema(description = "재고(PLT) 가중치", example = "")
	    private Float inventoryWeight;
		
		/** asis 현재 배치 현황 필터 */
		@Schema(description = "asis 현재 배치 현황 필터", example = "")
	    private List<String> currentPosition;
		
		/** asis 양산센터 데이터 제외 여부 */
		@Schema(description = "asis 양산센터 데이터 제외 여부", example = "")
	    private boolean exceptYangsanCurrent;
	    
		/** tobe 목표 배치 요건 필터 */
		@Schema(description = "tobe 목표 배치 요건 필터", example = "")
		private List<String> targetPosition;
	    
		/** tobe 양산센터 배치 금지 여부 */
		@Schema(description = "tobe 양산센터 배치 금지 여부", example = "")
	    private boolean exceptYangsanTarget;
		
		/** 캐파 설정 사용 여부 */
		@Schema(description = "캐파 설정 사용 여부", example = "")
	    private boolean targetCapacity = false;
		
		/** 센터별 상세 capa 값 */
		@Schema(description = "센터별 상세 capa 값", example = "")
	    private List<HiddenInputDto> hiddenInputs;
	}

	@Data
	public static class HiddenInputDto {
		
		/** 센터_저장조건 */
		@Schema(description = "센터_저장조건", example = "")
	    private String select;
		
		/** 설정할 capa 수량 */
		@Schema(description = "설정할 capa 수량", example = "")
	    private String text;
	}
}
