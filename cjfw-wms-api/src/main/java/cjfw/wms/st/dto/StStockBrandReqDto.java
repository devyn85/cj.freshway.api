package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.09.12 
 * @description : 브랜드별재고추이 조회 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.12    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "브랜드별재고추이 조회 요청") 
public class StStockBrandReqDto extends CommonDto {	

    /** 본점코드 */
	@Schema(description = "본점코드", nullable = true, example = "")
	private String custkey;
	
	/** 거래처코드 */
	@MultiSearch
	@Schema(description = "거래처코드-다중OR검색", example = "")
	private List<List<String>> custkeyMulti;
	
	/** 조회시작월 */
	@Schema(description = "조회시작월", nullable = true, example = "")
	private String fromdt;
	    
	/** 조회종료월 */
	@Schema(description = "조회종료월", nullable = true, example = "")
	private String todt;
	    
	    
}
