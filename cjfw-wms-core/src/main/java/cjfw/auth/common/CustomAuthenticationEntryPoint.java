package cjfw.auth.common;

import com.google.gson.Gson;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.model.ApiResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("Unauthorized {} ", authException.getMessage());

        response.setCharacterEncoding(CanalFrameConstants.DEFAULT_CHARACTERSET);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ApiResult res = ApiResult.createResult("Unauthorized", HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(new Gson().toJson(res));
    }
}
