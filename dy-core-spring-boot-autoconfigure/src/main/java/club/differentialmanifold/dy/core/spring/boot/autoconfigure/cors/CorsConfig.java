package club.differentialmanifold.dy.core.spring.boot.autoconfigure.cors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@ConditionalOnProperty(prefix = "dy.core.cors", name = "enabled")
@Configuration
@AutoConfigureAfter(CorsProperties.class)
@EnableConfigurationProperties(CorsProperties.class)
public class CorsConfig implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CorsProperties properties;

    public CorsConfig(CorsProperties properties) {
        this.properties = properties;
        logger.info("cors init success ..");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(properties.getAllowedOrigins())
                .allowedMethods(properties.getAllowedMethods())
                .allowCredentials(properties.getAllowCredentials())
                .maxAge(properties.getMaxAge());
    }


    @Bean
    public FilterRegistrationBean corsFilterBean() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowCredentials(properties.getAllowCredentials());
        corsConfiguration.setAllowedOrigins(Arrays.asList(properties.getAllowedOrigins()));
        corsConfiguration.setAllowedHeaders(Arrays.asList(properties.getAllowedHeaders()));
        corsConfiguration.setAllowedMethods(Arrays.asList(properties.getAllowedMethods()));
        Arrays.asList(properties.getPaths()).forEach(str -> source.registerCorsConfiguration(str, corsConfiguration));

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0); // 必须在所有Filter之前
        return bean;
    }

}
