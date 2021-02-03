package club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "dy.core.utils.http")
public class DyCoreUtilsHttpProperties {
    /**
     * enabled
     */
    Boolean enabled;

    /**
     * basic url
     */
    String basicUrl;

    /**
     * timeout unit: seconds
     */
    Long timeout;
}
