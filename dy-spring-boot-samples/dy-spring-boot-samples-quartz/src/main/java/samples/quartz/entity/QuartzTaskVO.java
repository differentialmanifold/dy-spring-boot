package samples.quartz.entity;

import lombok.Builder;
import lombok.Data;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

import java.util.Map;

@Data
@Builder
public class QuartzTaskVO {
    private JobKey jobKey;

    private TriggerKey triggerKey;

    private String description;

    private String cronExpression;

    private Map<?, ?> jobDataMap;

    private Class<? extends Job> jobClass;
}
