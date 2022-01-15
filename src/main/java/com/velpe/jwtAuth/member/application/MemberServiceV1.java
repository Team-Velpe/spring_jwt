package com.velpe.jwtAuth.member.application;

import com.velpe.jwtAuth.member.dao.MemberRepository;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceV1 implements MemberService{

    private final MemberRepository memberRepository;


    @Override
    public void save(MemberSaveForm memberSaveForm) {

        Member member = new Member();

        memberRepository.save(member);

    }

    @Override
    public void checkDuplicate(String loginId, String loginPw, String email) {



    }

}
