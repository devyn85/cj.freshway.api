package cjfw.batch.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuartzParamsUtil  {

	@Schema(description = "SCHED_NAME", example = "", nullable = false)
	private String schedName;

	@Schema(description = "TRIGGER_NAME", example = "", nullable = false)
	private String triggerName;

	@Schema(description = "TRIGGER_GROUP", example = "", nullable = false)
	private String triggerGroup;

	@Schema(description = "CRON_EXPRESSION", example = "", nullable = false)
	private String cronExpression;

	@Schema(description = "JOB_NAME", example = "", nullable = false)
	private String jobName;

	@Schema(description = "JOB_CLASS_NAME", example = "", nullable = false)
	private String jobClassName;

	@Schema(description = "PARAM_NAME", example = "", nullable = false)
	private String paramName;

	@Schema(description = "PARAM_VALUE", example = "", nullable = false)
	private String paramValue;

	@Schema(description = "COLUMN", example = "", nullable = false)
	private String column;

	@Schema(description = "MODE", example = "", nullable = false)
	private String mode;

	@Schema(description = "TYPE", example = "", nullable = false)
	private String type;

	@Schema(description = "OBJECT", example = "", nullable = false)
	private String object;

	@Schema(description = "DEFAULT", example = "", nullable = false)
	private String defaultVal;

    @Schema(description = "USE_YN", example = "", nullable = false)
    private String useYn;

}
