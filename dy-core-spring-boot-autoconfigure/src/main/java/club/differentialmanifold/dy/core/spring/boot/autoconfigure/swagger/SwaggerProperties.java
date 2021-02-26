package club.differentialmanifold.dy.core.spring.boot.autoconfigure.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "dy.core.swagger")
public class SwaggerProperties {

    private Boolean enabled = true;
    private List<SwaggerVersion> versions = def();

    private List<SwaggerVersion> def(){
        List<SwaggerVersion> list = new ArrayList<>();
        list.add(new SwaggerVersion());
        return list;
    }
}
