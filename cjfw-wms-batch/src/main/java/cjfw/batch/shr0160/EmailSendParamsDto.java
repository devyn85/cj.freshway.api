package cjfw.batch.shr0160;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


@Data
@Schema(description = "")
public class EmailSendParamsDto {

	/*****************************************************************
	 * SCM I/F 검색 조건용. 
	 *****************************************************************/
	@Schema(description = "EMAIL_NO", example = "", nullable = false)
	private int emailNo;

	@Schema(description = "TITLE", example = "", nullable = false)
	private String title;

	@Schema(description = "CNTS", example = "", nullable = false)
	private String cnts;

	@Schema(description = "SENDR_EMAIL_ADDR", example = "", nullable = false)
	private String sendrEmailAddr;

	@Schema(description = "RCVR_EMAIL_ADDR", example = "", nullable = false)
	private String rcvrEmailAddr;

	@Schema(description = "RESULT", example = "", nullable = false)
	private String result;

	@Schema(description = "emailList", example = "", nullable = false)
	private List<EmailSendParamsDto> emailList;

	@Schema(description = "groupId", example = "", nullable = false)
	private String groupId;
}
