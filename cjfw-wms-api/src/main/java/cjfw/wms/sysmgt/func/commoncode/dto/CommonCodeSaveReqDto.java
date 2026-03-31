package cjfw.wms.sysmgt.func.commoncode.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 공통코드 저장 Request DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonCodeSaveReqDto {
	@Schema(description = "공통그룹코드 리스트", example = "", nullable = false)
    @Valid
    private List<CodeGrp> codeGrps;

	@Schema(description = "공통코드 리스트", example = "", nullable = false)
    @Valid
    private List<CodeDtl> codeDtls;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodeGrp{
    	@Schema(description = "공통그룹코드", example = "TPL_TIMEZONE", nullable = false)
        @NotEmpty(message = "공통그룹코드는 필수 값입니다.")
        private String comGrpCd;
    	@Schema(description = "공통그룹코드명", example = "타임존", nullable = true)
        private String grpCdNm;
    	@Schema(description = "공통그룹코드 설명", example = "타임존", nullable = true)
        private String grpCdDesc;
    	@Schema(description = "사용여부", example = "1", allowableValues = {"1", "0"})
        private String useYn;
    	@Schema(description = "", example = "U", allowableValues = {"I", "U", "D"})
        @NotEmpty(message = "GridRow 저장 구분는 필수 값입니다.")
        private String rowStatus; // "I"(등록), "U"(수정), "D"(삭제)
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodeDtl {
    	@Schema(description = "공통그룹코드", example = "TPL_TIMEZONE", nullable = false)
    	@NotEmpty(message = "공통그룹코드는 필수 값입니다.")
        private String comGrpCd;
    	@Schema(description = "공통코드", example = "UTC", nullable = false)
        @NotEmpty(message = "공통코드는 필수 값입니다.")
        private String comCd;
        @Schema(description = "코드명", example = "UTC", nullable = true)
        private String cdNm;
        @Schema(description = "정렬순서", example = "2", nullable = true)
        private Integer sortNo;
        @Schema(description = "코드설명", example = "UTC", nullable = true)
        private String cdDesc;
        @Schema(description = "사용여부", example = "1", nullable = true)
        private String useYn;
        @Schema(description = "예약_문자1", example = " ", nullable = true)
        private String rsvStr1Val;
        @Schema(description = "예약_문자2", example = " ", nullable = true)
        private String rsvStr2Val;
        @Schema(description = "예약_문자3", example = " ", nullable = true)
        private String rsvStr3Val;
        @Schema(description = "예약_문자4", example = " ", nullable = true)
        private String rsvStr4Val;
        @Schema(description = "예약_문자5", example = " ", nullable = true)
        private String rsvStr5Val;
        @Schema(description = "예약_문자6", example = " ", nullable = true)
        private String rsvStr6Val;
        @Schema(description = "예약_문자7", example = " ", nullable = true)
        private String rsvStr7Val;
        @Schema(description = "예약_문자8", example = " ", nullable = true)
        private String rsvStr8Val;
        @Schema(description = "GridRow 저장 구분", example = "U", allowableValues = {"I", "U", "D"})
        @NotEmpty(message = "GridRow 저장 구분은 필수 값입니다.")
        private String rowStatus; // "I"(등록), "U"(수정), "D"(삭제)
    }
}
