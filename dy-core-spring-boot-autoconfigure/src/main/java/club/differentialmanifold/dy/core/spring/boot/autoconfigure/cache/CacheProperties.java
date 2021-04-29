package club.differentialmanifold.dy.core.spring.boot.autoconfigure.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "dy.core.cache")
public class CacheProperties {

    private Boolean enabled = true;
    private Integer ttl = 1800;
    private String  namespace = "dy";

}
