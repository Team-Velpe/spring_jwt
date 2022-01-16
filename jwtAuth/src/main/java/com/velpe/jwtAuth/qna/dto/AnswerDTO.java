package com.velpe.jwtAuth.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerDTO {

    private Long a_id;

    private String body;

    private String nickname;

}
