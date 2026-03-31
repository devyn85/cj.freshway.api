package cjfw.batch.scm0060;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Schema(description = "")
public class Scm0060ParamsDto {

	/*****************************************************************
	 * SCM I/F defaultParam 조건용.
	 *****************************************************************/
	@Schema(description = "SEARCH_IF_FLAG", example = "", nullable = false)
	private String SEARCH_IF_FLAG;

	@Schema(description = "UPDATE_IF_FLAG", example = "", nullable = false)
	private String UPDATE_IF_FLAG;

	@Schema(description = "UPDATE_IF_MEMO", example = "", nullable = false)
	private String UPDATE_IF_MEMO;

	@Schema(description = "ALL_FLAG", example = "", nullable = false)
	private String ALL_FLAG;

	@Schema(description = "SERIAL_FLAG", example = "", nullable = false)
	private String SERIAL_FLAG;


	@Schema(description = "DOCTYPE", example = "", nullable = false)
	private String DOCTYPE;

	@Schema(description = "DOCNO", example = "", nullable = false)
	private String DOCNO;

	@Schema(description = "DOCDT", example = "", nullable = false)
	private String DOCDT;

	@Schema(description = "DOCLINE", example = "", nullable = false)
	private String DOCLINE;

	@Schema(description = "SLIPNO", example = "", nullable = false)
	private String SLIPNO;

	@Schema(description = "SLIPDT", example = "", nullable = false)
	private String SLIPDT;

	@Schema(description = "SLIPLINE", example = "", nullable = false)
	private String SLIPLINE;





	/*****************************************************************
	 * SCM I/F LIST 조회용.
	 *****************************************************************/
	@Schema(description = "SERIALKEY", example = "", nullable = false)
	private String SERIALKEY;









	@Schema(description = "DCCODE", example = "", nullable = false)
	private String DCCODE;

	@Schema(description = "POKEY", example = "", nullable = false)
	private String POKEY;

	@Schema(description = "POLINE", example = "", nullable = false)
	private String POLINE;

	@Schema(description = "SOKEY", example = "", nullable = false)
	private String SOKEY;

	@Schema(description = "SOLINE", example = "", nullable = false)
	private String SOLINE;

	@Schema(description = "SITE", example = "", nullable = false)
	private String SITE;

	@Schema(description = "PLANT", example = "", nullable = false)
	private String PLANT;

	@Schema(description = "STORAGELOC", example = "", nullable = false)
	private String STORAGELOC;

	@Schema(description = "SKU", example = "", nullable = false)
	private String SKU;

	@Schema(description = "STOREROPENQTY", example = "", nullable = false)
	private BigDecimal STOREROPENQTY;

	@Schema(description = "STORERCONFIRMQTY", example = "", nullable = false)
	private BigDecimal STORERCONFIRMQTY;

	@Schema(description = "STORERCANCELQTY", example = "", nullable = false)
	private BigDecimal STORERCANCELQTY;

	@Schema(description = "STORERUOM", example = "", nullable = false)
	private String STORERUOM;

	@Schema(description = "UOM", example = "", nullable = false)
	private String UOM;

	@Schema(description = "CONFIRMQTY", example = "", nullable = false)
	private BigDecimal CONFIRMQTY;

	@Schema(description = "STOCKGRADE", example = "", nullable = false)
	private String STOCKGRADE;

	@Schema(description = "CHANNEL", example = "", nullable = false)
	private String CHANNEL;

	@Schema(description = "POSTINGDATE", example = "", nullable = false)
	private String POSTINGDATE;

	@Schema(description = "DEL_YN", example = "", nullable = false)
	private String DEL_YN;

	@Schema(description = "CONFIRMWEIGHT", example = "", nullable = false)
	private BigDecimal CONFIRMWEIGHT;

	@Schema(description = "STATUS", example = "", nullable = false)
	private String STATUS;

	@Schema(description = "EDITWHO", example = "", nullable = false)
	private String EDITWHO;

	@Schema(description = "CLOSECD", example = "", nullable = false)
	private String CLOSECD;

	@Schema(description = "EDITDATE", example = "", nullable = false)
	private String EDITDATE;

	@Schema(description = "REASONMSG2", example = "", nullable = false)
	private String REASONMSG2;

	@Schema(description = "IF_TIMESTAMP", example = "", nullable = false)
	private String IF_TIMESTAMP;

}
