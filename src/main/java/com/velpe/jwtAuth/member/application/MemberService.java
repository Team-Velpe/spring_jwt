package com.velpe.jwtAuth.member.application;


import com.velpe.jwtAuth.member.dto.MemberSaveForm;

public interface MemberService {

    void save(MemberSaveForm memberSaveForm);

    void checkDuplicate(String loginId, String loginPw, String email);

}
