package com.velpe.jwtAuth.memberQnaTest;

import com.velpe.jwtAuth.member.application.MemberServiceV1;
import com.velpe.jwtAuth.qna.application.AnswerServiceImpl;
import com.velpe.jwtAuth.qna.application.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberQnaTest {

    @Autowired
    private MemberServiceV1 memberService;

    @Autowired
    private AnswerServiceImpl answerService;

    @Autowired
    private QuestionServiceImpl questionService;

    public void saveTest(){

    }

}
