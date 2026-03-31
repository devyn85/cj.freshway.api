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
public class OmStockReocationAIReqDto {
	
	/** 작업 고유 식별자 */
	@Schema(description = "작업 고유 식별자", example = "YYYYMMDDHHmm")
	private String job_id;
	
	/** 분석 기준일자 */
	@Schema(description = "분석 기준일자", example = "YYYYMMDD")
	private String date_str;
	
	/** 결과 수신 Webhook URL */
	@Schema(description = "결과 수신 Webhook URL", example = "")
	private String callback_url;
	
	/** 설정값 */
	@Schema(description = "설정값", example = "")
	private Filters filters;
	
	private static class Filters {
		
		/** 분석 대상 상품코드 */
	    @Schema(description = "분석 대상 상품코드", example = "")
        private String select_items;
	    
	    /** 이체량(KG) 최소값 */
	    @Schema(description = "이체량(KG) 최소값", example = "")
        private Integer flow_low;
	    
	    /** 이체량(KG) 최대값 */
	    @Schema(description = "이체량(KG) 최대값", example = "")
        private Integer flow_high;
	    
	    /** 수송비 가중치 */
	    @Schema(description = "수송비 가중치", example = "")
        private Float transport_weight;
	    
	    /** 재고(PLT) 가중치 */
	    @Schema(description = "재고(PLT) 가중치", example = "")
        private Float inventory_weight;
	    
	    /** asis 현재 배치 현황 필터 */
	    @Schema(description = "asis 현재 배치 현황 필터", example = "")
        private List<String> current_position;
	    
	    /** asis 양산센터 데이터 제외 여부 */
	    @Schema(description = "asis 양산센터 데이터 제외 여부", example = "")
        private boolean except_yangsan_current;
	    
	    /** tobe 목표 배치 요건 필터 */
	    @Schema(description = "tobe 목표 배치 요건 필터", example = "")
        private List<String> target_position;
	    
	    /** tobe 양산센터 배치 금지 여부 */
	    @Schema(description = "tobe 양산센터 배치 금지 여부", example = "")
        private boolean except_yangsan_target;
	    
	    /** 케파 설정 사용 여부 */
	    @Schema(description = "케파 설정 사용 여부", example = "")
        private boolean target_capacity;
	    
	    /** 센터별 상세 CAPA 값 */
	    @Schema(description = "센터별 상세 CAPA 값", example = "")
        private List<HiddenInput> hidden_inputs;
    }
	
	private static class HiddenInput {
		
		/** 센터명 */
	    @Schema(description = "센터명", example = "센터명_저장조건")
		private String select;
	    
	    /** capa 수량 */
	    @Schema(description = "capa 수량", example = "")
        private String text;
    }	

}
