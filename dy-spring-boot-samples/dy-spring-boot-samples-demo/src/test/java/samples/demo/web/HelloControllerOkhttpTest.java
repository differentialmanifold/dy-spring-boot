package samples.demo.web;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.DyCoreProperties;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http.DyCoreOkhttpUtils;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http.DyCoreUtilsHttpProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import samples.SamplesDemoApplication;

@SpringBootTest(classes = SamplesDemoApplication.class)
public class HelloControllerOkhttpTest {
    @Autowired
    DyCoreOkhttpUtils dyCoreOkhttpUtils;

    @Autowired
    DyCoreUtilsHttpProperties dyCoreUtilsHttpProperties;

    @Test
    void testCoreApplication() {
        System.out.println("start core");
        System.out.println(dyCoreUtilsHttpProperties.getBasicUrl());
        Assertions.assertEquals("http://localhost:8081", dyCoreUtilsHttpProperties.getBasicUrl() );
    }

    @Test
    void testHello() throws Exception {
        String getResult = dyCoreOkhttpUtils.get("/api/hello");
        System.out.println(getResult);
    }
}
