package cjfw.batch.job.inspectSorter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "")
public class InspectSorterDto {

	@Schema(description = "SERIALKEY", example = "", nullable = false)
	private int serialkey;

	@Schema(description = "EXECUTEDT", example = "", nullable = false)
	private String executedt;

	@Schema(description = "OBJECTNAME", example = "", nullable = false)
	private String objectname;

	@Schema(description = "SYSTEM", example = "", nullable = false)
	private String system;

	@Schema(description = "COMMAND", example = "", nullable = false)
	private String command;

	@Schema(description = "DCCODE", example = "", nullable = false)
	private String dccode;

	@Schema(description = "STORERKEY", example = "", nullable = false)
	private String storerkey;

	@Schema(description = "ORGANIZE", example = "", nullable = false)
	private String organize;

	@Schema(description = "AREA", example = "", nullable = false)
	private String area;

	@Schema(description = "REQUESTCODE", example = "", nullable = false)
	private String requestcode;

	@Schema(description = "REQUESTMSG", example = "", nullable = false)
	private String requestmsg;

	@Schema(description = "WORKER", example = "", nullable = false)
	private String worker;

	@Schema(description = "RETURNMSG", example = "", nullable = false)
	private String returnmsg;

	@Schema(description = "SPID", example = "", nullable = false)
	private String spid;

	@Schema(description = "SENDYN", example = "", nullable = false)
	private String sendyn;



}
