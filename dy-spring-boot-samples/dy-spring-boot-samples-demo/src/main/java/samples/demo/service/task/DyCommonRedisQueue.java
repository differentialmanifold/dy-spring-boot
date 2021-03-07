package samples.demo.service.task;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.task.RedisQueue;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import samples.mybatis.model.City;

@Component
public class DyCommonRedisQueue implements RedisQueue<City> {

    private final String key = "dy_city_redis_queue";

    @Autowired
    StringRedisTemplate redisTemplate;


    @Override
    public City poll() {
        String str = redisTemplate.boundListOps(key).leftPop();
        if(StringUtils.isNotBlank(str))
            return JSON.parseObject(str, City.class);
        else
            return null;
    }

    @Override
    public void push(City value) {
        String str = JSON.toJSONString(value);
        redisTemplate.boundListOps(key).rightPush(str);
    }

}
