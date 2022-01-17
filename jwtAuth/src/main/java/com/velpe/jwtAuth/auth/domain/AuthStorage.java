package com.velpe.jwtAuth.auth.domain;

import com.velpe.jwtAuth.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthStorage {

    @Id
    @Column(name = "auth_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "access_token")
//    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "reg_date")
    private LocalDateTime regDate = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate = LocalDateTime.now();

    public static AuthStorage createAuthStorage(String refreshToken, Member member) {

        AuthStorage authStorage = new AuthStorage();

//        authStorage.accessToken = accessToken;
        authStorage.refreshToken = refreshToken;

        authStorage.member = member;

        return authStorage;

    }

}
