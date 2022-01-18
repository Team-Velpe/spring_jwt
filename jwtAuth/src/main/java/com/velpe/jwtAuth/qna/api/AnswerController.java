package com.velpe.jwtAuth.qna.api;

import com.velpe.jwtAuth.global.dto.DefaultResponse;
import com.velpe.jwtAuth.global.dto.MessageResponse;
import com.velpe.jwtAuth.member.application.MemberService;
import com.velpe.jwtAuth.member.domain.Member;
import com.velpe.jwtAuth.qna.application.AnswerService;
import com.velpe.jwtAuth.qna.application.AnswerServiceImpl;
import com.velpe.jwtAuth.qna.application.QuestionService;
import com.velpe.jwtAuth.qna.domain.Answer;
import com.velpe.jwtAuth.qna.domain.Question;
import com.velpe.jwtAuth.qna.dto.CreateAnswerRequest;
import com.velpe.jwtAuth.qna.dto.UpdateAnswerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final MemberService memberService;

    @PostMapping("/api/v1/qna/a")
    @ResponseStatus(HttpStatus.CREATED)
    public DefaultResponse writeAnswer(@RequestBody @Valid CreateAnswerRequest createAnswerRequest, Principal principal) {

        if (!principal.getName().equals(createAnswerRequest.getLoginId())) {
            throw new IllegalStateException("올바르지 않은 요청입니다.");
        }

        Member currentMember = memberService.getMemberByLoginId(principal.getName());
        Question targetQuestion = questionService.getOne(createAnswerRequest.getQ_id());

        Answer answer = Answer.createAnswer(createAnswerRequest.getBody());

        answer.setQuestion(targetQuestion);
        answer.setMember(currentMember);

        answerService.save(answer);

        return new DefaultResponse(
                answer.getId()
        );

    }

    @PutMapping("/api/v1/qna/a/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse updateAnswer(@PathVariable(name = "id") Long id, @RequestBody @Valid UpdateAnswerRequest updateAnswerRequest, Principal principal) throws IllegalStateException {

        if (!principal.getName().equals(updateAnswerRequest.getLoginId())) {
            throw new IllegalStateException("올바르지 않은 요청입니다.");
        }

        Long update = answerService.update(id, updateAnswerRequest);

        return new MessageResponse(
                "성공적으로 수정되었습니다."
        );

    }

    @DeleteMapping("/api/v1/qna/a/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse deleteAnswer(@PathVariable(name = "id") Long id ) {

        answerService.remove(id);

        return new MessageResponse(
                "성공적으로 삭제되었습니다."
        );
    }


}
