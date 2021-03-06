package com.velpe.jwtAuth.qna.application;

import com.velpe.jwtAuth.qna.dao.QuestionRepository;
import com.velpe.jwtAuth.qna.domain.Question;
import com.velpe.jwtAuth.qna.dto.AnswerDTO;
import com.velpe.jwtAuth.qna.dto.QuestionDTO;
import com.velpe.jwtAuth.qna.dto.QuestionDetailDTO;
import com.velpe.jwtAuth.qna.dto.UpdateQuestionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Transactional
    public void remove(Long id) throws NoSuchElementException {
        questionRepository.delete(getOne(id));
    }

    @Override
    public QuestionDetailDTO detail(Long id) throws NoSuchElementException {

        Question findQuestion = getOne(id);
        List<AnswerDTO> answers = findQuestion.getAnswers()
                .stream()
                .map(AnswerDTO::new)
                .collect(Collectors.toList());

        return new QuestionDetailDTO(findQuestion, answers);
    }

    @Override
    @Transactional
    public QuestionDTO update(Long id, UpdateQuestionRequest updateQuestionRequest) throws NoSuchElementException {

        Question findQuestion = getOne(id);

        findQuestion.changeInfo(
                updateQuestionRequest.getTitle(),
                updateQuestionRequest.getBody()
        );

        return new QuestionDTO(findQuestion);
    }

    @Override
    public Question getOne(Long id) throws NoSuchElementException {

        Optional<Question> questionOptional = questionRepository.findById(id);

        questionOptional.orElseThrow(
                () -> new NoSuchElementException("?????? ????????? ???????????? ????????????")
        );

        return questionOptional.get();
    }

    @Override
    public List<QuestionDTO> getList() {
        return questionRepository.findAllWithMember()
                .stream()
                .map(QuestionDTO::new)
                .collect(Collectors.toList());

    }

}
