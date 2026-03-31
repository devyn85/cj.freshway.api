package cjfw.batch.shr0250;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "")
public class PushSendParamsDto {

	/*****************************************************************
	 * SCM I/F 검색 조건용. 
	 *****************************************************************/
	@Schema(description = "SERIALKEY", example = "", nullable = false)
	private int SERIALKEY;

	@Schema(description = "RECEIVEPHONE", example = "", nullable = false)
	private String RECEIVEPHONE;

	@Schema(description = "SENDMESSAGE", example = "", nullable = false)
	private String SENDMESSAGE;

	@Schema(description = "RECEIVEGROUP", example = "", nullable = false)
	private String RECEIVEGROUP;

	@Schema(description = "RECEIVENAME", example = "", nullable = false)
	private String RECEIVENAME;

	@Schema(description = "STATUS", example = "", nullable = false)
	private String STATUS;

	@Schema(description = "IF_MEMO", example = "", nullable = false)
	private String IF_MEMO;

	@Schema(description = "IF_FLAG", example = "", nullable = false)
	private String IF_FLAG;
}
