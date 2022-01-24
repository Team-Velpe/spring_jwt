package com.velpe.jwtAuth.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CreateQuestionRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String body;

    @NotBlank
    private String loginId;


}
