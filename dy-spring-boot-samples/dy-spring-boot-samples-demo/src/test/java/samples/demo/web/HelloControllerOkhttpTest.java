package samples.demo.web;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http.DyOkhttpUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import samples.SamplesDemoApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = SamplesDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerOkhttpTest {

    @LocalServerPort
    int randomServerPort;

    @Test
    void testGetHello() throws Exception {
        String getResult = DyOkhttpUtils.get()
                .url("http://localhost:" + randomServerPort + "/api/hello")
                .build()
                .execute();
        Assertions.assertEquals("Hello World", getResult );
    }

    @Test
    void testPostHello() throws Exception {
        Map<String, String> params = new HashMap<>();
        int id = 3;
        params.put("id", 3 + "");

        String postResult = DyOkhttpUtils.post()
                .url("http://localhost:" + randomServerPort + "/api/hello")
                .params(params)
                .build()
                .execute();
        Assertions.assertEquals("Hello World Post id:" + id, postResult );
    }
}
