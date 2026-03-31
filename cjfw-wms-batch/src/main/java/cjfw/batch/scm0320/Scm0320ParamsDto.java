package cjfw.batch.scm0320;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Schema(description = "")
public class Scm0320ParamsDto {

	/*****************************************************************
	 * SCM0320 I/F 검색결과.
	 *****************************************************************/
    @Schema(description = "SEARCH_IF_FLAG", example = "", nullable = false)
    private String SEARCH_IF_FLAG;

	@Schema(description = "UPDATE_IF_FLAG", example = "", nullable = false)
	private String UPDATE_IF_FLAG;

	@Schema(description = "UPDATE_IF_MEMO", example = "", nullable = false)
	private String UPDATE_IF_MEMO;

    @Schema(description = "EDITDATE", example = "", nullable = false)
    private String EDITDATE;

	@Schema(description = "IFTIMESTAMP", example = "", nullable = false)
	private String IFTIMESTAMP;

    @Schema(description = "DOCNO", example = "", nullable = false)
    private String DOCNO;

    @Schema(description = "DOCLINE", example = "", nullable = false)
    private String DOCLINE;

    @Schema(description = "PLANT", example = "", nullable = false)
    private String PLANT;

    @Schema(description = "STOCKTRANSTYPE", example = "", nullable = false)
    private String STOCKTRANSTYPE;

    @Schema(description = "EFFECTIVEDATE", example = "", nullable = false)
    private String EFFECTIVEDATE;

    @Schema(description = "POSTINGDATE", example = "", nullable = false)
    private String POSTINGDATE;

    @Schema(description = "STORAGELOC", example = "", nullable = false)
    private String STORAGELOC;

    @Schema(description = "SKU", example = "", nullable = false)
    private String SKU;

    @Schema(description = "CONFIRMQTY", example = "", nullable = false)
    private BigDecimal CONFIRMQTY;

    @Schema(description = "UOM", example = "", nullable = false)
    private String UOM;

    @Schema(description = "REASONCODE2", example = "", nullable = false)
    private String REASONCODE2;

    @Schema(description = "OTHER03", example = "", nullable = false)
    private String OTHER03;

    @Schema(description = "REFERENCE05", example = "", nullable = false)
    private String REFERENCE05;

    @Schema(description = "REFERENCE07", example = "", nullable = false)
    private String REFERENCE07;

    @Schema(description = "POLINE", example = "", nullable = false)
    private String POLINE;

    @Schema(description = "REFERENCE08", example = "", nullable = false)
    private String REFERENCE08;

    @Schema(description = "SLIPNO", example = "", nullable = false)
    private String SLIPNO;

    @Schema(description = "SLIPLINE", example = "", nullable = false)
    private String SLIPLINE;

    @Schema(description = "SERIALTYPE", example = "", nullable = false)
    private String SERIALTYPE;

    @Schema(description = "CUSTKEY", example = "", nullable = false)
    private String CUSTKEY;

    @Schema(description = "CLOSECD", example = "", nullable = false)
    private String CLOSECD;

    @Schema(description = "POKEY", example = "", nullable = false)
    private String POKEY;

    @Schema(description = "STOCK_AMOUNT", example = "", nullable = false)
    private String STOCK_AMOUNT;

    @Schema(description = "ETC_AMOUNT", example = "", nullable = false)
    private String ETC_AMOUNT;
}


