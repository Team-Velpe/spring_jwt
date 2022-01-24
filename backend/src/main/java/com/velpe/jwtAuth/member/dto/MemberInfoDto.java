package com.velpe.jwtAuth.member.dto;

import com.velpe.jwtAuth.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class MemberInfoDto {

    private String loginId;
    private String name;
    private String nickname;
    private String email;

    public MemberInfoDto(Member member){
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
    }

}
