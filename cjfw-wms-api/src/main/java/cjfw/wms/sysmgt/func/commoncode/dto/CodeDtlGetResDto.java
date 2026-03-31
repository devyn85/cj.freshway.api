package cjfw.wms.sysmgt.func.commoncode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CodeDtlGetResDto {
	@Schema(description = "공통그룹코드", example = "TPL_TIMEZONE")
    private String comGrpCd;
	@Schema(description = "공통코드", example = "Asia/Seoul")
    private String comCd;
	@Schema(description = "공통코드(원본)", example = "Asia/Seoul")
    private String comCdOrig;
	@Schema(description = "공통코드명", example = "Asia/Seoul")
    private String cdNm;
	@Schema(description = "정렬순서", example = "1")
    private Integer sortNo;
	@Schema(description = "코드설명", example = " ")
    private String cdDesc;
	@Schema(description = "사용여부", example = "1")
    private String useYn;
	@Schema(description = "예약_문자1", example = " ")
    private String rsvStr1Val;
    @Schema(description = "예약_문자2", example = " ")
    private String rsvStr2Val;
    @Schema(description = "예약_문자3", example = " ")
    private String rsvStr3Val;
    @Schema(description = "예약_문자4", example = " ")
    private String rsvStr4Val;
    @Schema(description = "예약_문자5", example = " ")
    private String rsvStr5Val;
    @Schema(description = "예약_문자6", example = " ")
    private String rsvStr6Val;
    @Schema(description = "예약_문자7", example = " ")
    private String rsvStr7Val;
    @Schema(description = "예약_문자8", example = " ")
    private String rsvStr8Val;
	@Schema(description = "수정자ID", example = "devadmin01")
    private String modId;
	@Schema(description = "수정일시", example = "2022-07-28")
    private String modDt;
}
