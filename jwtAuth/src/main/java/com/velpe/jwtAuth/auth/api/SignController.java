package com.velpe.jwtAuth.auth.api;

import com.velpe.jwtAuth.auth.application.JwtProvider;
import com.velpe.jwtAuth.auth.dto.TokenResponse;
import com.velpe.jwtAuth.global.dto.DefaultResponse;
import com.velpe.jwtAuth.member.application.MemberServiceV1;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberLoginForm;
import com.velpe.jwtAuth.member.dto.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sign")
public class SignController {

    private final MemberServiceV1 memberService;
    private final JwtProvider jwtProvider;

    @PostMapping("/in")
    public DefaultResponse doLogin(@RequestBody MemberLoginForm memberLoginForm, HttpServletResponse response)  {

        // FIXME : 로그인 로직 재구성

        Member findMember = memberService.getMemberByLoginId(memberLoginForm.getLoginId());

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

    @PostMapping("/up")
    public DefaultResponse doJoin(@RequestBody MemberSaveForm memberSaveForm) throws Exception {
        memberService.save(memberSaveForm);
        return new DefaultResponse(
                "가입 성공"
        );
    }
}
