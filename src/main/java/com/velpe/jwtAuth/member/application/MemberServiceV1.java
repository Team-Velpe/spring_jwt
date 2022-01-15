package com.velpe.jwtAuth.member.application;

import com.velpe.jwtAuth.member.dao.MemberRepository;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberSaveForm;
import com.velpe.jwtAuth.member.dto.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceV1 implements MemberService {

    private final MemberRepository memberRepository;


    @Override
    public void save(MemberSaveForm memberSaveForm) {


    }

    @Override
    public void checkDuplicate(String loginId, String loginPw, String email) {
        // TODO :
    }

    @Override
    @Deprecated
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByLoginId(username).get();
    }

    @Override
    public Member getMemberByLoginId(String loginId) throws NoSuchElementException {

        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);

        memberOptional.orElseThrow(
                () -> new NoSuchElementException("해당 회원은 존재하지 않습니다.")
        );

        return memberOptional.get();

    }

}
