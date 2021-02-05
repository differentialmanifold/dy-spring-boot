package club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DyCoreUtilsHttpApplicationTest {

    @Test
    void contextLoads() {
    }

    @Test
    void testCoreApplication() {
        System.out.println("start core utils http");
        String result = DyOkhttpUtils.get()
                .url("https://www.baidu.com")
                .build()
                .execute();
        Assertions.assertTrue(result.contains("百度一下"));
    }
}
