package com.velpe.jwtAuth.qna.application;

import com.velpe.jwtAuth.qna.dao.QuestionRepository;
import com.velpe.jwtAuth.qna.domain.Question;
import com.velpe.jwtAuth.qna.dto.QuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public Long save(Question question) {

        Question save = questionRepository.save(question);

        return save.getId();
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public QuestionDTO update() {
        return null;
    }

}
