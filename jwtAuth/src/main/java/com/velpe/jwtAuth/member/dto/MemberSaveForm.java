package com.velpe.jwtAuth.member.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class MemberSaveForm {

    private String loginId;
    private String loginPw;

    private String name;
    private String nickname;

    private String email;

}
