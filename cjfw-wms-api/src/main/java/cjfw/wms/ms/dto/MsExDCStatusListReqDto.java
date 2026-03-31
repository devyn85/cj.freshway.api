package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.30
 * @description : 저장위치정보 목록 조회 요청 dto
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.30 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "저장위치정보 요청")
public class MsExDCStatusListReqDto extends CommonDto {

    /** 기준일FROM */
	@Schema(description = "기준일FROM")
	private String fromDt;

	/** 기준일TO */
	@Schema(description = "기준일TO")
	private String thruDt;

    /** 저장조건 */
    @Schema(description = "저장조건 코드", example = "ST01")
    private String storagetype;

    /** 창고 */
    @Schema(description = "창고 코드", example = "ORG001")
    private String organize;

    /** 권역 */
    @Schema(description = "권역명", example = "강남구")
    private String district;

    /** 지역 */
    @Schema(description = "지역명", example = "서울")
    private String area;

    /** 물류센터 */
    @Schema(description = "물류센터", example = "2170")
    private String fixDcCode;


}
