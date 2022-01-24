package com.velpe.jwtAuth.global.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse <T> {

    T msg;

}
