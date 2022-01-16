package com.velpe.jwtAuth.qna.api;

import com.velpe.jwtAuth.global.dto.DefaultResponse;
import com.velpe.jwtAuth.global.dto.MessageResponse;
import com.velpe.jwtAuth.qna.application.QuestionService;
import com.velpe.jwtAuth.qna.domain.Question;
import com.velpe.jwtAuth.qna.dto.CreateQuestionRequest;
import com.velpe.jwtAuth.qna.dto.QuestionDTO;
import com.velpe.jwtAuth.qna.dto.UpdateQuestionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;


    @PostMapping("/api/v1/qna")
    @ResponseStatus(HttpStatus.CREATED)
    public DefaultResponse writeQuestion(@RequestBody @Valid CreateQuestionRequest createQuestionRequest, Principal principal) throws IllegalStateException {

        if (!Objects.equals(principal.getName(), createQuestionRequest.getLoginId())) {
            throw new IllegalStateException("올바르지 않은 요청입니다.");
        }

        Question question = Question.createQuestion(
                createQuestionRequest.getTitle(),
                createQuestionRequest.getBody()
        );

        questionService.save(question);

        return new DefaultResponse(
                question.getId()
        );

    }

    @PutMapping("/api/v1/qna/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DefaultResponse updateQuestion(@PathVariable(name = "id") Long id, @RequestBody @Valid UpdateQuestionRequest updateQuestionRequest, Principal principal) throws IllegalStateException {


        if (!Objects.equals(principal.getName(), updateQuestionRequest.getLoginId())) {
            throw new IllegalStateException("올바르지 않은 요청입니다.");
        }

        QuestionDTO questionDTO = questionService.update(id, updateQuestionRequest);

        return new DefaultResponse(
            questionDTO
        );

    }

    @DeleteMapping("/api/v1/qna/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse deleteQuestion(@PathVariable(name = "id") Long id) {

        questionService.remove(id);

        return new MessageResponse(
                "성공적으로 삭제되었습니다"
        );
    }

    @GetMapping("/api/v1/qna/{id}")
    public DefaultResponse showQuestionDetail(@PathVariable(name = "id") Long id) {

        QuestionDTO questionDTO = questionService.detail(id);

        return new DefaultResponse(
                questionDTO
        );
    }

    @GetMapping("/api/v1/qna")
    public DefaultResponse showQuestionList() {

        Map<String, Object> content = new HashMap<>();
        content.put("content", questionService.getList());

        return new DefaultResponse(
                content
        );
    }


}
