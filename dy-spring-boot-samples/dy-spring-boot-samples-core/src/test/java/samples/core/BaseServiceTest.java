package samples.core;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.bcrypt.BCryptPasswordEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BaseServiceTest {
    @Test
    public void testBCryptPasswordEncoder() {
        String rawPassword = "admin";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(rawPassword);

        System.out.println(encodePassword);

        Assertions.assertTrue(encoder.matches(rawPassword, encodePassword));
    }
}
