package com.velpe.jwtAuth.member.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MemberDeleteForm {

    private String loginId;

    // 필드가 하나 뿐인 객체를 RequestBody로 받을 때 Json parse error가 발생
    // JsonCreator로 생성자 지정을 해줘야 한다.
    @JsonCreator
    public MemberDeleteForm(String loginId){
        this.loginId = loginId;
    }
}
