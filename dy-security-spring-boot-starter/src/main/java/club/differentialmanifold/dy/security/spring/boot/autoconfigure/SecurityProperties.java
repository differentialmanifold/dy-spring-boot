package club.differentialmanifold.dy.security.spring.boot.autoconfigure;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "dy.security")
public class SecurityProperties {

    private Jwt jwt;
    private PermitMatch corsPermit;
    private PermitMatch resourcePermit;
    private PermitMatch authPermit;

    public static class Jwt {

        private String header = "Authorization";
        private String tokenHead = "Bearer ";
        private Long   expiration = 360000L;
        private String secret = "1100";

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getTokenHead() {
            return tokenHead;
        }

        public void setTokenHead(String tokenHead) {
            this.tokenHead = tokenHead;
        }

        public Long getExpiration() {
            return expiration;
        }

        public void setExpiration(Long expiration) {
            this.expiration = expiration;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }

    public static class PermitMatch{
        String [] patterns;

        public String[] getPatterns() {
            return patterns;
        }

        public void setPatterns(String[] patterns) {
            this.patterns = patterns;
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this);
        }
    }
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
