package com.velpe.jwtAuth.qna.application;

import com.velpe.jwtAuth.member.application.MemberService;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.member.dto.MemberSaveForm;
import com.velpe.jwtAuth.qna.domain.Answer;
import com.velpe.jwtAuth.qna.domain.Question;
import com.velpe.jwtAuth.qna.dto.CreateQuestionRequest;
import com.velpe.jwtAuth.qna.dto.QuestionDTO;
import com.velpe.jwtAuth.qna.dto.QuestionDetailDTO;
import com.velpe.jwtAuth.qna.dto.UpdateQuestionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class QuestionServiceImplTest {

    @Autowired QuestionServiceImpl questionService;
    @Autowired MemberService memberService;
    @Autowired AnswerServiceImpl answerService;

    @Test
    @Transactional
    @DisplayName("질문 작성 테스트")
    public void saveTest() {

        //given
        CreateQuestionRequest createQuestionRequest = new CreateQuestionRequest(
                "질문1",
                "질문내용1",
                "member"
        );

        Question question = Question.createQuestion(
                createQuestionRequest.getTitle(),
                createQuestionRequest.getBody()
        );

        //when
        questionService.save(question);

        Question findQuestion = questionService.getOne(1L);

        //then
        assertThat(question.getTitle()).isEqualTo(findQuestion.getTitle());

    }

    @Test
    @Transactional
    @DisplayName("질문 수정 테스트")
    public void updateTest() {
        //given
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

        CreateQuestionRequest createQuestionRequest = new CreateQuestionRequest(
                "질문1",
                "질문내용1",
                "member"
        );

        Question question = Question.createQuestion(
                createQuestionRequest.getTitle(),
                createQuestionRequest.getBody()
        );
        question.setMember(findMember);

        questionService.save(question);

        UpdateQuestionRequest updateQuestionRequest = new UpdateQuestionRequest(
                "수정1",
                "수정내용1",
                "member"
        );

        //when
        QuestionDTO update = questionService.update(1L, updateQuestionRequest);
        Question findQuestion = questionService.getOne(1L);

        //then
        assertThat(update.getTitle()).isEqualTo(findQuestion.getTitle());
        assertThat(findQuestion.getTitle()).isEqualTo("수정1");

    }

    @Test
    @Transactional
    @DisplayName("질문 삭제 테스트")
    public void deleteTest() {

        //given
        CreateQuestionRequest createQuestionRequest = new CreateQuestionRequest(
                "질문1",
                "질문내용1",
                "member"
        );

        Question question = Question.createQuestion(
                createQuestionRequest.getTitle(),
                createQuestionRequest.getBody()
        );
        questionService.save(question);

        // when
        questionService.remove(1L);

        //then
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> {
                    questionService.getOne(1L);
                }
        );


    }

    @Test
    @Transactional
    @DisplayName("질문 리스트 테스트")
    public void listTest() {

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

        for ( int i=1; i<=5; i++) {

            CreateQuestionRequest createQuestionRequest = new CreateQuestionRequest(
                    "질문" + i,
                    "질문내용" + i,
                    "user1"
            );

            Question question = Question.createQuestion(
                    createQuestionRequest.getTitle(),
                    createQuestionRequest.getBody()
            );

            question.setMember(findMember);

            questionService.save(question);

        }

        //when
        List<QuestionDTO> questionDTOS = questionService.getList();

        //then
        assertThat(questionDTOS.size()).isEqualTo(5);

    }

    @Test
    @Transactional
    @DisplayName("질문 디테일 회원 확인")
    public void checkMemberTest() {

        //given
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

        CreateQuestionRequest createQuestionRequest = new CreateQuestionRequest(
                "질문1",
                "질문내용1",
                "member"
        );

        Question question = Question.createQuestion(
                createQuestionRequest.getTitle(),
                createQuestionRequest.getBody()
        );
        question.setMember(findMember);

        questionService.save(question);

        // when
        Question findQuestion = questionService.getOne(1L);

        // then
        assertThat(findQuestion.getMember().getNickname()).isEqualTo("user1");


    }

    @Test
    @Transactional
    @DisplayName("디테일 테스트")
    public void detailTest() {

        //given
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

        CreateQuestionRequest createQuestionRequest = new CreateQuestionRequest(
                "질문1",
                "질문내용1",
                "member"
        );

        Question question = Question.createQuestion(
                createQuestionRequest.getTitle(),
                createQuestionRequest.getBody()
        );
        question.setMember(findMember);

        questionService.save(question);

        for ( int i=1; i<=5; i++) {

            Answer answer = Answer.createAnswer(
                    "답변" + i
            );
            answer.setMember(findMember);
            answer.setQuestion(question);

            answerService.save(answer);

        }

        // when
//        Question findQuestion1 = questionService.getOne(1L);

        QuestionDetailDTO detail = questionService.detail(1L);

//        System.out.println( detail );

        // then
//        System.out.println(findQuestion1.getAnswers().toString());
//        assertThat(findQuestion1.getAnswers().size()).isEqualTo(5);
//
//        System.out.println(findQuestion1.getAnswers().get(0).getMember().getNickname());

        assertThat(detail.getAnswers().size()).isEqualTo(5);


    }



}