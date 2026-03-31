package cjfw.batch.common;


import cjfw.batch.common.dto.QuartzParamsUtil;
import cjfw.core.dataaccess.CommonDao;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
@DisallowConcurrentExecution
public class QuartzJobRegister {

    private final QuartzJobRegisterService quartzJobService;

    @PostConstruct
    public void init() throws SchedulerException {
        quartzJobService.syncJobsFromMaster();
    }
}