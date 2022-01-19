package com.velpe.jwtAuth.member.application;

import com.velpe.jwtAuth.global.exception.ExceptionMessage;
import com.velpe.jwtAuth.member.dao.MemberRepository;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberInfoDto;
import com.velpe.jwtAuth.member.dto.MemberModifyForm;
import com.velpe.jwtAuth.member.dto.MemberSaveForm;
import com.velpe.jwtAuth.member.dto.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceV1 implements MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    @Override
    public void save(MemberSaveForm memberSaveForm) {

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

    @Transactional
    @Override
    public void modifyInfo(MemberModifyForm memberModifyForm) {



        if(isMemberExistByNickname(memberModifyForm.getNickname())){
            throw new DuplicateKeyException(ExceptionMessage.DuplicatedNickname.getValue());
        }

        Member member = memberRepository.findByLoginId(memberModifyForm.getLoginId())
                .orElseThrow(()->new UsernameNotFoundException(ExceptionMessage.UsernameNotFound.getValue()));

        member.modifyInfo(memberModifyForm.getNickname());
    }

    @Transactional
    @Override
    public void delete(String loginId) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(()->new UsernameNotFoundException(ExceptionMessage.UsernameNotFound.getValue()));

        memberRepository.delete(member);
    }

    @Override
    public Member findByLoginId(String loginId){
        return memberRepository.findByLoginId(loginId)
                .orElseThrow(()->new UsernameNotFoundException(ExceptionMessage.UsernameNotFound.getValue()));
    }

    @Override
    public List<MemberInfoDto> findAll() {
        List<Member> memberList = memberRepository.findAll();

        return memberList.stream()
                .map(MemberInfoDto::new)
                .collect(Collectors.toList());
    }


    @Override
    public void checkDuplicate(String loginId, String loginPw, String email) {
        // TODO :
    }

    @Override
    public Member loadUserByUsername(String username) {
        return memberRepository.findByLoginId(username)
                .orElseThrow(()->new UsernameNotFoundException(ExceptionMessage.UsernameNotFound.getValue()));
    }

    @Override
    public Member getMemberByLoginId(String loginId) {

        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);

        memberOptional.orElseThrow(
                () -> new UsernameNotFoundException(ExceptionMessage.UsernameNotFound.getValue())
        );

        return memberOptional.get();

    }


    private void isAvailableMember(MemberSaveForm memberSaveForm) {
        if(isMemberExistByLoginId(memberSaveForm.getLoginId())){
            throw new DuplicateKeyException(ExceptionMessage.DuplicatedLoginId.getValue());
        }

        if(isMemberExistByEmail(memberSaveForm.getEmail())){
            throw new DuplicateKeyException(ExceptionMessage.DuplicatedEmail.getValue());
        }

        if(isMemberExistByNickname(memberSaveForm.getNickname())){
            throw new DuplicateKeyException(ExceptionMessage.DuplicatedNickname.getValue());
        }
    }

    private boolean isMemberExistByLoginId(String loginId){
        return !memberRepository.findByLoginId(loginId).isEmpty();
    }

    private boolean isMemberExistByEmail(String email) {
        return !memberRepository.findByEmail(email).isEmpty();
    }

    private boolean isMemberExistByNickname(String nickname) {
        return !memberRepository.findByNickname(nickname).isEmpty();
    }

}
