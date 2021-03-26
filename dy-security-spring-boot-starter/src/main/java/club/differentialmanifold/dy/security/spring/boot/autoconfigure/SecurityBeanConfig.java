package club.differentialmanifold.dy.security.spring.boot.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "dy.security", name = "enabled")
@Configuration
@AutoConfigureAfter(SecurityProperties.class)
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityBeanConfig {

    private final SecurityProperties properties;

    public SecurityBeanConfig(SecurityProperties properties) {
        this.properties = properties;
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil(properties);
    }

    @Bean
    public JwtRequestFilter jwtAuthenticationTokenFilter(){
        return new JwtRequestFilter(jwtTokenUtil(), properties);
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint(){
        return new JwtAuthenticationEntryPoint();
    }


}
