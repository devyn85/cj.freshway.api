package cjfw.wms.cm.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonPopupDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.12
 * @description : 로케이션 검색 요청 DTO
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.12 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Data
@Schema(description = "로케이션 검색 팝업 요청")
public class CmSearchLocationPopupReqDto extends CommonPopupDto {
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;	
    
	/** 코드 */
	@Schema(description = "코드")
	private String code;
	
	/** 명 */
	@Schema(description = "명", nullable = false)
	private String name;	
	
	/** 다중선택 */
    @Schema(description = "다중 선택")
	private String multiSelect;
    
    /** 로케이션(다중검색) */
	@MultiSearch
    @Schema(description = "로케이션-다중검색")
    private List<String> multiSelectMulti;    


}
