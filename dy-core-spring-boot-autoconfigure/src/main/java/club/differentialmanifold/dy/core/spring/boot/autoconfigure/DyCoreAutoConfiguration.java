package club.differentialmanifold.dy.core.spring.boot.autoconfigure;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.cache.CacheConfig;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.cors.CorsConfig;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception.GlobalExceptionHandler;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.swagger.SwaggerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(DyCoreProperties.class)
@Import({
        SwaggerConfig.class,
        CorsConfig.class,
        GlobalExceptionHandler.class,
        CacheConfig.class
})
public class DyCoreAutoConfiguration{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final DyCoreProperties properties;

    public DyCoreAutoConfiguration(DyCoreProperties properties) {
        this.properties = properties;
        logger.info("dy core init success ..");
    }
}
