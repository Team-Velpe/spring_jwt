import React from "react";

import "../../styles/qna/question.scss";

interface QuestionProps {
  writer: string;
  title: string;
  date: string;
  content: string;
}

const Question: React.FC<QuestionProps> = ({ writer, title, date, content }) => {
  return (
    <div className="quest">
      <div className="quest-info">
        <div className="quest-icon">Q.</div>
        <div>
          <div className="quest-writer">
            <strong>{writer}</strong>님
          </div>
          <div className="quest-title">{title}</div>
          <div className="quest-date">{date}</div>
        </div>
      </div>
      <hr />
      <div className="quest-cont">{content}</div>
    </div>
  );
};

export default Question;
