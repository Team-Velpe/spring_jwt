package com.velpe.jwtAuth.global.config;

import com.velpe.jwtAuth.auth.application.JwtProvider;
import com.velpe.jwtAuth.member.dto.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// 요청 들어올때 딱한번 실행되는 Filter
//@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = jwtProvider.getAccessToken(request);
        String refreshToken = jwtProvider.getRefreshToken(request);

        System.out.println("accessTokenInAuthFilter : " + accessToken);
        System.out.println("refreshTokenInAuthFilter : " + refreshToken);

        if ( accessToken != null ) {


            if (jwtProvider.validateToken(accessToken)) {
                setAuthentication(accessToken);
            } else if ( !jwtProvider.validateToken(accessToken) && refreshToken != null ) {


                boolean isValidRefToken = jwtProvider.validateToken(refreshToken);
                boolean isIssuedRefToken = jwtProvider.isValidRefToken(refreshToken);
                boolean isBannedRefToken = jwtProvider.isBannedRefToken(refreshToken);

                if (isBannedRefToken) {
                    filterChain.doFilter(request, response);
                }

                if ( isValidRefToken && isIssuedRefToken ) {

                    String loginId = jwtProvider.getLoginId(refreshToken);
                    Role authority = jwtProvider.getAuthority(loginId);

                    String newAccessToken = jwtProvider.issueAccessToken(loginId, authority);

                    jwtProvider.setHeaderAccessToken(response, newAccessToken);

                    setAuthentication(newAccessToken);

                }


            }

            filterChain.doFilter(request, response);
        }

        filterChain.doFilter(request, response);

    }


    public void setAuthentication(String token) {

        Authentication authentication = jwtProvider.getAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }


}
