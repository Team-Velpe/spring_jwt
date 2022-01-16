package com.velpe.jwtAuth.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateQuestionRequest {

    private String title;
    private String body;

    private String loginId;


}
