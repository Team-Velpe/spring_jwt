package com.velpe.jwtAuth.auth.application;


import com.velpe.jwtAuth.auth.dao.AuthRepository;
import com.velpe.jwtAuth.auth.dao.BlackListRepository;
import com.velpe.jwtAuth.auth.domain.AuthStorage;
import com.velpe.jwtAuth.member.application.MemberService;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private static SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode("d3Bxa2Z3aGFlaGx3a2RscnBhdWNxanNXb3NpZHBma2RsdG1xamZrZmRqc3dwZGJydGxxdGtya2VobGZ3bGFoZm1ycFR3bGFrc3RscWtmZGxyanNka2Zka3FocnBUd2xxbmZka3Nna3NsUmtx"));

    private long accessTokenExpMs = 1000 * 60 * 30L;
    private long refreshTokenExpMs = 1000 * 60 * 60 * 24L;

    private final MemberService memberService;
    private final AuthRepository authRepository;
    private final BlackListRepository blackListRepository;


    /**
     * Access Token 생성
     * @param loginId
     * @param authority
     * @return Access Token
     */
    public String issueAccessToken(String loginId, Role authority) {
        return this.issueToken(loginId, authority, accessTokenExpMs);
    }

    /**
     * Refresh Token 생성
     * @param loginId
     * @param authority
     * @return Refresh Token
     */
    public String issueRefreshToken(String loginId, Role authority) {
        return this.issueToken(loginId, authority, refreshTokenExpMs);
    }


    /**
     * JWT 토큰 생성
     * @param loginId
     * @param authority
     * @param tokenSort
     * @return
     */
    public String issueToken(String loginId, Role authority, long tokenSort) {

        Claims claims = Jwts.claims().setSubject(loginId);
        claims.put("authority", authority);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + tokenSort))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();


    }

    public Authentication getAuthentication(String token) {

        try {

            Member findMember = memberService.getMemberByLoginId(this.getLoginId(token));
            return new UsernamePasswordAuthenticationToken(findMember, "", findMember.getAuthorities());

        } catch ( NoSuchElementException e ) {

            System.out.println( e.getMessage() );
            return null;

        }

    }

    public String getLoginId(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();

    }



    public boolean validateToken(String token) {

        try {

            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJwt(token);

            return true;

        } catch ( SignatureException e ) {
            System.out.println("Invalid Token signature");
        } catch ( MalformedJwtException e) {
            System.out.println("Invalid Token");
        } catch ( ExpiredJwtException e ) {
            System.out.println("Expired Token");
        } catch ( UnsupportedJwtException e  ) {
            System.out.println("Unsupported Token");
        } catch ( IllegalStateException e ) {
            System.out.println("JWT claims String is empty");
        } catch ( Exception e ) {
            System.out.println("error" +e.getMessage());
        }

        // TODO : Exception 추가

        return false;
    }

    public String getAccessToken(HttpServletRequest req) {

        if ( req.getHeader("authorization") != null ) {
            return req.getHeader("authorization").substring(7);
        }

        return null;

    }

    public String getRefreshToken(HttpServletRequest req) {

        if ( req.getHeader("refreshToken") != null ) {
            return req.getHeader("refreshToken").substring(7);
        }

        return null;

    }

    public void setHeaderAccessToken(HttpServletResponse resp, String accessToken) {
        resp.addHeader("authorization", "Bearer " + accessToken);
    }

    public void setHeaderRefreshToken(HttpServletResponse resp, String refreshToken) {
        resp.setHeader("refreshToken", "Bearer " + refreshToken);
    }

    public boolean isValidRefToken(String refreshToken) {
        return authRepository.existsByRefreshToken(refreshToken);
    }

    public boolean isBannedRefToken(String refreshToken) {
        return blackListRepository.existsByRefreshToken(refreshToken);
    }

    public Role getAuthority(String loginId) {
        return memberService.getMemberByLoginId(loginId).getAuthority();
    }

    public void saveToken(String refreshToken, Member member) {

        AuthStorage authStorage = AuthStorage.createAuthStorage(
//                accessToken,
                refreshToken,
                member
        );

        authRepository.save(authStorage);

    }

}
