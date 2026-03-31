package cjfw.batch.scm0320;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "")
public class Scm0320ParamsReqDto {

	/*****************************************************************
	 * SCM0320 I/F 검색 조건용.
	 *****************************************************************/
    @Schema(description = "SEARCH_IF_FLAG", example = "", nullable = false)
    private String SEARCH_IF_FLAG;

	@Schema(description = "UPDATE_IF_FLAG", example = "", nullable = false)
	private String UPDATE_IF_FLAG;

    @Schema(description = "IFTIMESTAMP", example = "", nullable = false)
	private String IFTIMESTAMP;

    @Schema(description = "DOCNO", example = "", nullable = false)
    private String DOCNO;

    @Schema(description = "DOCLINE", example = "", nullable = false)
    private String DOCLINE;

	@Schema(description = "UPDATE_IF_MEMO", example = "", nullable = true)
	private String UPDATE_IF_MEMO;
	
}


