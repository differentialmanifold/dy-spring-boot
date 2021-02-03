package club.differentialmanifold.dy.core.spring.boot.autoconfigure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DyCoreApplicationTest {
    @Autowired
    private DyCoreProperties dyCoreProperties;


    @Test
    void contextLoads() {
    }

    @Test
    void testCoreApplication() {
        System.out.println("start core");
        System.out.println(dyCoreProperties.getEnv());
        Assertions.assertEquals("staging", dyCoreProperties.getEnv() );
    }
}
