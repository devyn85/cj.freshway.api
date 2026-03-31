package cjfw.batch.job.autoconfirmcanceldpwd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "")
public class ExdcDpWdAutoConfirmCancelReqDto {

	/*****************************************************************
	 * 조회 파라미터
	 *****************************************************************/
	@Schema(description = "DCCODE", example = "", nullable = false)
	private String dccode;

	@Schema(description = "STORERKEY", example = "", nullable = false)
	private String storerkey;

	@Schema(description = "SLIPDT", example = "", nullable = false)
	private String slipdt;

	@Schema(description = "SLIPNO", example = "", nullable = false)
	private String slipno;

    @Schema(description = "SLIPLINE", example = "", nullable = false)
    private String slipline;

    @Schema(description = "DOCNO", example = "", nullable = false)
    private String docno;

    @Schema(description = "STOCKID", example = "", nullable = false)
    private String stockid;

    @Schema(description = "MAPKEY_NO", example = "", nullable = false)
    private String mapkeyNo;

    @Schema(description = "MAPKEY_LINE", example = "", nullable = false)
    private String mapkeyLine;

    @Schema(description = "TMP_SOKEY", example = "", nullable = false)
    private String tmpSokey;

    @Schema(description = "TMP_SOLINE", example = "", nullable = false)
    private String tmpSoline;

    @Schema(description = "TMP_DPKEY", example = "", nullable = false)
    private String tmpDpkey;

    @Schema(description = "TMP_DPLINE", example = "", nullable = false)
    private String tmpDpline;

    @Schema(description = "ERROR_CHECK", example = "", nullable = false)
    private String errorCheck;

    @Schema(description = "ERROR_STATUS", example = "", nullable = false)
    private String errorStatus;

    @Schema(description = "WORKER", example = "", nullable = false)
    private String worker;
}
