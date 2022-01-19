package com.velpe.jwtAuth.memberTest;

import com.velpe.jwtAuth.member.application.MemberService;
import com.velpe.jwtAuth.member.application.MemberServiceV1;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberInfoDto;
import com.velpe.jwtAuth.member.dto.MemberModifyForm;
import com.velpe.jwtAuth.member.dto.MemberSaveForm;
import com.velpe.jwtAuth.member.dto.Role;
import io.jsonwebtoken.lang.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void checkBean(){
        assertThat(memberService).isNotNull();
    }

    @Test
    @Transactional
    public void saveTest() throws Exception {

        MemberSaveForm memberSaveForm = new MemberSaveForm("test", "1", "김철수", "nick", "testm@test.com");

        memberService.save(memberSaveForm);

        Member member = memberService.findByLoginId(memberSaveForm.getLoginId());

        MemberInfoDto memberInfoDto = new MemberInfoDto(member);

        assertThat(memberSaveForm.getLoginId()).isEqualTo(memberInfoDto.getLoginId());
    }

    @Test
    @Transactional
    public void modifyTest() throws Exception {
        MemberSaveForm memberSaveForm = new MemberSaveForm("test", "1", "김철수", "nick", "testm@test.com");

        memberService.save(memberSaveForm);

        Member member = memberService.findByLoginId(memberSaveForm.getLoginId());

        String mNick = "modifiedNick";

        MemberModifyForm memberModifyForm = new MemberModifyForm(mNick, memberSaveForm.getLoginId());
        memberService.modifyInfo(memberModifyForm);

        assertThat(member.getNickname()).isEqualTo(mNick);
    }

    @Test
    @Transactional
    public void deleteTest() throws Exception {
        MemberSaveForm memberSaveForm = new MemberSaveForm("test", "1", "김철수", "nick", "testm@test.com");

        memberService.save(memberSaveForm);

        Member member = memberService.findByLoginId(memberSaveForm.getLoginId());

        memberService.delete(member.getLoginId());

        assertThat(memberService.findAll()).isEmpty();
    }

    @Test
    @Transactional
    public void saveExceptionTest() throws Exception {
        MemberSaveForm memberSaveForm = new MemberSaveForm("test", "1", "김철수", "nick", "testm@test.com");

        memberService.save(memberSaveForm);

        MemberSaveForm memberSaveForm2 = new MemberSaveForm("test", "1", "김철수", "nick", "testm@test.com");

        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, ()->{
            memberService.save(memberSaveForm2);
        });

    }

    @Test
    @Transactional
    public void getMemberListTest() throws Exception {
        MemberSaveForm memberSaveForm = new MemberSaveForm("test", "1", "김철수", "nick", "testm@test.com");
        memberService.save(memberSaveForm);

        assertThat(memberService.findAll().get(0).getLoginId()).isEqualTo(memberSaveForm.getLoginId());
    }

    @Test
    @Transactional
    public void memberListRequestTest() throws Exception {
        MemberSaveForm memberSaveForm = new MemberSaveForm("test", "1", "김철수", "nick", "testm@test.com");
        memberService.save(memberSaveForm);

        assertThat(memberService.findAll().size()).isEqualTo(1);
    }
}
