package samples.demo.web;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception.CodeException;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception.CommonCode;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags ="DY HelloController")
@RequestMapping("/api")
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiOperation(value = "测试hello world", notes = "note: hello world")
    public ResponseResult<String> hello() {
        logger.info("hello world");
        return ResponseResult.success("Hello World");
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String helloPost(@RequestParam("id") long id) {
        return "Hello World Post id:" + id;
    }

    @RequestMapping(value = "/hello-exception", method = RequestMethod.GET)
    @ApiOperation(value = "测试hello world exception", notes = "note: hello world exception")
    public String helloException() {
        logger.info("hello world exception");
        throw new CodeException(CommonCode.ACCESS_DENIED);
    }
}
