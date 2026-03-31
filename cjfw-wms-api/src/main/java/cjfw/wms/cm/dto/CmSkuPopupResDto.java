package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.05.09 
 * @description : 상품 코드 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class CmSkuPopupResDto {
	/** 체크여부 */
	@Schema(description = "체크여부", example = "0")
	private String checkyn;
	
	/** 상품코드 */
	@Schema(description = "상품코드", example = "342182")
	private String code;

	/** 기본 제품명 없을경우 SKU코드 */
	@Schema(description = "기본 제품명 없을경우 SKU코드", example = "한우목심")
	private String name;
	
	/** 기본 단위 */
	@Schema(description = "기본 단위", example = "EA")
	private String baseuom;

    /** 박스당 낱개수 */
    @Schema(description = "박스당 낱개수", example = "1")
    private int qtyPerBox;
    
	/** 저장조건 */
	@Schema(description = "저장조건", example = "")
	private String storagetype;
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스")
	private String customRowCheckYn = "N";
	
	
		
}