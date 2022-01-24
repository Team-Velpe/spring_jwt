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
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Question extends BaseTimeEntity {

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
    
    
    // 연관관계 메소드
    public void setMember(Member member) {

        // TODO : 회원 도메인 완료후 재작업
        this.member = member;

    }

    // 생성메소드
    public static Question createQuestion( String title, String body) {

        Question question = new Question();

        question.title = title;
        question.body = body;

        return question;

    }

    // 질문 내용 변경
    public void changeInfo( String title, String body ) {

        this.title = title;
        this.body = body;

    }

}
