package cjfw.batch.common;


import cjfw.batch.common.dto.BatchApiRequestDto;
import cjfw.batch.common.dto.BatchApiResponseDto;
import cjfw.batch.common.dto.QuartzParamsUtil;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Log4j2
@RestController
@RequestMapping("/batch/api")
@RequiredArgsConstructor
public class RescheduleJobController {

    private final QuartzJobRegisterService quartzJobService;

    @PostMapping("/v1.0/rescheduleJob")
    public ApiResult<BatchApiResponseDto> syncJobs() throws SchedulerException {
        BatchApiResponseDto resDto = new BatchApiResponseDto();

        try {
            quartzJobService.syncJobsFromMaster();
        } catch (SchedulerException e) {
            resDto.setReturnCode("ERR");
            resDto.setReturnMsg(e.getMessage());
            return ApiResult.createResult(resDto);
        }

        resDto.setReturnCode("OK");
        resDto.setReturnMsg("성공적으로 처리 되었습니다.");
        return ApiResult.createResult(resDto);
    }

}