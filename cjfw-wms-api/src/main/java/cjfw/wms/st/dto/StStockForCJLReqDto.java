package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.09.12 
 * @description : 저장품재고조회(CJ대한통운제공) 조회 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.12    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "저장품재고조회(CJ대한통운제공) 조회 요청") 
public class StStockForCJLReqDto extends CommonDto {	

    /** id */
	@Schema(description = "id", nullable = true, example = "")
	private String id = "FX";
	
	/** trName */
	@Schema(description = "trName", nullable = true, example = "")
	private String trName = "WEB_LOC_TO_FX";
	    
	/** pl */
	@Schema(description = "pl", nullable = true, example = "")
	private String pl = "1000";
	
	/** 센터코드 */
	@Schema(description = "센터코드", nullable = true, example = "")
	private String sl;
	
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = true, example = "")
	private String sku;    
	    
}
