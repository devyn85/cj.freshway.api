package cjfw.batch.scm0110;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Schema(description = "")
public class Scm0110ParamsDto {

	/*****************************************************************
	 * SCM I/F 검색 조건용. 
	 *****************************************************************/
	@Schema(description = "SEARCH_IF_FLAG", example = "", nullable = false)
	private String SEARCH_IF_FLAG;

	@Schema(description = "UPDATE_IF_FLAG", example = "", nullable = false)
	private String UPDATE_IF_FLAG;

	@Schema(description = "UPDATE_IF_MEMO", example = "", nullable = false)
	private String UPDATE_IF_MEMO;



	/*****************************************************************
	 * SCM I/F LIST 조회용.
	 *****************************************************************/
	@Schema(description = "POKEY", example = "", nullable = false)
	private String POKEY;

	@Schema(description = "POLINE", example = "", nullable = false)
	private String POLINE;

	@Schema(description = "DOCNO", example = "", nullable = false)
	private String DOCNO;

	@Schema(description = "EFFECTIVEDATE", example = "", nullable = false)
	private String EFFECTIVEDATE;

	@Schema(description = "POSTINGDATE", example = "", nullable = false)
	private String POSTINGDATE;

	@Schema(description = "SLIPNO", example = "", nullable = false)
	private String SLIPNO;

	@Schema(description = "PLANT", example = "", nullable = false)
	private String PLANT;

	@Schema(description = "CLOSECD", example = "", nullable = false)
	private String CLOSECD;

	@Schema(description = "EDITDATE", example = "", nullable = false)
	private String EDITDATE;

	@Schema(description = "EDITWHO", example = "", nullable = false)
	private String EDITWHO;

	@Schema(description = "IFTIMESTAMP", example = "", nullable = false)
	private String IFTIMESTAMP;

	@Schema(description = "ORDERTYPE", example = "", nullable = false)
	private String ORDERTYPE;


	@Schema(description = "DOCLINE", example = "", nullable = false)
	private String DOCLINE;

	@Schema(description = "SKU", example = "", nullable = false)
	private String SKU;

	@Schema(description = "STORAGELOC", example = "", nullable = false)
	private String STORAGELOC;

	@Schema(description = "STOREROPENQTY", example = "", nullable = false)
	private double STOREROPENQTY;

	@Schema(description = "CONFIRMQTY", example = "", nullable = false)
	private BigDecimal CONFIRMQTY;

	@Schema(description = "STORERCONFIRMQTY", example = "", nullable = false)
	private BigDecimal STORERCONFIRMQTY;

	@Schema(description = "STORERCANCELQTY", example = "", nullable = false)
	private BigDecimal STORERCANCELQTY;

	@Schema(description = "REASONCODE2", example = "", nullable = false)
	private String REASONCODE2;

	@Schema(description = "UOM", example = "", nullable = false)
	private String UOM;

	@Schema(description = "STORERUOM", example = "", nullable = false)
	private String STORERUOM;

	@Schema(description = "STATUS", example = "", nullable = false)
	private String STATUS;

	@Schema(description = "SLIPLINE", example = "", nullable = false)
	private String SLIPLINE;

	@Schema(description = "CONFIRMWEIGHT", example = "", nullable = false)
	private BigDecimal CONFIRMWEIGHT;

	@Schema(description = "DP_DOCNO", example = "", nullable = false)
	private String DPDOCNO;

	@Schema(description = "DP_DOCLINE", example = "", nullable = false)
	private String DPDOCLINE;

	@Schema(description = "CANCELQTY", example = "", nullable = false)
	private String CANCELQTY;

	@Schema(description = "SERIALTYPE", example = "", nullable = false)
	private String SERIALTYPE;

	@Schema(description = "ETC_QTY", example = "", nullable = false)
	private BigDecimal ETCQTY;

	@Schema(description = "STOCKID", example = "", nullable = false)
	private String STOCKID;

	@Schema(description = "CONVSERIALNO", example = "", nullable = false)
	private String CONVSERIALNO;

	@Schema(description = "SERIALNO", example = "", nullable = false)
	private String SERIALNO;

	@Schema(description = "DP_SLIPNO", example = "", nullable = false)
	private String DPSLIPNO;

	@Schema(description = "DP_SLIPLINE", example = "", nullable = false)
	private String DPSLIPLINE;

	@Schema(description = "DP_POKEY", example = "", nullable = false)
	private String DPPOKEY;

	@Schema(description = "DP_POLINE", example = "", nullable = false)
	private String DPPOLINE;

}
