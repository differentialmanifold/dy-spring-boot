package club.differentialmanifold.dy.core.spring.boot.autoconfigure.swagger;

import lombok.Data;

@Data
public class SwaggerVersion {

    private String version = "1.0";
    private String groupName = "api-1.0";
    private String apis = "";
    private String regex = "/api/v1/.*";
    private String title = "Application API 1.0";
    private String description = "API 1.0 documentation";
    private String termsOfServiceUrl = "";
    private String license = "";
    private String licenseUrl = "";
    private String contactName = "";
    private String contactUrl = "";
    private String contactEmail = "";
}
