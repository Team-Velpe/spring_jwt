package com.velpe.jwtAuth.member.api;

import com.velpe.jwtAuth.auth.application.JwtProvider;
import com.velpe.jwtAuth.auth.dto.TokenResponse;
import com.velpe.jwtAuth.global.dto.DefaultResponse;
import com.velpe.jwtAuth.member.application.MemberService;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberLoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;


    @PostMapping("/api/v1/members/login")
    public DefaultResponse doLogin(@RequestBody MemberLoginForm memberLoginForm, HttpServletResponse response)  {

        // FIXME : 로그인 로직 재구성
        Member findMember = memberService.getMemberByLoginId(memberLoginForm.getLoginId());

        String accessToken = jwtProvider.issueAccessToken(findMember.getLoginId(), List.of(findMember.getAuthority()));
        String refreshToken = jwtProvider.issueRefreshToken(findMember.getLoginId(), List.of(findMember.getAuthority()));

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

}
