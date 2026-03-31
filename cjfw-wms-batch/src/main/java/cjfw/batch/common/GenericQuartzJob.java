package cjfw.batch.common;

import cjfw.batch.common.dto.BatchParamsUtil;
import lombok.extern.log4j.Log4j2;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static cjfw.core.utility.DateUtil.isValidDate;

@Log4j2
@Component
public class GenericQuartzJob extends QuartzJobBean {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private SetParamService setParamService;

    @Autowired private JobExplorer jobExplorer;   // 중요

	@Override
	public void executeInternal(JobExecutionContext quartzContext) throws JobExecutionException {
		String jobName = quartzContext.getMergedJobDataMap().getString("jobName");

		try {
			Job batchJob = applicationContext.getBean(jobName, Job.class);

			// 배치 파라미터 조회.
			Map<String, Object> jobParams = new HashMap<String, Object>();
			BatchParamsUtil batchParam = setParamService.getRealTimeParam(jobName, jobParams);

			// JobParametersBuilder 생성
			JobParametersBuilder builder = new JobParametersBuilder()
					.addLong("timestamp", System.currentTimeMillis())
					.addString("jobName", jobName);

			if (batchParam.getJobParams() != null) {
				for (Map.Entry<String, Object> entry : batchParam.getJobParams().entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();

					if (value instanceof String) {
						builder.addString(key, (String) value);
					} else if (value instanceof Long) {
						builder.addLong(key, (Long) value);
					} else if (value instanceof Double) {
						builder.addDouble(key, (Double) value);
					} else if (value instanceof Date) {
						// 날자 유효성 체크만 하고 String 형식으로 입력.
						boolean isValid = isValidDate(String.valueOf((Date) value), "yyyyMMdd");
						if(isValid) {
							builder.addDate(key, (Date) value);
						}
					} else {
						// 기본적으로 toString 으로 처리
						builder.addString(key, value.toString());
					}
				}
			}

			jobLauncher.run(batchJob, builder.toJobParameters());
		} catch (Exception e) {
			throw new JobExecutionException("Spring Batch Job 실행 실패: " + jobName, e);
		}
	}
}