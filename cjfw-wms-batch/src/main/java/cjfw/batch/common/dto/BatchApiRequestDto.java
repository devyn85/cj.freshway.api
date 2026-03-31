package cjfw.batch.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class BatchApiRequestDto {
	private String jobName;
    private String jobStatus;
    private String userId;
	private List<Param> paramList;

	@Data
	public static class Param {
		private String paramName;
		private String paramDesc;
		private String paramValue;
		private String paramType;
		private String useYn;
	}
}
