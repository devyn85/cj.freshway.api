package cjfw.batch.noneParamJob;


import cjfw.batch.common.BatchJobListener;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.sap.JCoUtil;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.axis.utils.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class IbAllWeightBatch {
	private transient static final String SAP_FUNCTION_NAME = "ZCO_WMS_OUT_LEDGER";
	private transient static final String SAP_TABLE_NAME = "ZCOS_WMS_OUT";
	private final CommonDao commonDao;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private BatchJobListener batchJobListener;

	@Bean
	public Job ibAllWeightJob() {
		return new JobBuilder("ibAllWeightJob", jobRepository)
				.listener(batchJobListener)
				.flow(execStep())
				.end()
				.build();
	}

	@Bean
	@StepScope
	public Step execStep() {
		return new StepBuilder("execStep", jobRepository)
				.tasklet(execTasklet(), transactionManager)
				.build();
	}

	public Tasklet execTasklet() {
		return (contribution, chunkContext) -> {
			// 배치수행로그
						BatchLogParamsDto batchLogDto = BatchLogParamsDto.builder()
								.jobExecutionId(Long.toString(chunkContext.getStepContext().getStepExecution().getJobExecution().getId()))
								.jobName("ibAllWeightJob")
								.jobDiv("JAVA")
								.nodeLevel(0)
								.jobStatus("START")
								.command("")
								.lineNo("80")
								.resultCode("0")
								.resultMsg("")
								.build();
			try {
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
				batchLogDto.setLineNo("82");
				JCoDestination destination = JCoUtil.getDestination();
				JCoFunction function = JCoUtil.getFunction(SAP_FUNCTION_NAME);

				if (function == null) {
					throw new Exception(SAP_FUNCTION_NAME + " not found in SAP.");
				}

				JCoParameterList importParams = function.getImportParameterList();
				importParams.setValue("I_BDATJ", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy")));
				importParams.setValue("I_POPER", "0"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM")));

				try {
					function.execute(destination);
				}catch (Exception e) {}

				JCoParameterList exportParams = function.getExportParameterList();
				log.debug(exportParams.toString());

				String eReturn = exportParams.getString("E_RETURN"); // 메시지
				log.debug("eReturn:" + eReturn);

				JCoTable table = function.getTableParameterList().getTable(SAP_TABLE_NAME);
				log.debug("table:", table);

				for (int i = 0; i < table.getNumRows(); i++) {
					table.setRow(i);
					String SBQTY31 = table.getString("SBQTY31"); //판매출고수량
					String SBAMT31 = table.getString("SBAMT31"); //판매출고금액
					String BWKEY = table.getString("BWKEY");

					log.debug(SBQTY31);
					log.debug(SBAMT31);
					log.debug(BWKEY);

					if(StringUtils.isEmpty(BWKEY)) {
						continue;
					}

					Map<String, Object> paramMap = new HashMap<>();
					paramMap.put("fromSttlBaseDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM")));
					paramMap.put("toSttlBaseDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM")));
					paramMap.put("dccode", BWKEY);
					paramMap.put("qty", SBQTY31);
					paramMap.put("price", SBAMT31);
					paramMap.put("editwho", "batch");
                	commonDao.insertQuartz("ibAllWeightService.saveIbAllWeightBatch", paramMap);
				}
			} catch (Exception e) {
				batchLogDto.setResultCode("-1");
				batchLogDto.setResultMsg(e.toString());
			} finally {
				batchLogDto.setJobStatus("END");
				batchLogDto.setLineNo("130");
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
			}

			return RepeatStatus.FINISHED;
		};
	}
}