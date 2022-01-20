import React from "react";

import "../../styles/qna/qnaList.scss";

import { Link } from "react-router-dom";

interface QnaListProps {
  question: any;
}

const QnaList: React.FC<QnaListProps> = ({ question }) => {
  return (
    <ul className="qna-list">
      <li className="qna-cont">
        <ul className="qna-icon">Q.</ul>
        <ul className="qna-info">
          <Link to="/notfound">
            <li className="qna-question">{question.title}</li>
          </Link>
          <li className="qna-date">{question.reg_date}</li>
        </ul>
      </li>
      <li className="qna-name">
        <p>{question.nickname}</p>님
      </li>
    </ul>
  );
};

export default QnaList;
