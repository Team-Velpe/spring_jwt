import React from "react";

import Textarea from "./Textarea";
import Button from "../member/Button";
import axiosI from "../../utils/AxiosI";

async function modifyAnswer(e: any) {
  let pathUrl = window.location.pathname;
  let path = pathUrl.split("/");
  let detailNumber = path[path.length - 1];

  const token: any = window.localStorage.getItem("accessToken");
  const base64Payload = token.split(".")[1];
  const result = JSON.parse(atob(base64Payload));

  e.preventDefault();

  const { body } = e.target;

  const data = {
    body: body.value,
    loginId: result.sub,
  };

  await axiosI
    .put("http://localhost:8083/api/v1/qna/a/" + detailNumber, data)
    .then((response) => {
      console.log(response);

      if (response.status === 200) {
        window.location.replace("/");
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

export default function AnswerModifyInput() {
  return (
    <form className="comment" onSubmit={modifyAnswer}>
      <Textarea name="body" placeholder="답변을 작성해 주세요" rows={6} />
      <Button name="답변 수정" backgroundColor="#ff4800" />
    </form>
  );
}
