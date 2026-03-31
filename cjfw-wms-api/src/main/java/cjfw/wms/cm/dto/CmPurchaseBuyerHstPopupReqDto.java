package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.05.15 
 * @description : 수급담당 변경이력 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.15 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name = "공통 검색", description = "공통 검색")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "수급담당 변경이력 조회 요청")
public class CmPurchaseBuyerHstPopupReqDto extends CommonDto {
	@Schema(description = "물류센터코드", example = "1000")
	private String dcCode;
	
	@Schema(description = "상품코드", example = "100073")
	private String sku;
}
