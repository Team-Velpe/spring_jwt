package com.velpe.jwtAuth.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CreateAnswerRequest {

    @NotBlank
    private String body;

    @NotBlank
    private String loginId;

    @NotBlank
    private Long q_id;

}
