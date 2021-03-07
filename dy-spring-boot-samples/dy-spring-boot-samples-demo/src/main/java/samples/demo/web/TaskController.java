package samples.demo.web;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception.CodeException;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception.CommonCode;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import samples.demo.service.task.DyCommonRedisTaskService;
import samples.mybatis.model.City;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DyCommonRedisTaskService dyCommonRedisTaskService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiOperation(value = "测试hello world", notes = "note: hello world")
    public ResponseResult<Object> hello() {
        logger.info("start task");
        City city = new City();
        city.setName("a task city");
        dyCommonRedisTaskService.put(city);
        return ResponseResult.success();
    }
}
