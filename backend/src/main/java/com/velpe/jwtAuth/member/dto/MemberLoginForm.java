package com.velpe.jwtAuth.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MemberLoginForm {

    private String loginId;
    private String loginPw;

}
