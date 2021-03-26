package club.differentialmanifold.dy.security.spring.boot.autoconfigure;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception.CommonCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        Map<String, Object> map = new HashMap<>();
        map.put("errcode", CommonCode.INVALID_ACCESSTOKEN.getErrcode());
        map.put("errmsg", CommonCode.INVALID_ACCESSTOKEN.getErrmsg());

        ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(map));
    }
}