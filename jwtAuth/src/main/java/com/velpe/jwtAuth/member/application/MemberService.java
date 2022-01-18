package com.velpe.jwtAuth.member.application;


import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberInfoDto;
import com.velpe.jwtAuth.member.dto.MemberModifyForm;
import com.velpe.jwtAuth.member.dto.MemberSaveForm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface MemberService extends UserDetailsService {

    void save(MemberSaveForm memberSaveForm) throws Exception;

    void modifyInfo(MemberModifyForm memberModifyForm) throws Exception;

    void delete(Long memberId);

    Member findByLoginId(String loginId);

    List<MemberInfoDto> findAll();

    void checkDuplicate(String loginId, String loginPw, String email);

    Member getMemberByLoginId(String loginId);

}
