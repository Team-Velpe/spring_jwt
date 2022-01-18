package com.velpe.jwtAuth.auth.api;

import com.velpe.jwtAuth.auth.application.JwtProvider;
import com.velpe.jwtAuth.auth.dto.TokenResponse;
import com.velpe.jwtAuth.global.dto.DefaultResponse;
import com.velpe.jwtAuth.member.application.MemberService;
import com.velpe.jwtAuth.member.application.MemberServiceV1;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberLoginForm;
import com.velpe.jwtAuth.member.dto.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sign")
public class SignController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/in")
    public DefaultResponse doLogin(@RequestBody MemberLoginForm memberLoginForm, HttpServletResponse response)  {

        // FIXME : 로그인 로직 재구성

        Member findMember = memberService.getMemberByLoginId(memberLoginForm.getLoginId());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(!passwordEncoder.matches(memberLoginForm.getLoginPw(), findMember.getLoginPw())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberLoginForm.getLoginId(), memberLoginForm.getLoginPw());
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtProvider.issueAccessToken(findMember.getLoginId(), findMember.getAuthority());
        String refreshToken = jwtProvider.issueRefreshToken(findMember.getLoginId(), findMember.getAuthority());

        jwtProvider.saveToken(refreshToken, findMember);

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
