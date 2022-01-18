import React from "react";

import "../../styles/qna/qna.scss";

import Answer from "../../components/qna/Answer";
import Question from "../../components/qna/Question";
import Textarea from "../../components/qna/Textarea";

const Detail = () => {
  return (
    <div className="qna">
      <Question writer="질문자" title="이건 무슨 오류인가요?" date="2022년 1월 18일 오후 5시" content="content" />
      <Answer writer="답변자1" date="2022년 1월 18일 오후 9시" content="content" />
      <Answer writer="답변자2" date="2022년 1월 18일 오후 9시" content="content" />
      <Textarea name="comment" placeholder="답변을 작성해 주세요" cols={30} />
    </div>
  );
};

export default Detail;
