package cjfw.batch.common;

import cjfw.batch.common.dto.BatchApiRequestDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.common.dto.QuartzParamsUtil;
import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class SetParamService {

	private final CommonDao commonDao;

	public BatchParamsUtil getBatchParam(String jobName, Map<String, Object> jobParams) {
		BatchParamsUtil batchParam = new BatchParamsUtil();
		QuartzParamsUtil quartzParamsUtil = new QuartzParamsUtil();
		quartzParamsUtil.setJobName(jobName);

		List<QuartzParamsUtil> batchParamList = commonDao.selectList("quartz.selectBatchDetail", quartzParamsUtil);

		Map<String, Consumer<String>> paramSetterMap = new HashMap<>() {{
			put("AVC_INOUTDT", batchParam::setAVC_INOUTDT);
			put("AVC_SYSTEM", batchParam::setAVC_SYSTEM);
			put("AVC_EXECUTEMODE", batchParam::setAVC_EXECUTEMODE);
			put("AVC_COMMAND", batchParam::setAVC_COMMAND);
			put("AVC_DCCODE", batchParam::setAVC_DCCODE);
			put("AVC_STORERKEY", batchParam::setAVC_STORERKEY);
			put("AVC_ORGANIZE", batchParam::setAVC_ORGANIZE);
			put("AVC_AREA", batchParam::setAVC_AREA);
			put("AVC_REQUESTCODE", batchParam::setAVC_REQUESTCODE);
			put("AVC_REQUESTMSG", batchParam::setAVC_REQUESTMSG);
			put("AVC_IFID", batchParam::setAVC_IFID);
			put("AVC_WORKER", batchParam::setAVC_WORKER);
			put("AVC_SECURITYKEY", batchParam::setAVC_SECURITYKEY);
			put("AI_SPID", val -> batchParam.setAI_SPID(Integer.parseInt(val)));
			put("I_EXECUTESTEP", val -> batchParam.setI_EXECUTESTEP(Integer.parseInt(val)));
			put("I_ERR", val -> batchParam.setI_ERR(Integer.parseInt(val)));
			put("AVC_IFRESULT", batchParam::setAVC_IFRESULT);
			put("VC_RESULTMSG", batchParam::setVC_RESULTMSG);
			put("VC_RETURNMSG", batchParam::setVC_RETURNMSG);
			put("AN_SERIALKEY", val -> batchParam.setAN_SERIALKEY(Integer.parseInt(val)));
			put("PGMID", batchParam::setPGMID);
			put("RUNDATE", batchParam::setRUNDATE);
			put("STAT", batchParam::setSTAT);
			put("MSG", batchParam::setMSG);
			put("EXENO", batchParam::setEXENO);
			put("SERIALKEY", val -> batchParam.setSERIALKEY(Integer.parseInt(val)));
			put("autoExcute", batchParam::setAutoExcute);
			put("time", val -> batchParam.setTime(Long.valueOf(val)));
			put("AVC_LOG_PARAMS",  batchParam::setAVC_LOG_PARAMS);
		}};

		for (QuartzParamsUtil param : batchParamList) {
			String name = param.getParamName();
			String defaultVal = Optional.ofNullable(param.getParamValue()).orElse("");
			if (paramSetterMap.containsKey(name)) {
				paramSetterMap.get(name).accept(defaultVal);
				jobParams.put(name, defaultVal);
			}
		}
		batchParam.setJobParams(jobParams);

		return batchParam;
	}

	private static BatchParamsUtil parse(Map<String, Object> jobParameters) {
		return BatchParamsUtil.builder()
				.AVC_SYSTEM((String) jobParameters.get("AVC_SYSTEM"))
				.AVC_COMMAND((String) jobParameters.get("AVC_COMMAND"))
				.AVC_WORKER((String) jobParameters.get("AVC_WORKER"))
				.AVC_EXECUTEMODE((String) jobParameters.get("AVC_EXECUTEMODE"))
				.AVC_STORERKEY((String) jobParameters.get("AVC_STORERKEY"))
				.AVC_ORGANIZE((String) jobParameters.get("AVC_ORGANIZE"))
				.AVC_AREA((String) jobParameters.get("AVC_AREA"))
				.AVC_SECURITYKEY((String) jobParameters.get("AVC_SECURITYKEY"))
				.AI_SPID(getIntParam(jobParameters, "AI_SPID"))
				.AVC_DCCODE((String) jobParameters.get("AVC_DCCODE"))
				.I_EXECUTESTEP(getIntParam(jobParameters, "I_EXECUTESTEP"))
				.AVC_REQUESTCODE((String) jobParameters.get("AVC_REQUESTCODE"))
				.AVC_REQUESTMSG((String) jobParameters.get("AVC_REQUESTMSG"))
				.AVC_IFID((String) jobParameters.get("AVC_IFID"))
				.AVC_IFRESULT((String) jobParameters.get("AVC_IFRESULT"))
				.AVC_IFCURSTATUS((String) jobParameters.get("AVC_IFCURSTATUS"))
				.AVC_INOUTDT((String) jobParameters.get("AVC_INOUTDT"))
				.AVC_REUESTMSG((String) jobParameters.get("AVC_REUESTMSG"))
				.AN_SERIALKEY(getIntParam(jobParameters, "AN_SERIALKEY"))
				.PGMID((String) jobParameters.get("PGMID"))
				.RUNDATE((String) jobParameters.get("RUNDATE"))
				.STAT((String) jobParameters.get("STAT"))
				.MSG((String) jobParameters.get("MSG"))
				.EXENO((String) jobParameters.get("EXENO"))
				.SERIALKEY(getIntParam(jobParameters, "SERIALKEY"))
				.autoExcute((String) jobParameters.get("autoExcute"))
				.time(getLongParam(jobParameters))
				.AVC_LOG_PARAMS((String) jobParameters.get("AVC_LOG_PARAMS"))	//(20250818) BATCH LOG기록용 추가
				.build();
	}

	private static int getIntParam(Map<String, Object> params, String key) {
		Object val = params.get(key);
		return val != null ? Integer.parseInt(val.toString()) : 0;
	}

	private static Long getLongParam(Map<String, Object> params) {
		Object val = params.get("time");
		return val != null ? (Long) val : 0L;
	}



	public BatchParamsUtil getRealTimeParam(String jobName, Map<String, Object> jobParams) {
		BatchParamsUtil batchParam = new BatchParamsUtil();

		if(jobParams.get("paramCnt") != null) {
			if((Long) jobParams.get("paramCnt") > 0) {
				if(!"N".equals(jobParams.get("autoExcute"))) {
					batchParam = this.getBatchParam(jobName, jobParams);
				} else {
					batchParam = parse(jobParams);
				}
			}
		} else {
			batchParam = this.getBatchParam(jobName, jobParams);
		}

		return batchParam;
	}


	public BatchParamsUtil getObjectBatchParam(Map<String, Object> jobParams, String jobExecutionId) {
		BatchParamsUtil batchParam = new BatchParamsUtil();
		for (Map.Entry<String, Object> entry : jobParams.entrySet()) {
			String key = entry.getKey();          // 예: "IF_ID"
			Object value = entry.getValue();

			try {
				// key 값을 이용해 setter 메소드명 만들기
				String methodName = "set" + key.toUpperCase(); // 대문자 변환 주의
				// 파라미터 타입 추정
				Method[] methods = BatchParamsUtil.class.getMethods();
				for (Method method : methods) {
					if (method.getName().equals(methodName)
							&& method.getParameterCount() == 1) {
						method.invoke(batchParam, value);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//(20250815) BATCH LOG용 파라미터중 [JOB_ID] 재지정 ({JOB_ID}^{EXEC_MODE}^{LOG_LEVEL}^{NODE_LEVEL}^{PRINT_YN})
		if(batchParam.getAVC_LOG_PARAMS() != null) {
			String newLogParams = batchParam.getAVC_LOG_PARAMS().replace("JOBID", jobExecutionId);
			batchParam.setAVC_LOG_PARAMS(newLogParams);
		}

		return batchParam;
	}


    public void saveBatchStatus(String jobName, String jobStatus) {
        BatchApiRequestDto reqDto = new BatchApiRequestDto();
        reqDto.setJobName(jobName);
        reqDto.setJobStatus(jobStatus);
        commonDao.update("batchCommonService.updateBatchStatus", reqDto);
    }
}