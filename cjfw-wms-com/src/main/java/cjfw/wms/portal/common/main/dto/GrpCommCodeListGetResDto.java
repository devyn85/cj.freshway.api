package cjfw.wms.portal.common.main.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

/**
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : wisujang
 * @date : 2023.04.21
 * @description : 그룹 공통코드 목록 조회 응답 DTO
 * @issues :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.04.21        wisujang       생성
 */

@Data
@Schema(description = "그룹 공통코드 목록 조회 응답")
public class GrpCommCodeListGetResDto {

    @Schema(description = "공통그룹코드", example = "TPL_TIMEZONE")
    private String comGrpCd;

    @Schema(description = "공통코드 리스트")
    private List<CommCode> commCodes;

    @Data
    public static class CommCode{
        @Schema(description = "공통코드", example = "ko_kr")
        private String comCd;

        @Schema(description = "공통그룹코드", example = "TPL_TIMEZONE")
        private String comGrpCd;

        @Schema(description = "코드명", example = "한국어")
        private String cdNm;

        @Schema(description = "정렬순서", example = "1")
        private Integer sortNo;

        @Schema(description = "참조1", example = "")
        private String rsvStr1Val;

        @Schema(description = "참조2", example = "")
        private String rsvStr2Val;

        @Schema(description = "참조3", example = "")
        private String rsvStr3Val;

        @Schema(description = "참조4", example = "")
        private String rsvStr4Val;

        @Schema(description = "참조5", example = "")
        private String rsvStr5Val;

        @Schema(description = "참조6", example = "")
        private String rsvStr6Val;

        @Schema(description = "참조7", example = "")
        private String rsvStr7Val;

        @Schema(description = "참조8", example = "")
        private String rsvStr8Val;
    }

}
