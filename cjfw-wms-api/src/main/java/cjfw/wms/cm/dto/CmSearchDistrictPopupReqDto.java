package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.13
 * @description : 배송권역 검색 요청 DTO
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.12 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송권역 검색 팝업 요청")
public class CmSearchDistrictPopupReqDto extends CommonDto{

	@Schema(description = "배송권역코드/명", nullable = false, example = "2600")
	private String name;

	@Schema(description = "다중선택 물류센터코드/명", nullable = false, example = "")
    private String multiSelect;

	@Schema(description = "코드리스트", nullable = false, example = "")
    private String[] codeList;
}
