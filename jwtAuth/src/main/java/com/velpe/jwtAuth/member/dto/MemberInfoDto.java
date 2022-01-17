package com.velpe.jwtAuth.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberInfoDto {

    private String loginId;
    private String nickname;
    private String email;

}
