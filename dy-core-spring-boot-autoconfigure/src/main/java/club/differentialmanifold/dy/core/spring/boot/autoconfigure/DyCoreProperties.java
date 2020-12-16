package club.differentialmanifold.dy.core.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "dy.core")
public class DyCoreProperties {
  /**
   * environment
   */
  String env = "dev";
}
