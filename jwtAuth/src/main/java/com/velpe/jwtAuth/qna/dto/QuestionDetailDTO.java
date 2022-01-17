package com.velpe.jwtAuth.qna.dto;

import com.velpe.jwtAuth.qna.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDetailDTO {

    private Long q_id;

    private String title;
    private String body;

    private String nickname;

    private LocalDateTime reg_date;
    private LocalDateTime update_date;

    List<AnswerDTO> answers;

    public QuestionDetailDTO(Question question, List<AnswerDTO> answers) {

        q_id = question.getId();

        title = question.getTitle();
        body = question.getBody();

        nickname = question.getMember().getNickname();

        this.answers = answers;

        reg_date = question.getRegDate();
        update_date = question.getUpdateDate();

    }

}
