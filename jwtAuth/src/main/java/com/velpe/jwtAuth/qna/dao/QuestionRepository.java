package com.velpe.jwtAuth.qna.dao;

import com.velpe.jwtAuth.qna.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, Long> {
    
    // FIXME : 동일 쿼리, 개선 필요
    
    @Query("select q from Question q join fetch q.member m ")
    Question findQuestionWithMember();

    @Query("select q from Question q join fetch q.member m ")
    List<Question> findAllWithMember();

}
