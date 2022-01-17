package com.velpe.jwtAuth.memberTest;

import com.velpe.jwtAuth.member.application.MemberServiceV1;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberInfoDto;
import com.velpe.jwtAuth.member.dto.MemberSaveForm;
import com.velpe.jwtAuth.member.dto.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberServiceV1 memberService;

    @Test
    @Transactional
    public void saveTest() throws Exception {

        MemberSaveForm memberSaveForm = new MemberSaveForm("test", "1", "nick", "testm@test.com");

        memberService.save(memberSaveForm);

        Member member = memberService.findByLoginId(memberSaveForm.getLoginId());

        MemberInfoDto memberInfoDto = new MemberInfoDto(member.getLoginId(), member.getNickname(), member.getEmail());

        assertThat(memberSaveForm.getLoginId()).isEqualTo(memberInfoDto.getLoginId());
    }

    public void modifyTest(){

    }

}
