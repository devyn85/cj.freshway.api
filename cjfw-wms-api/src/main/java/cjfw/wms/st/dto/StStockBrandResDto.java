package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.09.12
 * @description : 브랜드별재고추이 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.12    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
 */
@Data
@Schema(description = "브랜드별재고추이 조회 결과")
public class StStockBrandResDto extends CommonDto {
    
	/** 본점코드 */
	@Schema(description = "본점코드", nullable = true, example = "")
	private String custkey;
	
	/** 본점명 */
	@Schema(description = "본점명", nullable = true, example = "")
	private String description;
	
	/** 연월 */
	@Schema(description = "연월", nullable = true, example = "")
	private String inoutdt;
	
	/** 월별 재고량 */
	@Schema(description = "월별 재고량", nullable = true, example = "")
	private String monthcnt;


		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
