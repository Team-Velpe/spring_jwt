package com.velpe.jwtAuth.qna.dto;

import com.velpe.jwtAuth.qna.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class QuestionDTO {

    private Long q_id;

    private String title;
    private String body;

    private String nickname;

    private LocalDateTime reg_date;
    private LocalDateTime update_date;

    public QuestionDTO(Question question) {

        q_id = question.getId();

        title = question.getTitle();
        body = question.getBody();

        nickname = question.getMember().getNickname();

        reg_date = question.getCreatedDate();
        update_date = question.getModifiedDate();

    }


}
