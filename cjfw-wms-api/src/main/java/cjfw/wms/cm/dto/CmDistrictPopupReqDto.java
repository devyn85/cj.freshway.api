package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.08.20 
 * @description : 배송권역조회 팝업 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송권역 조회(팝업) 요청 DTO")
public class CmDistrictPopupReqDto extends CommonDto {
	@Schema(description = "01(배송권역코드) 또는 서울(배송권역명)", example="01 또는 서울")
	private String searchVal;
}
