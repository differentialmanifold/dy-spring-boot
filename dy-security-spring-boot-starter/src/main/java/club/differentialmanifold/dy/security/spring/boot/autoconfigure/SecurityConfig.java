package club.differentialmanifold.dy.security.spring.boot.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        SecurityBeanConfig.class,
        WebSecurityConfig.class
})
public class SecurityConfig {
}
