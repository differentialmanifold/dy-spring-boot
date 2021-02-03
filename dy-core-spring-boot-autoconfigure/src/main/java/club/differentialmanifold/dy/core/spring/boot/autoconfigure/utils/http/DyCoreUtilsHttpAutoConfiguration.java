package club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@ConditionalOnProperty(prefix = "dy.core.utils.http", name = "enabled")
@Configuration
@EnableConfigurationProperties(DyCoreUtilsHttpProperties.class)
public class DyCoreUtilsHttpAutoConfiguration {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final DyCoreUtilsHttpProperties properties;

    public DyCoreUtilsHttpAutoConfiguration(DyCoreUtilsHttpProperties properties) {
        this.properties = properties;
        logger.info("dy core utils http init success ..");
    }

    private OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectionPool(pool())
                .connectTimeout(properties.getTimeout(), TimeUnit.SECONDS)
                .readTimeout(properties.getTimeout(), TimeUnit.SECONDS)
                .writeTimeout(properties.getTimeout(),TimeUnit.SECONDS)
                .build();
    }

    private ConnectionPool pool() {
        return new ConnectionPool(200, 5, TimeUnit.MINUTES);
    }

    @Bean
    public DyCoreOkhttpUtils dyCoreOkhttpUtils() {
        DyCoreOkhttpUtils dyCoreOkhttpUtils = new DyCoreOkhttpUtils(okHttpClient(), properties);
        return dyCoreOkhttpUtils;
    }
}
