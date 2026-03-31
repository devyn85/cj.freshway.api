package cjfw.wms.cm.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonPopupDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2025.05.09 
 * @description : 운송사 검색 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "운송사 검색 요청")
public class CmCarrierPopupReqDto extends CommonPopupDto {
	/** 운송사코드/명 */
	@Schema(description = "운송사코드/명", nullable = false, example = "")
	private String searchVal;

	/** 운송사코드/명 */
	@Schema(description = "운송사코드/명", nullable = false, example = "")
	private String name;
    
    /** 운송사유형 */
	@Schema(description = "운송사유형", example = "LOCAL")
	private String carrierType;
	
	/** 운송사코드/명 */
	@Schema(description = "운송사유형활성화여부", example = "")
	private String carrierTypeHiddenYn;	
	
	/** 다중선택 */
	@Schema(description = "다중 선택", example = "")
	private String multiSelect;
	
    /** 운송사(다중검색) */
	@MultiSearch
    @Schema(description = "운송사-다중검색")
    private List<String> multiSelectMulti;    	
   
}
