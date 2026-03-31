package cjfw.batch.common;


import cjfw.batch.common.dto.QuartzParamsUtil;
import cjfw.core.dataaccess.CommonDao;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
@DisallowConcurrentExecution
public class QuartzJobRegisterService {

    private final Scheduler scheduler;
    private final CommonDao commonDao;

    /**
     * DB 기준 전체 동기화
     * - useYn=Y : 등록/수정
     * - useYn=N : 해제
     */
    public void syncJobsFromMaster() throws SchedulerException {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("useYn", "Y");
        List<QuartzParamsUtil> activeQuartzList =
                commonDao.selectList("quartz.selectJobList", (Object) paramMap);

        for (QuartzParamsUtil dto : activeQuartzList) {
            registerOrUpdateJob(dto.getJobName(), dto.getCronExpression());
        }

        paramMap.put("useYn", "N");
        List<QuartzParamsUtil> inactiveQuartzList =
                commonDao.selectList("quartz.selectJobList", (Object) paramMap);

        for (QuartzParamsUtil dto : inactiveQuartzList) {
            unscheduleJob(dto.getJobName());
        }
    }

    /**
     * 단건 등록/수정
     */
    public void registerOrUpdateJob(String jobName, String schedule) throws SchedulerException {
        String triggerName = jobName + "Trigger";

        JobKey jobKey = JobKey.jobKey(jobName);
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName);

        JobDetail jobDetail = JobBuilder.newJob(GenericQuartzJob.class)
                .withIdentity(jobKey)
                .usingJobData("jobName", jobName)
                .storeDurably()
                .build();

        CronTrigger newTrigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(triggerKey)
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(schedule)
                                .withMisfireHandlingInstructionDoNothing()
                )
                .build();

        // 1) Job 자체가 없으면 신규 등록
        if (!scheduler.checkExists(jobKey)) {
            scheduler.scheduleJob(jobDetail, newTrigger);
            log.info("Quartz job registered. jobName={}, cron={}", jobName, schedule);
            return;
        }

        // 2) Job은 있는데 Trigger가 없으면 Trigger만 등록
        Trigger existingTrigger = scheduler.getTrigger(triggerKey);
        if (existingTrigger == null) {
            if (!scheduler.checkExists(jobKey)) {
                scheduler.addJob(jobDetail, true);
            }
            scheduler.scheduleJob(newTrigger);
            log.info("Quartz trigger created. jobName={}, cron={}", jobName, schedule);
            return;
        }

        // 3) CronTrigger 이면 cron 비교 후 변경 시 reschedule
        if (existingTrigger instanceof CronTrigger existingCronTrigger) {
            String currentCron = existingCronTrigger.getCronExpression();

            if (!currentCron.equals(schedule)) {
                scheduler.rescheduleJob(triggerKey, newTrigger);
                log.info("Quartz trigger rescheduled. jobName={}, oldCron={}, newCron={}",
                        jobName, currentCron, schedule);
            } else {
                log.info("Quartz job unchanged. jobName={}, cron={}", jobName, schedule);
            }
            return;
        }

        // 4) 예상치 못한 Trigger 타입이면 교체
        scheduler.unscheduleJob(triggerKey);
        scheduler.scheduleJob(newTrigger);
        log.info("Quartz trigger replaced. jobName={}, cron={}", jobName, schedule);
    }

    /**
     * 단건 해제
     */
    public void unscheduleJob(String jobName) throws SchedulerException {
        String triggerName = jobName + "Trigger";

        JobKey jobKey = JobKey.jobKey(jobName);
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName);

        if (scheduler.checkExists(triggerKey)) {
            scheduler.unscheduleJob(triggerKey);
            log.info("Quartz trigger unscheduled. jobName={}", jobName);
        }

        if (scheduler.checkExists(jobKey) && scheduler.getTriggersOfJob(jobKey).isEmpty()) {
            scheduler.deleteJob(jobKey);
            log.info("Quartz job deleted. jobName={}", jobName);
        }
    }
}