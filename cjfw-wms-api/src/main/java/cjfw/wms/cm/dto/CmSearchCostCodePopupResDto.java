package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2025.08.09
 * @description : 코스트코드 검색 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "코스트코드 검색 응답")
public class CmSearchCostCodePopupResDto {
	/** 코드 */
	@Schema(description = "코드", nullable = false, example = "")
	private String code;
	
	/** 명 */
	@Schema(description = "명", nullable = false, example = "")
	private String name;
	
	/** DATA1 */
    @Schema(description = "DATA1", nullable = false, example = "")
    private String data1;
    
    /** DATA2 */
    @Schema(description = "DATA3", nullable = false, example = "")
    private String data2;
    
    /** DATA3 */
    @Schema(description = "DATA3", nullable = false, example = "")
    private String data3;
    
    /** DATA4 */
    @Schema(description = "DATA4", nullable = false, example = "")
    private String data4;
	
}