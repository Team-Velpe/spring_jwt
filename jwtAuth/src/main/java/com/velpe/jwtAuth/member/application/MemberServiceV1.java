package com.velpe.jwtAuth.member.application;

import com.velpe.jwtAuth.member.dao.MemberRepository;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberSaveForm;
import com.velpe.jwtAuth.member.dto.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceV1 implements MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    @Override
    public void save(MemberSaveForm memberSaveForm) throws Exception {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        isAvailableMember(memberSaveForm);

        Member member = Member.createMember(
                memberSaveForm.getLoginId(),
                passwordEncoder.encode(memberSaveForm.getLoginPw()),
                memberSaveForm.getName(),
                memberSaveForm.getNickname(),
                Role.MEMBER,
                memberSaveForm.getEmail()
        );

        memberRepository.save(member);
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

    private void isAvailableMember(MemberSaveForm memberSaveForm) throws Exception {
        if(isMemberExistByLoginId(memberSaveForm.getLoginId())){
            throw new Exception("이미 사용 중인 아이디입니다.");
        }

        if(isMemberExistByEmail(memberSaveForm.getEmail())){
            throw new Exception("이미 사용 중인 이메일입니다.");
        }

        if(isMemberExistByNickname(memberSaveForm.getNickname())){
            throw new Exception("이미 사용 중인 닉네임입니다.");
        }
    }

    private boolean isMemberExistByLoginId(String loginId){
        if(memberRepository.findByLoginId(loginId).isEmpty()){
            return false;
        }
        return true;
    }

    private boolean isMemberExistByEmail(String email) {
        if(memberRepository.findByEmail(email).isEmpty()){
            return false;
        }
        return true;
    }

    private boolean isMemberExistByNickname(String nickname) {
        if(memberRepository.findByNickname(nickname).isEmpty()){
            return false;
        }
        return true;
    }

}
