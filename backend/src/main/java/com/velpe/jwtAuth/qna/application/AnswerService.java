package com.velpe.jwtAuth.qna.application;

import com.velpe.jwtAuth.qna.domain.Answer;
import com.velpe.jwtAuth.qna.dto.UpdateAnswerRequest;

public interface AnswerService {

    Long save(Answer answer);

    void remove(Long id);

    Long update(Long id, UpdateAnswerRequest updateAnswerRequest);

    Answer getOne(Long id);

}
