package club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.DyCoreProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DyCoreUtilsHttpApplicationTest {
    @Autowired
    private DyCoreOkhttpUtils dyCoreOkhttpUtils;


    @Test
    void contextLoads() {
    }

    @Test
    void testCoreApplication() {
        System.out.println("start core");
        String result = dyCoreOkhttpUtils.get("https://www.baidu.com", null);
        Assertions.assertTrue(result.contains("百度一下"));
    }
}
