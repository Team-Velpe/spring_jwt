package com.velpe.jwtAuth.member.api;

import com.velpe.jwtAuth.auth.application.JwtProvider;
import com.velpe.jwtAuth.auth.dto.TokenResponse;
import com.velpe.jwtAuth.global.dto.DefaultResponse;
import com.velpe.jwtAuth.global.util.GlobalUtil;
import com.velpe.jwtAuth.member.application.MemberService;
import com.velpe.jwtAuth.member.application.MemberServiceV1;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberInfoDto;
import com.velpe.jwtAuth.member.dto.MemberLoginForm;
import com.velpe.jwtAuth.member.dto.MemberModifyForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberServiceV1 memberService;
    private final GlobalUtil util;

    @GetMapping
    public DefaultResponse showAllMembers(){
        System.out.println("members : " + memberService.findAll().toString());
        return new DefaultResponse(
                util.getListContent(memberService.findAll())
        );
    }

    @PutMapping("/{memberId}")
    public DefaultResponse modifyMemberInfo(
            @PathVariable Long memberId, @RequestBody MemberModifyForm memberModifyForm) throws Exception {
        memberService.modifyInfo(memberId, memberModifyForm);

        return new DefaultResponse(
                "회원 정보수정 성공"
        );
    }

    @DeleteMapping("/{memberId}")
    public DefaultResponse deleteMember(@PathVariable Long memberId){
        memberService.delete(memberId);

        return new DefaultResponse(
                "탈퇴 성공"
        );
    }


    // 삭제 필요
    @GetMapping("/test")
    public String test(){
        return "ok";
    }


}
