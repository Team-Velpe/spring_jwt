package com.velpe.jwtAuth.qna.domain;

import com.velpe.jwtAuth.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id
    @GeneratedValue
    @Column(name = "q_id")
    private Long id;

    private String title;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
    private List<Answer> answers = new ArrayList<>();

    @Column(name = "reg_date")
    private LocalDateTime regDate = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate = LocalDateTime.now();
    
    
    // 연관관계 메소드
    public void setMember(Member member) {



    }

    public void setAnswers(Answer answers) {



    }

    // 생성메소드
    public static Question createQuestion( String title, String body) {

        Question question = new Question();

        question.title = title;
        question.body = body;

        return question;

    }

}
