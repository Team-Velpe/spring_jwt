package com.velpe.jwtAuth.qna.application;

import com.velpe.jwtAuth.qna.dao.AnswerRepository;
import com.velpe.jwtAuth.qna.domain.Answer;
import com.velpe.jwtAuth.qna.dto.UpdateAnswerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public Answer getOne(Long id) throws NoSuchElementException {

        Optional<Answer> answerOptional = answerRepository.findById(id);

        answerOptional.orElseThrow(
                () -> new NoSuchElementException("해당 답변은 존재하지 않습니다.")
        );

        return answerOptional.get();
    }

    @Override
    @Transactional
    public Long save(Answer answer) {
        Answer save = answerRepository.save(answer);
        return save.getId();
    }

    @Override
    @Transactional
    public void remove(Long id) throws NoSuchElementException {
        answerRepository.delete(getOne(id));
    }

    @Override
    @Transactional
    public Long update(Long id, UpdateAnswerRequest updateAnswerRequest) throws NoSuchElementException {

        Answer findAnswer = getOne(id);

        findAnswer.changeInfo(
                updateAnswerRequest.getBody()
        );

        return findAnswer.getId();
    }


}
