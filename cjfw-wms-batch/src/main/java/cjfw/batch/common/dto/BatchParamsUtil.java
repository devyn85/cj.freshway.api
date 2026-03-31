package cjfw.batch.common.dto;

import cjfw.batch.job.inspectSorter.InspectSorterDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchParamsUtil {


	/*****************************************************************
	 * INPUT PARAMETER
	 *****************************************************************/
	@Schema(description = "AVC_SYSTEM", example = "", nullable = false)
	private String AVC_SYSTEM;

	@Schema(description = "AVC_COMMAND", example = "", nullable = false)
	private String AVC_COMMAND;

	@Schema(description = "AVC_WORKER", example = "", nullable = false)
	private String AVC_WORKER;

	@Schema(description = "AVC_EXECUTEMODE", example = "", nullable = false)
	private String AVC_EXECUTEMODE;

	@Schema(description = "AVC_STORERKEY", example = "", nullable = false)
	private String AVC_STORERKEY;

	@Schema(description = "AVC_ORGANIZE", example = "", nullable = false)
	private String AVC_ORGANIZE;

	@Schema(description = "AVC_AREA", example = "", nullable = false)
	private String AVC_AREA;

	@Schema(description = "AVC_SECURITYKEY", example = "", nullable = false)
	private String AVC_SECURITYKEY;

	@Schema(description = "AI_SPID", example = "", nullable = false)
	private int AI_SPID;

	@Schema(description = "AVC_DCCODE", example = "", nullable = false)
	private String AVC_DCCODE;

	@Schema(description = "I_EXECUTESTEP", example = "", nullable = false)
	private int I_EXECUTESTEP;

	@Schema(description = "AVC_REQUESTCODE", example = "", nullable = false)
	private String AVC_REQUESTCODE;

	@Schema(description = "AVC_REQUESTMSG", example = "", nullable = false)
	private String AVC_REQUESTMSG;

	@Schema(description = "AVC_IFID", example = "", nullable = false)
	private String AVC_IFID;

	@Schema(description = "AVC_IFRESULT", example = "", nullable = false)
	private String AVC_IFRESULT;

	@Schema(description = "AVC_IFCURSTATUS", example = "", nullable = false)
	private String AVC_IFCURSTATUS;

	@Schema(description = "AVC_INOUTDT", example = "", nullable = false)
	private String AVC_INOUTDT;

	@Schema(description = "AVC_REUESTMSG", example = "", nullable = false)
	private String AVC_REUESTMSG;

	@Schema(description = "AN_SERIALKEY", example = "", nullable = false)
	private int AN_SERIALKEY;

	@Schema(description = "PGMID", example = "", nullable = false)
	private String PGMID;

	@Schema(description = "RUNDATE", example = "", nullable = false)
	private String RUNDATE;

	@Schema(description = "STAT", example = "", nullable = false)
	private String STAT;

	@Schema(description = "MSG", example = "", nullable = false)
	private String MSG;

	@Schema(description = "EXENO", example = "", nullable = false)
	private String EXENO;

	@Schema(description = "SERIALKEY", example = "", nullable = false)
	private int SERIALKEY;

	@Schema(description = "AVC_SOURCEOBJECT", example = "", nullable = false)
	private String AVC_SOURCEOBJECT;

	@Schema(description = "VC_RETURNCURSOR", example = "", nullable = false)
	private List<InspectSorterDto> VC_RETURNCURSOR;
	
	@Schema(description = "AVC_LOG_PARAMS", example = "", nullable = false)
	private String AVC_LOG_PARAMS;
	
	/*****************************************************************
	 * 기존 OUT PARAMETER
	 *****************************************************************/
    @Schema(description = "I_ERR", example = "", nullable = false)
	private int I_ERR;

	@Schema(description = "VC_RESULTMSG", example = "", nullable = false)
	private String VC_RESULTMSG;

	@Schema(description = "VC_RETURNMSG", example = "", nullable = false)
	private String VC_RETURNMSG;

	@Schema(description = "ERR", example = "", nullable = false)
	private int ERR;

	@Schema(description = "RESULTMSG", example = "", nullable = false)
	private String RESULTMSG;

	@Schema(description = "RETURNMSG", example = "", nullable = false)
	private String RETURNMSG;

	@Schema(description = "OUT_SUCCESS", example = "", nullable = false)
	private int OUT_SUCCESS;

	@Schema(description = "OUT_ERR_CODE", example = "", nullable = false)
	private int OUT_ERR_CODE;

	@Schema(description = "OUT_RETURN_MSG", example = "", nullable = false)
	private String OUT_RETURN_MSG;


	/*****************************************************************
	 * 기타 PARAMETER
	 *****************************************************************/
	
	@Schema(description = "AUTO_EXCUTE", example = "", nullable = false)
	private String autoExcute;

	@Schema(description = "TIME", example = "", nullable = false)
	private Long time;

	@Schema(description = "IF_ID", example = "", nullable = false)
	private String ifId;

	@Schema(description = "DEL_YN", example = "", nullable = false)
	private String delYn;

	@Schema(description = "JOB_PARAMS", example = "", nullable = false)
	private Map<String, Object> jobParams;
}
