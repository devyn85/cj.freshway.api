package cjfw.wms.om.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.24 
 * @description : 저장품자동발주관리 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class OmStockReocationCompareResDto {
    
    /** TO-BE (재배치 이후) 원화 개수 */
	@Schema(description = "TO-BE (재배치 이후) 원화 개수", example = "")
	private String toBeConversionCount;

	/** AS-IS (재배치 이전) 원화 개수 */
	@Schema(description = "AS-IS (재배치 이전) 원화 개수", example = "")
	private String asIsConversionCount;

	/** 해당 변화를 겪은 SKU 개수 */
	@Schema(description = "해당 변화를 겪은 SKU 개수", example = "")
	private String countOfSkus;
		
}