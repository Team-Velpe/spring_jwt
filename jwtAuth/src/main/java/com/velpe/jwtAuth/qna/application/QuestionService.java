package com.velpe.jwtAuth.qna.application;

import com.velpe.jwtAuth.qna.domain.Question;
import com.velpe.jwtAuth.qna.dto.QuestionDTO;
import com.velpe.jwtAuth.qna.dto.UpdateQuestionRequest;

import java.util.List;

public interface QuestionService {

    Long save(Question question);

    void remove(Long id);

    QuestionDTO update(Long id, UpdateQuestionRequest updateQuestionRequest);

    QuestionDTO detail(Long id);

    List<QuestionDTO> getList();

    Question getOne(Long id);

}
