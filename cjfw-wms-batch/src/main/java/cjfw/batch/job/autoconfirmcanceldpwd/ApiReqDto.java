package cjfw.batch.job.autoconfirmcanceldpwd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "")
public class ApiReqDto {

	/*****************************************************************
	 * 조회 파라미터
	 *****************************************************************/
	@Schema(description = "STORERKEY", example = "", nullable = false)
	private String STORERKEY;

	@Schema(description = "HTTPMETHOD", example = "", nullable = false)
	private String HTTPMETHOD;

	@Schema(description = "URL", example = "", nullable = false)
	private String URL;

    @Schema(description = "SOURCEIP", example = "", nullable = false)
    private String SOURCEIP;

    @Schema(description = "REQUESTTIME", example = "", nullable = false)
    private String REQUESTTIME;

    @Schema(description = "RESPONSETIME", example = "", nullable = false)
    private String RESPONSETIME;

    @Schema(description = "LATENCY", example = "", nullable = false)
    private long LATENCY;

    @Schema(description = "REQUESTBODY", example = "", nullable = false)
    private String REQUESTBODY;

    @Schema(description = "RESPONSEBODY", example = "", nullable = false)
    private String RESPONSEBODY;

    @Schema(description = "STATUS", example = "", nullable = false)
    private String STATUS;

    @Schema(description = "ERRORMESSAGE", example = "", nullable = false)
    private String ERRORMESSAGE;

    @Schema(description = "ERRORSTACKTRACE", example = "", nullable = false)
    private String ERRORSTACKTRACE;

    @Schema(description = "WORKER", example = "", nullable = false)
    private String WORKER;

}
