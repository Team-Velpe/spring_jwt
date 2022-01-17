package com.velpe.jwtAuth.qna.application;

import com.velpe.jwtAuth.member.application.MemberServiceV1;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberSaveForm;
import com.velpe.jwtAuth.qna.domain.Answer;
import com.velpe.jwtAuth.qna.domain.Question;
import com.velpe.jwtAuth.qna.dto.UpdateAnswerRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class AnswerServiceImplTest {

    @Autowired AnswerServiceImpl answerService;
    @Autowired MemberServiceV1 memberService;
    @Autowired QuestionServiceImpl questionService;

    @Test
    @DisplayName("답변 생성 테스트")
    @Transactional
    public void addTest() {

        // given
        MemberSaveForm memberSaveForm = new MemberSaveForm(
                "user1",
                "user1",
                "user1",
                "user1"
                );

        try {
            memberService.save(memberSaveForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Member findMember = memberService.getMemberByLoginId("user1");

        Question question = Question.createQuestion(
                "질문1",
                "답변1"
        );

        questionService.save(question);

        Question findQuestion = questionService.getOne(1L);

        Answer answer = Answer.createAnswer(
                "답변1"
        );

        answer.setMember(findMember);
        answer.setQuestion(findQuestion);

        answerService.save(answer);

        // when
        Answer findAnswer = answerService.getOne(1L);

        // then
        assertThat(findAnswer.getBody()).isEqualTo(answer.getBody());

        Assertions.assertThrows(
                NoSuchElementException.class ,
                () -> {
                    answerService.getOne(2L);
                }
        );

    }

    @Test
    @Transactional
    @DisplayName("답변 삭제 테스트")
    public void removeTest() {

        // given
        MemberSaveForm memberSaveForm = new MemberSaveForm(
                "user1",
                "user1",
                "user1",
                "user1"
        );

        try {
            memberService.save(memberSaveForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Member findMember = memberService.getMemberByLoginId("user1");

        Question question = Question.createQuestion(
                "질문1",
                "답변1"
        );

        questionService.save(question);

        Question findQuestion = questionService.getOne(1L);

        Answer answer = Answer.createAnswer(
                "답변1"
        );

        answer.setMember(findMember);
        answer.setQuestion(findQuestion);

        answerService.save(answer);

        // when
        answerService.remove(1L);

        // then
        Assertions.assertThrows(
                NoSuchElementException.class ,
                () -> {
                    answerService.getOne(1L);
                }
        );

    }

    @Test
    @Transactional
    @DisplayName("답변 수정 테스트")
    public void updateTest(){

        // given
        MemberSaveForm memberSaveForm = new MemberSaveForm(
                "user1",
                "user1",
                "user1",
                "user1"
        );

        try {
            memberService.save(memberSaveForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Member findMember = memberService.getMemberByLoginId("user1");

        Question question = Question.createQuestion(
                "질문1",
                "답변1"
        );

        questionService.save(question);

        Question findQuestion = questionService.getOne(1L);

        Answer answer = Answer.createAnswer(
                "답변1"
        );

        answer.setMember(findMember);
        answer.setQuestion(findQuestion);

        answerService.save(answer);

        UpdateAnswerRequest updateAnswerRequest = new UpdateAnswerRequest("수정 답변1", "user1");

        // when
        Long updatedAnswerId = answerService.update(1L, updateAnswerRequest);

        Answer findAnswer = answerService.getOne(1L);

        // then
        assertThat(findAnswer.getBody()).isEqualTo("수정 답변1");


    }

}