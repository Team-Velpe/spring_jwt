package com.velpe.jwtAuth.member.domain;

import com.velpe.jwtAuth.global.util.BaseTimeEntity;
import com.velpe.jwtAuth.member.dto.Role;
import com.velpe.jwtAuth.qna.domain.Answer;
import com.velpe.jwtAuth.qna.domain.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseTimeEntity implements UserDetails {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id")
    private String loginId;
    @Column(name = "login_pw")
    private String loginPw;

    private String name;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role authority;

    private String email;
    private String thumbnail = "/default";

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Answer> answers;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Question> questions;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;


    public static Member createMember(String loginId, String loginPw, String name, String nickname, Role authority, String email) {

        Member member = new Member();

        member.loginId = loginId;
        member.loginPw = loginPw;

        member.name = name;
        member.nickname = nickname;

        member.authority = authority;

        member.email = email;

        return member;

    }

    public void modifyInfo(String nickname){
        this.nickname = nickname;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority( this.authority.getValue() ));

        return authorities;
    }

    @Override
    public String getPassword() {
        return loginPw;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
