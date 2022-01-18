import React from "react";

import "../styles/components/qnaList.scss";

interface QnaListProps {
  question: string;
  date: string;
  nickname: string;
}

const QnaList: React.FC<QnaListProps> = ({ question, date, nickname }) => {
  return (
    <ul className="qna-list">
      <li className="qna-cont">
        <ul className="qna-title">Q.</ul>
        <ul className="qna-info">
          <li className="qna-question">{question}</li>
          <li className="qna-date">{date}</li>
        </ul>
      </li>
      <li className="qna-name">
        <p>{nickname}</p>님
      </li>
    </ul>
  );
};

export default QnaList;
