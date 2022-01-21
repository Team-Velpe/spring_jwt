import React from "react";

import "../../styles/qna/question.scss";
import Answer from "./Answer";

export default function Question(props:any) {

  const { question } = props;

  console.log(question);

  return (
    <>
      <div className="quest">
        <div className="quest-info">
          <div className="quest-icon">Q.</div>
          <div>
            <div className="quest-writer"><strong>{question.nickname}</strong>님</div>
            <div className="quest-title">{question.title}</div>
            <div className="quest-date">{question.reg_date}</div>
          </div>
        </div>
        <hr />
        <div className="quest-cont">{question.body}</div>
      </div>
      {question.answers.length === 0 && <div></div>}
      {question.answers.map((a: any) => {
        return <Answer writer={a.nickname} date={a.a_id} content={a.body} />;
      })}
    </>
  );
}
