package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.12
 * @description : 로케이션 검색 응답 DTO
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.12 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Data
@Schema(description = "로케이션 검색 응답")
public class CmSearchLocationPopupResDto {
	// 로케이션
	@Schema(description = "로케이션", nullable = false, example = "1006692")
    private String code;
    // 내용
	@Schema(description = "내용", nullable = false, example = "내용")
    private String name;
}