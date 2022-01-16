package com.velpe.jwtAuth.qna.application;

import com.velpe.jwtAuth.qna.domain.Answer;

public interface AnswerService {

    void save();
    void remove();
    Answer update();


}
