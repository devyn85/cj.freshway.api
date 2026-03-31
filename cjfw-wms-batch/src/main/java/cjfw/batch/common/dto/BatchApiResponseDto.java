package cjfw.batch.common.dto;

import lombok.Data;

@Data
public class BatchApiResponseDto {
	private String jobName;
	private String returnCode;
	private String returnMsg;

}
