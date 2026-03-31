package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.12
 * @description : 배송권역 검색 응답 DTO
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.12 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Data
@Schema(description = "배송권역 검색 응답")
public class CmSearchDistrictPopupResDto {

	@Schema(description = "배송권역", nullable = false, example = "1006692")
    private String code;

	@Schema(description = "배송 권역명", nullable = false, example = "내용")
    private String name;

	@Schema(description = "배송그룹", nullable = false, example = "권역그룹")
    private String districttype;

	@Schema(description = "권역그룹", nullable = false, example = "권역그룹")
    private String districtgroup;
}