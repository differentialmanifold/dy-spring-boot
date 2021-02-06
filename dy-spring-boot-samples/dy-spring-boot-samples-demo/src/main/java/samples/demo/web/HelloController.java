package samples.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello")
    public String hello() {
        logger.info("hello world");
        return "Hello World";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String helloPost(@RequestParam("id") long id) {
        return "Hello World Post id:" + id;
    }
}
