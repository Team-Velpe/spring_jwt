package com.velpe.jwtAuth.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    DuplicatedLoginId("이미 사용 중인 아이디입니다."),
    DuplicatedNickname("이미 사용 중인 닉네임입니다."),
    DuplicatedEmail("이미 사용 중인 이메일입니다."),
    UsernameNotFound("존재하지 않는 회원의 아이디입니다."),
    AccessDenied("권한이 없습니다.");

    private String value;
}
