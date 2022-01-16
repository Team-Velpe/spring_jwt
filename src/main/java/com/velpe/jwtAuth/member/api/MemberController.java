package com.velpe.jwtAuth.member.api;

import com.velpe.jwtAuth.auth.application.JwtProvider;
import com.velpe.jwtAuth.auth.dto.TokenResponse;
import com.velpe.jwtAuth.global.dto.DefaultResponse;
import com.velpe.jwtAuth.member.application.MemberService;
import com.velpe.jwtAuth.member.application.MemberServiceV1;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberLoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceV1 memberServiceV1;
    private final JwtProvider jwtProvider;


    @PostMapping("/api/v1/members/login")
    public DefaultResponse doLogin(@RequestBody MemberLoginForm memberLoginForm, HttpServletResponse response)  {

        // FIXME : 로그인 로직 재구성

        Member findMember = memberServiceV1.getMemberByLoginId(memberLoginForm.getLoginId());

        String accessToken = jwtProvider.issueAccessToken(findMember.getLoginId(), findMember.getAuthority());
        String refreshToken = jwtProvider.issueRefreshToken(findMember.getLoginId(), findMember.getAuthority());

        jwtProvider.setHeaderAccessToken(response,accessToken);
        jwtProvider.setHeaderRefreshToken(response, refreshToken);

        jwtProvider.saveToken(accessToken, refreshToken, findMember);

        return new DefaultResponse(
                new TokenResponse(
                        accessToken,
                        refreshToken
                )
        );


    }

    @GetMapping("/test")
    public String test(){
        return "ok";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String allExceptionHandler(Exception e){
        return e.getMessage();
    }

}
