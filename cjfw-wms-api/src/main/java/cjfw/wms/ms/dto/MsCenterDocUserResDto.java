package cjfw.wms.ms.dto;

import cjfw.wms.common.annotation.MaskingEmail;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.17
 * @description : 센터서류 담당자 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@Schema(description = "센터서류 담당자 조회 결과 DTO")
public class MsCenterDocUserResDto extends CommonDto{
	@Schema(description = "시리얼 키")
    private String serialKey;

    @MaskingName
    @Schema(description = "사용자 이름")
    private String userNm;

    @Schema(description = "센터 코드")
    private String dcCode;

    @Schema(description = "고객사 키")
    private String storerKey;

    @MaskingEmail
    @Schema(description = "이메일")
    private String email;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "등록일", example = "YYYY-MM-DD HH:mm:ss")
    private String addDate;

    @Schema(description = "수정일", example = "YYYY-MM-DD HH:mm:ss")
    private String editDate;

    @Schema(description = "등록자")
    private String addWho;

    @Schema(description = "수정자")
    private String editWho;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;
}
