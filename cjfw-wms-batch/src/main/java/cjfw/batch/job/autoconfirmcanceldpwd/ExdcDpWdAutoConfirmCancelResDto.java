package cjfw.batch.job.autoconfirmcanceldpwd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Schema(description = "")
public class ExdcDpWdAutoConfirmCancelResDto {

	/*****************************************************************
	 * 가출고 취소대상
	 *****************************************************************/
	@Schema(description = "DCCODE", example = "", nullable = false)
	private String dccode;

	@Schema(description = "STORERKEY", example = "", nullable = false)
	private String storerkey;

	@Schema(description = "ORGANIZE", example = "", nullable = false)
	private String organize;

	@Schema(description = "SLIPDT", example = "", nullable = false)
	private String slipdt;

	@Schema(description = "SLIPNO", example = "", nullable = false)
	private String slipno;

	@Schema(description = "DOCNO", example = "", nullable = false)
	private String docNo;

	@Schema(description = "MAPKEY_NO", example = "", nullable = false)
	private String mapkeyNo;

	@Schema(description = "TMP_SOKEY", example = "", nullable = false)
	private String tmpSokey;

    @Schema(description = "DOCDT", example = "", nullable = false)
    private String docdt;

    @Schema(description = "SLIPLINE", example = "", nullable = false)
    private String slipline;

    @Schema(description = "DOCLINE", example = "", nullable = false)
    private String docline;

    @Schema(description = "SKU", example = "", nullable = false)
    private String sku;

    @Schema(description = "UOM", example = "", nullable = false)
    private String uom;

    @Schema(description = "BUNJA", example = "", nullable = false)
    private BigDecimal bunja;

    @Schema(description = "BUNMO", example = "", nullable = false)
    private BigDecimal bunmo;

    @Schema(description = "CONFIRMQTY", example = "", nullable = false)
    private BigDecimal confirmqty;

    @Schema(description = "SHORTAGETRANQTY", example = "", nullable = false)
    private BigDecimal shortagetranqty;

    @Schema(description = "TRANQTY", example = "", nullable = false)
    private BigDecimal tranqty;

    @Schema(description = "LOTTABLE01", example = "", nullable = false)
    private String lottable01;

    @Schema(description = "REFERENCE02", example = "", nullable = false)
    private String reference02;

    @Schema(description = "REFERENCE10", example = "", nullable = false)
    private String reference10;

    @Schema(description = "REASONCODE", example = "", nullable = false)
    private String reasoncode;

    @Schema(description = "REASONMSG", example = "", nullable = false)
    private String reasonmsg;

    @Schema(description = "STOCKID", example = "", nullable = false)
    private String stockid;

    @Schema(description = "STOCKGRADE", example = "", nullable = false)
    private String stockgrade;

    @Schema(description = "TRANBOX", example = "", nullable = false)
    private double transbox;

    @Schema(description = "MAPKEY_LINE", example = "", nullable = false)
    private String mapkeyLine;

    @Schema(description = "TMP_SOLINE", example = "", nullable = false)
    private String tmpSoline;

    @Schema(description = "TMP_DPKEY", example = "", nullable = false)
    private String tmpDpkey;

    @Schema(description = "TMP_DPLINE", example = "", nullable = false)
    private String tmpDpline;

    @Schema(description = "ISSUE_NO", example = "", nullable = false)
    private String issueNo;

}
