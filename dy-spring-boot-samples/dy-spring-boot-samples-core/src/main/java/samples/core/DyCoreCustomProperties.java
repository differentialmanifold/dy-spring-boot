package samples.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "dy.core.custom")
public class DyCoreCustomProperties {

  String custom1 = "custom1";

  String custom2 = "custom2";
}
