package com.velpe.jwtAuth.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDeleteForm {
    private String loginId;
}
