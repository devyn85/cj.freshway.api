package cjfw.batch.shr0130;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "")
public class SmsSendParamsDto {

	/*****************************************************************
	 * SMS SEND 컬럼.
	 *****************************************************************/
	@Schema(description = "SENDPHONE", example = "", nullable = false)
	private String SENDPHONE;

	@Schema(description = "RECEIVEPHONE", example = "", nullable = false)
	private String RECEIVEPHONE;

	@Schema(description = "SENDDT", example = "", nullable = false)
	private String SENDDT;

	@Schema(description = "SENDMESSAGE", example = "", nullable = false)
	private String SENDMESSAGE;

	@Schema(description = "SERIALKEY", example = "", nullable = false)
	private int SERIALKEY;

}
