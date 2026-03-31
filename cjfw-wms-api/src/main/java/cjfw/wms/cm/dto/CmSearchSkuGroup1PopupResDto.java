package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.19
 * @description : 소분류 팝업 검색 응답DTO
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.19 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Data
@Schema(description = "소분류 검색 응답")
public class CmSearchSkuGroup1PopupResDto {

	@Schema(description = "상품분류코드", nullable = false, example = "1006692")
    private String code;

	@Schema(description = "상품분류명", nullable = false, example = "내용")
    private String name;
}