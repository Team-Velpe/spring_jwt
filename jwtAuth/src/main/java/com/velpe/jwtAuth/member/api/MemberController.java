package com.velpe.jwtAuth.member.api;

import com.velpe.jwtAuth.global.dto.DefaultResponse;
import com.velpe.jwtAuth.global.util.GlobalUtil;
import com.velpe.jwtAuth.member.application.MemberService;
import com.velpe.jwtAuth.member.application.MemberServiceV1;
import com.velpe.jwtAuth.member.dto.MemberModifyForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final GlobalUtil util;

    @GetMapping("")
    public DefaultResponse showAllMembers(){
        System.out.println("members : " + memberService.findAll().toString());
        return new DefaultResponse(
                util.getListContent(memberService.findAll())
        );
    }

    @PutMapping("/{memberId}")
    public DefaultResponse modifyMemberInfo(
            @RequestBody MemberModifyForm memberModifyForm, Principal principal) throws Exception {

        memberService.modifyInfo(memberModifyForm);

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


}
