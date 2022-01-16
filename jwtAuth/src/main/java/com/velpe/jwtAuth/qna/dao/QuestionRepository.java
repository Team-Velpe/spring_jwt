package com.velpe.jwtAuth.qna.dao;

import com.velpe.jwtAuth.qna.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface QuestionRepository extends JpaRepository<Question, Long> {


    @Query("select q from Question q join fetch q.member m ")
    Question findQuestionWithMember();


}
