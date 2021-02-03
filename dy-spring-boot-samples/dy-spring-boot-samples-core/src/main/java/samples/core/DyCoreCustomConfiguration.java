package samples.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DyCoreCustomProperties.class)
public class DyCoreCustomConfiguration {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final DyCoreCustomProperties properties;

    public DyCoreCustomConfiguration(DyCoreCustomProperties properties) {
        this.properties = properties;
        logger.info("dy core custom init success ..");
    }
}
