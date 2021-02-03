package samples.demo.web;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String helloPost(@RequestParam("id") long id) {
        return "Hello World Post id:" + id;
    }
}
