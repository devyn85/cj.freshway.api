package cjfw.batch.common;


import cjfw.batch.common.dto.BatchApiRequestDto;
import cjfw.batch.common.dto.BatchApiResponseDto;
import cjfw.core.model.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static cjfw.core.utility.DateUtil.isValidDate;

@Slf4j
@RestController
@RequestMapping("/batch/api")
@RequiredArgsConstructor
public class ExecuteBatchController {

	private final JobLauncher jobLauncher;
	private final ApplicationContext context; // Job을 이름으로 조회

    @Qualifier("batchAsyncExecutor")
    private final TaskExecutor batchAsyncExecutor;

	@PostMapping("/v1.0/execute")
	public ApiResult<BatchApiResponseDto> executeBatch(@RequestBody BatchApiRequestDto dto) throws Exception {
		BatchApiResponseDto resDto = new BatchApiResponseDto();

		// 전달된 jobName으로 Job Bean 조회
		Job job = context.getBean(dto.getJobName(), Job.class);

		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addString("autoExcute", "N");				// 자동 실행여부
		builder.addLong("time", System.currentTimeMillis());		// 필수 파라미터 (중복 방지용)

		int size = Optional.ofNullable(dto.getParamList())
				.map(List::size)
				.orElse(0);
		builder.addLong("paramCnt", (long) size);

		// 동적 파라미터 추가
		if(dto.getParamList() != null) {

			for (BatchApiRequestDto.Param param : dto.getParamList()) {
				String key = param.getParamName();
				String type = param.getParamType();
				String value = param.getParamValue();

				if (key == null || value == null || type == null) continue;

				switch (type.toLowerCase()) {
					case "string":
						builder.addString(key, value);
						break;
					case "number":
						try {
							builder.addLong(key, Long.parseLong(value));
						} catch (NumberFormatException e) {
                            resDto.setReturnCode("ERR");
							resDto.setReturnMsg("[실패]" + "숫자 파라미터 형식 오류: " + key + "=" + value);
						}
						break;
					case "date":
						// 날자 유효성 체크만 하고 String 형식으로 입력.
						boolean isValid = isValidDate(value, "yyyyMMdd");

						if(isValid) {
							builder.addString(key, value);
						} else {
							resDto.setReturnCode("ERR");
							resDto.setReturnMsg("[실패]" + "날짜 파라미터 형식 오류: " + key + "=" + value);
						}
						break;
					default:
						resDto.setReturnCode("ERR");
						resDto.setReturnMsg("[실패]" + "지원되지 않는 파라미터 타입: " + type);
				}
			}
		}

        // 파라미터 유효성 오류가 있으면 Job 실행 요청 자체를 중단
        if ("ERR".equals(resDto.getReturnCode())) {
            return ApiResult.createResult(resDto);
        }

        JobParameters jobParameters = builder.toJobParameters();
        batchAsyncExecutor.execute(() -> {
            try {
                jobLauncher.run(job, jobParameters);
            } catch (Exception e) {
                log.error("Batch execution failed", e);
            }
        });

        // 비동기 실행이므로 여기서는 완료/실패 판정 불가 → "요청 접수"로 응답
        resDto.setReturnCode("OK");
        resDto.setReturnMsg("Batch 실행요청 완료.\n\nSTAUS=STARTED");

        return ApiResult.createResult(resDto);
	}
}