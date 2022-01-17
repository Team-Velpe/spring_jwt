package com.velpe.jwtAuth.qna.dto;

import com.velpe.jwtAuth.qna.domain.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerDTO {

    private Long a_id;

    private String body;

    private String nickname;

    public AnswerDTO(Answer answer) {
        this.a_id = answer.getId();
        this.body = answer.getBody();
        this.nickname = answer.getMember().getNickname();
    }

}
