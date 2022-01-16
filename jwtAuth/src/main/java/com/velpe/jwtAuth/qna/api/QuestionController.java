package com.velpe.jwtAuth.qna.api;

import com.velpe.jwtAuth.global.dto.DefaultResponse;
import com.velpe.jwtAuth.qna.application.QuestionService;
import com.velpe.jwtAuth.qna.domain.Question;
import com.velpe.jwtAuth.qna.dto.CreateQuestionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;


    @PostMapping("/api/v1/qna")
    @ResponseStatus(HttpStatus.CREATED)
    public DefaultResponse writeQuestion(@RequestBody CreateQuestionRequest createQuestionRequest) {

        Question question = Question.createQuestion(
                createQuestionRequest.getTitle(),
                createQuestionRequest.getBody()
        );

        questionService.save(question);

        return new DefaultResponse(
                question.getId()
        );

    }


}
