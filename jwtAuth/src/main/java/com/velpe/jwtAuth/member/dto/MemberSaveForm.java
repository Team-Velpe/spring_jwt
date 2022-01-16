package com.velpe.jwtAuth.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSaveForm {

    private String loginId;
    private String loginPw;

    private String name;
    private String nickname;

    private String email;

}
