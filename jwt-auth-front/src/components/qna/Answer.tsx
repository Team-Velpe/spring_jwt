import React, { useEffect } from "react";

import { useNavigate } from "react-router-dom";
import "../../styles/qna/answer.scss";
import axiosI from "../../utils/AxiosI";
import Button from "../member/Button";
import AnswerInput from "./AnswerInput";

export default function Answer(props: any) {
  const { answer } = props;

  const token: any = window.localStorage.getItem("accessToken");
  const base64Payload = token.split(".")[1];
  const result = JSON.parse(atob(base64Payload));

  function modifyA() {
    window.location.replace("http://localhost:3000/qna/a/modify/" + answer.a_id);
  }

  async function deleteA() {
    await axiosI
      .delete("http://localhost:8083/api/v1/qna/a/" + answer.a_id)
      .then((response) => {
        console.log(response);
        window.location.reload();
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <div className="answer">
      <div className="answer-info">
        <div className="answer-icon">A.</div>
        <div>
          <div className="answer-writer">
            <strong>{answer.nickname}</strong>님
          </div>
          <div className="answer-date">{answer.a_id}</div>
        </div>
        {result.sub !== answer.nickname ? null : (
          <div className="answer-btn">
            <button className="answer-modify" onClick={modifyA}>
              수정
            </button>
            <button className="answer-modify" onClick={deleteA}>
              삭제
            </button>
          </div>
        )}
      </div>
      <hr />
      <div className="answer-cont">{answer.body}</div>
    </div>
  );
}
