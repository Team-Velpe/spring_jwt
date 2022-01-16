package com.velpe.jwtAuth.global.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    /**
     * JWT Entry point, JWT 인증 실패후 진행될 Entry point
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        //TODO : 로그 찍는 로직 필요
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "권한이 없습니다.");

    }

}
