package com.velpe.jwtAuth.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberModifyForm {
    private String nickname;

    // 로그인한 회원의 정보를 위해 token에서 받아 넘겨주기
    private String loginId;
}
