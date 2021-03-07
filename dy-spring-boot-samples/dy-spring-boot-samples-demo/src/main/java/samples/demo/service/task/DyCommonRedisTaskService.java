package samples.demo.service.task;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.task.RedisTaskService;
import org.springframework.stereotype.Service;
import samples.mybatis.model.City;

@Service
public class DyCommonRedisTaskService extends RedisTaskService<City> {
    private static final String threadNamePrefix = "dy-common-";

    public DyCommonRedisTaskService(DyCommonRedisQueue queue) {
        super(queue, 1, 1, threadNamePrefix);
    }

    @Override
    public void dealWith(City city) {
        logger.info("Finished dy common task, city name is: " + city.getName());
    }
}
