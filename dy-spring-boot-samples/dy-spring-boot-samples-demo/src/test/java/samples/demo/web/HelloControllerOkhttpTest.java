package samples.demo.web;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.DyCoreProperties;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http.DyCoreOkhttpUtils;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http.DyCoreUtilsHttpProperties;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.web.WebAppConfiguration;
import samples.SamplesDemoApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = SamplesDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerOkhttpTest {
    @Autowired
    DyCoreOkhttpUtils dyCoreOkhttpUtils;

    @Autowired
    DyCoreUtilsHttpProperties dyCoreUtilsHttpProperties;

    @LocalServerPort
    int randomServerPort;

    @Test
    void testCoreApplication() {
        System.out.println("start core");
        System.out.println(dyCoreUtilsHttpProperties.getBasicUrl());
        Assertions.assertEquals("http://localhost:8081", dyCoreUtilsHttpProperties.getBasicUrl() );
    }

    @Test
    void testGetHello() throws Exception {
        String getResult = dyCoreOkhttpUtils.get("http://localhost:" + randomServerPort + "/api/hello");
        Assertions.assertEquals("Hello World", getResult );
    }

    @Test
    void testPostHello() throws Exception {
        Map<String, String> params = new HashMap<>();
        int id = 3;
        params.put("id", 3 + "");

        String postResult = dyCoreOkhttpUtils.postForm("http://localhost:" + randomServerPort + "/api/hello", params);
        Assertions.assertEquals("Hello World Post id:" + id, postResult );
    }
}
