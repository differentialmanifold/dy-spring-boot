package samples.core;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.DyCoreProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SamplesCoreApplicationTest {
    @Autowired
    private DyCoreProperties dyCoreProperties;

    @Test
    void contextLoads() {
    }

    @Test
    void testCoreApplication() {
        System.out.println("start");
        System.out.println(dyCoreProperties.getEnv());
        Assertions.assertEquals("dev", dyCoreProperties.getEnv() );
    }
}
