package club.differentialmanifold.dy.security.spring.boot.autoconfigure;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil implements Serializable {

    protected static final long serialVersionUID = 1L;
    protected static final String CLAIM_KEY_USERNAME = "sub";
    protected static final String CLAIM_KEY_CREATED = "created";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final SecurityProperties properties;

    public JwtTokenUtil(SecurityProperties properties) {
        this.properties = properties;
    }

    public String getUsername(String headToken) {
        headToken = headToken.substring(this.properties.getJwt().getTokenHead().length()).trim();
        return getUsernameFromToken(headToken);
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
//            logger.error("token failure -> {}", e.getMessage(), e);
            username = null;
//            throw new SecurityException("");
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
//            logger.error("token failure -> {}", e.getMessage(), e);
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
//            logger.error("token failure -> {}", e.getMessage(), e);
            expiration = null;
        }
        return expiration;
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(properties.getJwt().getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
//            logger.error("token failure -> {}", e.getMessage(), e);
            claims = null;
        }
        return claims;
    }

    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + properties.getJwt().getExpiration() * 1000);
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, properties.getJwt().getSecret())
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            logger.error("token failure -> {}", e.getMessage(), e);
            refreshedToken = null;
        }
        return refreshedToken;
    }
}