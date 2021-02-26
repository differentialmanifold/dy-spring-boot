package club.differentialmanifold.dy.core.spring.boot.autoconfigure.cors;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "dy.core.cors")
public class CorsProperties {

    private Boolean enabled = true;

    private String[] paths = {"/**"};

    private String[] allowedOrigins = {"*"};

    private String[] allowedMethods = {"GET","PUT","POST","DELETE","OPTIONS"};

    private String[] resolvedMethods = {"*"};

    private String[] allowedHeaders = {"*"};

    private String[] exposedHeaders = {"*"};

    private Boolean allowCredentials = false;

    private Long maxAge = 1800L;



}
