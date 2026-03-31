package cjfw.wms.cm.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonPopupDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.05.09 
 * @description : 상품 코드 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class CmSkuPopupReqDto extends CommonPopupDto {
	/**코드*/
    @Schema(description = "code")
    private String code;
	/** 명 */
	@Schema(description = "명", nullable = false)
	private String name;	
	
	/** 다중선택 */
    @Schema(description = "다중 선택")
	private String multiSelect;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> multiSelectMulti;       
	
	@Schema(description = "999개 청크 목록(OR IN 생성용)")
	private java.util.List<java.util.List<String>> codeGroups;
	
	@Schema(description = "KIT상품여부")
	private String kit = "N";
	
	/** 호출하는곳(1:화주상품,2:KIT상품") */
	@Schema(description = "호출하는곳(1:화주상품,2:KIT상품")
	private String callFrom;
	
	/** 창고 */
	@Schema(description = "창고")
	private String organize;
	
	/** 거래처코드 */
	@Schema(description = "거래처코드")
	private String custkey ;
	
	/** 전체데이터검색여부 */
	@Schema(description = "전체데이터검색여부")
	private String allDataYn;

}
