package samples.demo.web;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samples.quartz.entity.QuartzTaskVO;
import samples.quartz.jobs.SayHelloJob;
import samples.quartz.service.QuartzJobService;

@RestController
@RequestMapping("/api/quartz")
public class QuartzController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuartzJobService quartzJobService;

    @RequestMapping("/create")
    public String quartzStart() throws SchedulerException {
        String cron = "0/5 * * * * ?";
        JobKey jobKey = JobKey.jobKey("job1", "group1");
        TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "group1");

        //创建一个定时任务
        QuartzTaskVO task = QuartzTaskVO.builder()
                .jobKey(jobKey)
                .triggerKey(triggerKey)
                .cronExpression(cron)
                .jobClass(SayHelloJob.class)
                .description("这是一个测试的任务")
                .build();

        quartzJobService.scheduleJob(task);
        return "create";
    }

    @GetMapping("/pause")
    public String pauseJob() throws SchedulerException {
        JobKey jobKey = JobKey.jobKey("job1", "group1");

        quartzJobService.pauseJob(jobKey);
        return "pauseJob";
    }

    @GetMapping("/resume")
    public String resumeJob() throws SchedulerException {
        JobKey jobKey = JobKey.jobKey("job1", "group1");

        quartzJobService.resumeJob(jobKey);
        return "resume";
    }

    @GetMapping("/remove")
    public String removeJob() throws SchedulerException {
        JobKey jobKey = JobKey.jobKey("job1", "group1");
        TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "group1");

        quartzJobService.deleteJob(jobKey, triggerKey);

        return "remove";
    }

}
