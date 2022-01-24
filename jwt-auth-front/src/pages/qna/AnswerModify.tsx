import React from "react";
import { useNavigate } from "react-router-dom";

import AnswerModifyInput from "../../components/qna/AnswerModifyInput";
import "../../styles/qna/answer.scss";

const AnswerModify = () => {
  return (
    <div className="answer-container">
      <AnswerModifyInput />
    </div>
  );
};

export default AnswerModify;
