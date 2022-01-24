package com.velpe.jwtAuth.qna.domain;

import com.velpe.jwtAuth.global.util.BaseTimeEntity;
import com.velpe.jwtAuth.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Answer extends BaseTimeEntity {

    @Id
    @Column(name = "a_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q_id")
    private Question question;

    // 연관관계 메소드
    public void setMember(Member member) {

        this.member = member;
//        member.getAnswer()

    }

    public void setQuestion(Question question) {

        this.question = question;
        question.getAnswers().add(this);

    }


    // 생성메소드
    public static Answer createAnswer(String body) {

        Answer answer = new Answer();

        answer.body = body;

        return answer;

    }

    // 수정 메소드
    public void changeInfo(String body) {
        this.body = body;
    }



}
