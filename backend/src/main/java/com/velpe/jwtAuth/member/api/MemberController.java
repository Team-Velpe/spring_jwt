package com.velpe.jwtAuth.member.api;

import com.velpe.jwtAuth.global.dto.DefaultResponse;
import com.velpe.jwtAuth.global.exception.ExceptionMessage;
import com.velpe.jwtAuth.global.util.GlobalUtil;
import com.velpe.jwtAuth.member.application.MemberService;
import com.velpe.jwtAuth.member.application.MemberServiceV1;
import com.velpe.jwtAuth.member.dto.MemberDeleteForm;
import com.velpe.jwtAuth.member.dto.MemberModifyForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final GlobalUtil util;

    @GetMapping
    public DefaultResponse showAllMembers(){
        return new DefaultResponse(
                util.getListContent(memberService.findAll())
        );
    }

    @PutMapping
    public DefaultResponse modifyMemberInfo(
            @RequestBody MemberModifyForm memberModifyForm, Principal principal) {

        if(!memberModifyForm.getLoginId().equals(principal.getName())){
            throw new AccessDeniedException(ExceptionMessage.AccessDenied.getValue());
        }

        memberService.modifyInfo(memberModifyForm);

        return new DefaultResponse(
                "회원 정보수정 성공"
        );
    }

    @DeleteMapping
    public DefaultResponse deleteMember(
            @RequestBody MemberDeleteForm memberDeleteForm, Principal principal){

        if(!memberDeleteForm.getLoginId().equals(principal.getName())){
            throw new AccessDeniedException(ExceptionMessage.AccessDenied.getValue());
        }

        memberService.delete(memberDeleteForm.getLoginId());

        return new DefaultResponse(
                "탈퇴 성공"
        );
    }


}
