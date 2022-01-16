package com.velpe.jwtAuth.qna.application;

import com.velpe.jwtAuth.qna.domain.Question;
import com.velpe.jwtAuth.qna.dto.QuestionDTO;

public interface QuestionService {

    Long save(Question question);

    void remove(Long id);

    QuestionDTO update();

}
