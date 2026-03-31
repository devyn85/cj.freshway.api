package cjfw.batch.scm0330;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Schema(description = "")
public class Scm0330ParamsDto {

	/*****************************************************************
	 * SCM I/F 검색 조건용. 
	 *****************************************************************/
	@Schema(description = "SEARCH_IF_FLAG", example = "", nullable = false)
	private String SEARCH_IF_FLAG;

	@Schema(description = "UPDATE_IF_FLAG", example = "", nullable = false)
	private String UPDATE_IF_FLAG;

	@Schema(description = "UPDATE_IF_MEMO", example = "", nullable = false)
	private String UPDATE_IF_MEMO;

	@Schema(description = "IF_ID", example = "", nullable = false)
	private String IF_ID;



	/*****************************************************************
	 * SCM I/F 조회용
	 *****************************************************************/
	@Schema(description = "POKEY", example = "", nullable = false)
	private String POKEY;

	@Schema(description = "POLINE", example = "", nullable = false)
	private String POLINE;

	@Schema(description = "DOCNO", example = "", nullable = false)
	private String DOCNO;

	@Schema(description = "DOCLINE", example = "", nullable = false)
	private String DOCLINE;

	@Schema(description = "EFFECTIVEDATE", example = "", nullable = false)
	private String EFFECTIVEDATE;

	@Schema(description = "POSTINGDATE", example = "", nullable = false)
	private String POSTINGDATE;

	@Schema(description = "SLIPNO", example = "", nullable = false)
	private String SLIPNO;

	@Schema(description = "CLOSECD", example = "", nullable = false)
	private String CLOSECD;

	@Schema(description = "EDITDATE", example = "", nullable = false)
	private String EDITDATE;

	@Schema(description = "IFTIMESTAMP", example = "", nullable = false)
	private String IFTIMESTAMP;


	@Schema(description = "SKU", example = "", nullable = false)
	private String SKU;

	@Schema(description = "PLANT", example = "", nullable = false)
	private String PLANT;

	@Schema(description = "STORAGELOC", example = "", nullable = false)
	private String STORAGELOC;

	@Schema(description = "STORERCONFIRMQTY", example = "", nullable = false)
	private BigDecimal STORERCONFIRMQTY;

	@Schema(description = "STORERUOM", example = "", nullable = false)
	private String STORERUOM;

	@Schema(description = "SLIPLINE", example = "", nullable = false)
	private String SLIPLINE;

	@Schema(description = "CONFIRMWEIGHT", example = "", nullable = false)
	private String CONFIRMWEIGHT;

	@Schema(description = "STOCKTRANSTYPE", example = "", nullable = false)
	private String STOCKTRANSTYPE;

	@Schema(description = "SERIALNO", example = "", nullable = false)
	private String SERIALNO;

	@Schema(description = "CONVSERIALNO", example = "", nullable = false)
	private String CONVSERIALNO;

	@Schema(description = "CANCELQTY", example = "", nullable = false)
	private BigDecimal CANCELQTY;

	@Schema(description = "CONFIRMQTY", example = "", nullable = false)
	private BigDecimal CONFIRMQTY;

	@Schema(description = "DPDOCLINE", example = "", nullable = false)
	private String DPDOCLINE;

	@Schema(description = "DPDOCNO", example = "", nullable = false)
	private String DPDOCNO;

	@Schema(description = "DPSLIPLINE", example = "", nullable = false)
	private String DPSLIPLINE;

	@Schema(description = "DPSLIPNO", example = "", nullable = false)
	private String DPSLIPNO;

	@Schema(description = "STOCKID", example = "", nullable = false)
	private String STOCKID;

	@Schema(description = "UOM", example = "", nullable = false)
	private String UOM;

	@Schema(description = "ETCQTY", example = "", nullable = false)
	private BigDecimal ETCQTY;

}


