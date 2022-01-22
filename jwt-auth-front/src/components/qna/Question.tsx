import React from "react";

import { useNavigate } from "react-router-dom";
import "../../styles/qna/question.scss";
import axiosI from "../../utils/AxiosI";
import Button from "../member/Button";
import Answer from "./Answer";

export default function Question(props: any) {
  const { question } = props;
  const navigate = useNavigate();

  let pathUrl = window.location.pathname;
  let path = pathUrl.split("/");
  let detailNumber = path[path.length - 1];

  const token: any = window.localStorage.getItem("accessToken");
  const base64Payload = token.split(".")[1];
  const result = JSON.parse(atob(base64Payload));

  function modifyQ() {
    window.location.replace("/qna/q/modify/" + detailNumber);
  }

  async function deleteQ() {
    await axiosI
      .delete("http://localhost:8083/api/v1/qna/q/" + detailNumber)
      .then((response) => {
        console.log(response);
        navigate("/");
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <>
      <div className="quest">
        <div className="quest-info">
          <div className="quest-icon">Q.</div>
          <div>
            <div className="quest-writer">
              <strong>{question.nickname}님</strong>
            </div>
            <div className="quest-title">{question.title}</div>
            <div className="quest-date">{question.reg_date}</div>
          </div>
          {result.sub !== question.nickname ? null : (
            <div className="quest-btn">
              <button className="quest-modify" onClick={modifyQ}>
                수정
              </button>
              <button className="quest-delete" onClick={deleteQ}>
                삭제
              </button>
            </div>
          )}
        </div>
        <hr />
        <div className="quest-cont">{question.body}</div>
      </div>
      {question.answers.length === 0 && <div>아직 답변이 없습니다.</div>}
      {question.answers.map((a: any) => {
        return <Answer answer={a} />;
      })}
    </>
  );
}
