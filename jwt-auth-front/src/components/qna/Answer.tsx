import React from "react";

import "../../styles/qna/answer.scss";

interface AnswerProps {
  writer: string;
  date: string;
  content: string;
}

const Answer: React.FC<AnswerProps> = ({ writer, date, content }) => {
  return (
    <div className="answer">
      <div className="answer-info">
        <div className="answer-icon">A.</div>
        <div>
          <div className="answer-writer">
            <strong>{writer}</strong>님
          </div>
          <div className="answer-date">{date}</div>
        </div>
      </div>
      <hr />

      <div className="answer-cont">{content}</div>
    </div>
  );
};

export default Answer;
