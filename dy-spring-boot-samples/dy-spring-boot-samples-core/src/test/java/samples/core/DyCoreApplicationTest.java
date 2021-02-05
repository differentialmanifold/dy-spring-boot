package samples.core;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.DyCoreProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DyCoreApplicationTest {
    @Autowired
    private DyCoreProperties dyCoreProperties;

    @Autowired
    private DyCoreCustomProperties dyCoreCustomProperties;

    @Test
    void contextLoads() {
    }

    @Test
    void testCoreApplication() {
        System.out.println("start core");
        System.out.println(dyCoreProperties.getEnv());
        Assertions.assertEquals("staging", dyCoreProperties.getEnv() );
    }

    @Test
    void testCoreCustomApplication() {
        System.out.println("start core custom");
        System.out.println(dyCoreCustomProperties.getCustom1());
        Assertions.assertEquals("this is custom1", dyCoreCustomProperties.getCustom1() );
    }
}
