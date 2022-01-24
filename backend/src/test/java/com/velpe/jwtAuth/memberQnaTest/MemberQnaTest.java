package com.velpe.jwtAuth.memberQnaTest;

import com.velpe.jwtAuth.member.application.MemberService;
import com.velpe.jwtAuth.member.application.MemberServiceV1;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberSaveForm;
import com.velpe.jwtAuth.qna.application.AnswerService;
import com.velpe.jwtAuth.qna.application.AnswerServiceImpl;
import com.velpe.jwtAuth.qna.application.QuestionService;
import com.velpe.jwtAuth.qna.application.QuestionServiceImpl;
import com.velpe.jwtAuth.qna.domain.Answer;
import com.velpe.jwtAuth.qna.domain.Question;
import com.velpe.jwtAuth.qna.dto.UpdateAnswerRequest;
import com.velpe.jwtAuth.qna.dto.UpdateQuestionRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberQnaTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Test
    @Transactional
    public void questionSaveTest() throws Exception {
        MemberSaveForm memberSaveForm = new MemberSaveForm("test", "1", "kim", "nick", "email@mail.com");
        memberService.save(memberSaveForm);

        Member member = memberService.findByLoginId(memberSaveForm.getLoginId());

        Question question = Question.createQuestion("titleTest", "bodyTest");
        question.setMember(member);
        questionService.save(question);

        assertThat(questionService.getOne(question.getId()).getTitle()).isEqualTo("titleTest");

    }

    @Test
    @Transactional
    public void answerSaveTest() throws Exception {
        MemberSaveForm memberSaveForm = new MemberSaveForm("test", "1", "kim", "nick", "email@mail.com");
        memberService.save(memberSaveForm);

        Member member = memberService.findByLoginId(memberSaveForm.getLoginId());

        Question question = Question.createQuestion("titleTest", "bodyTest");
        question.setMember(member);
        questionService.save(question);


        Answer answer = Answer.createAnswer("answerTest");
        answer.setMember(member);
        answer.setQuestion(question);

        answerService.save(answer);

        assertThat(answerService.getOne(answer.getId()).getBody()).isEqualTo("answerTest");

    }

    @Test
    @Transactional
    public void questionModifyTest() throws Exception {
        MemberSaveForm memberSaveForm = new MemberSaveForm("test", "1", "kim", "nick", "email@mail.com");
        memberService.save(memberSaveForm);

        Member member = memberService.findByLoginId(memberSaveForm.getLoginId());

        Question question = Question.createQuestion("titleTest", "bodyTest");
        question.setMember(member);
        questionService.save(question);

        String mTitle = "modifyTitle";
        String mBody = "modifyBody";

        UpdateQuestionRequest updateQuestionRequest = new UpdateQuestionRequest(mTitle, mBody, member.getLoginId());

        questionService.update(question.getId(), updateQuestionRequest);

        assertThat(question.getTitle()).isEqualTo(mTitle);
        assertThat(question.getBody()).isEqualTo(mBody);

    }

    @Test
    @Transactional
    public void questionDeleteTest() throws Exception {
        MemberSaveForm memberSaveForm = new MemberSaveForm("test", "1", "kim", "nick", "email@mail.com");
        memberService.save(memberSaveForm);

        Member member = memberService.findByLoginId(memberSaveForm.getLoginId());

        Question question = Question.createQuestion("titleTest", "bodyTest");
        question.setMember(member);
        questionService.save(question);

        questionService.remove(question.getId());

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, ()->{
            questionService.getOne(question.getId());
        });
    }

    @Test
    @Transactional
    public void answerDeleteTest() throws Exception {
        MemberSaveForm memberSaveForm = new MemberSaveForm("test", "1", "kim", "nick", "email@mail.com");
        memberService.save(memberSaveForm);

        Member member = memberService.findByLoginId(memberSaveForm.getLoginId());

        Question question = Question.createQuestion("titleTest", "bodyTest");
        question.setMember(member);
        questionService.save(question);


        Answer answer = Answer.createAnswer("answerTest");
        answer.setMember(member);
        answer.setQuestion(question);

        answerService.save(answer);

        answerService.remove(answer.getId());

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, ()->{
            answerService.getOne(answer.getId());
        });
    }

    @Test
    @Transactional
    public void answerModifyTest() throws Exception {
        MemberSaveForm memberSaveForm = new MemberSaveForm("test", "1", "kim", "nick", "email@mail.com");
        memberService.save(memberSaveForm);

        Member member = memberService.findByLoginId(memberSaveForm.getLoginId());

        Question question = Question.createQuestion("titleTest", "bodyTest");
        question.setMember(member);
        questionService.save(question);


        Answer answer = Answer.createAnswer("answerTest");
        answer.setMember(member);
        answer.setQuestion(question);

        answerService.save(answer);

        String mBody = "modifyBody";

        UpdateAnswerRequest updateAnswerRequest = new UpdateAnswerRequest(mBody, member.getLoginId());

        answerService.update(answer.getId(), updateAnswerRequest);

        assertThat(answer.getBody()).isEqualTo(mBody);
    }

}
