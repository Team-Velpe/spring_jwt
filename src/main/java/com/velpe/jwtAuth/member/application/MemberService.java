package com.velpe.jwtAuth.member.application;


import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberSaveForm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MemberService extends UserDetailsService {

    void save(MemberSaveForm memberSaveForm);

    void checkDuplicate(String loginId, String loginPw, String email);

    Member getMemberByLoginId(String loginId);

}
