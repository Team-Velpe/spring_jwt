import React from "react";

import Textarea from "./Textarea";
import Button from "../member/Button";
import axiosI from "../../utils/AxiosI";

export default function AnswerInput() {
  let pathUrl = window.location.pathname;
  let path = pathUrl.split("/");
  let detailNumber = path[path.length - 1];

  const token: any = window.localStorage.getItem("accessToken");
  const base64Payload = token.split(".")[1];
  const result = JSON.parse(atob(base64Payload));

  async function addAnswer(e: any) {
    e.preventDefault();

    const { body } = e.target;

    const data = {
      body: body.value,
      loginId: result.sub,
      q_id: detailNumber,
    };

    await axiosI
      .post("http://localhost:8083/api/v1/qna/a", data)
      .then((response) => {
        if (response.status === 201) {
          window.location.reload();
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <form className="comment" onSubmit={addAnswer}>
      <Textarea name="body" placeholder="답변을 작성해 주세요" rows={4} />
      <Button name="답변 작성" backgroundColor="#ff4800" />
    </form>
  );
}
