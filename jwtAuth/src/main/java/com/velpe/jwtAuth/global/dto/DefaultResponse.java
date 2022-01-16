package com.velpe.jwtAuth.global.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public  class DefaultResponse <T> {

    T data;

}
