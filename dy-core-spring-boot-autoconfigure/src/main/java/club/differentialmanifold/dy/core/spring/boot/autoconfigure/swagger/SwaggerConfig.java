package club.differentialmanifold.dy.core.spring.boot.autoconfigure.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ConditionalOnProperty(prefix = "dy.core.swagger", name = "enabled")
@Configuration
@ConditionalOnWebApplication
@AutoConfigureAfter(SwaggerProperties.class)
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SwaggerProperties properties;

//    @Autowired
//    private GenericApplicationContext context;

    public SwaggerConfig(SwaggerProperties properties, GenericApplicationContext context){
        this.properties = properties;
        this.properties.getVersions().forEach( version -> {
            ApiInfo apiInfo = new ApiInfoBuilder()
                    .title(version.getTitle())
                    .description(version.getDescription())
                    .contact(new Contact(
                            version.getContactName(),
                            version.getContactUrl(),
                            version.getContactEmail()))
                    .version(version.getVersion())
                    .license(version.getLicense())
                    .licenseUrl(version.getLicenseUrl())
                    .termsOfServiceUrl(version.getTermsOfServiceUrl())
                    .build();

            try{

                context.registerBean("docket_"+version.getVersion() , Docket.class, () -> new Docket(DocumentationType.SWAGGER_2)
                        .groupName(version.getGroupName())
                        .apiInfo(apiInfo)
                        .select()
                        .apis(StringUtils.hasLength(version.getApis()) ? RequestHandlerSelectors.basePackage(version.getApis()) : RequestHandlerSelectors.any())
                        .paths(StringUtils.hasLength(version.getRegex()) ? PathSelectors.regex(version.getRegex()) : PathSelectors.any())
                        .build());

            }catch (Exception e){
                e.printStackTrace();
            }

        });
        logger.info("swagger init success ..");
    }
}
