package cjfw.batch.scm0160;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "")
public class Scm0160ParamsDto {

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

	@Schema(description = "DOCNO", example = "", nullable = false)
	private String DOCNO;
}
