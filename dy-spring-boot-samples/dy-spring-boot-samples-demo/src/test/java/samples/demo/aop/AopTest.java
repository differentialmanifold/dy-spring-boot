package samples.demo.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import samples.SamplesDemoApplication;

@SpringBootTest(classes = SamplesDemoApplication.class)
public class AopTest {
    @Autowired
    AopService aopService;

    @Test
    void testAop() throws Exception {
        aopService.getAllUser("abc");
    }
}
