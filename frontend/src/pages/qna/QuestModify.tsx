import React from "react";
import { useNavigate } from "react-router-dom";

import Input from "../../components/member/Input";
import "../../styles/qna/upload.scss";
import Textarea from "../../components/qna/Textarea";
import Button from "../../components/member/Button";

import axiosI from "../../utils/AxiosI";

const QuestModify = () => {
  // const navigate = useNavigate();
  let pathUrl = window.location.pathname;
  let path = pathUrl.split("/");
  let detailNumber = path[path.length - 1];

  const token: any = window.localStorage.getItem("accessToken");
  const base64Payload = token.split(".")[1];
  const result = JSON.parse(atob(base64Payload));

  async function modify(e: any) {
    e.preventDefault();

    const { title, body } = e.target;

    const data = {
      title: title.value,
      body: body.value,
      loginId: result.sub,
    };

    await axiosI
      .put("http://localhost:8083/api/v1/qna/q/" + detailNumber, data)
      .then((response) => {
        console.log(response);
        window.location.replace("/qna/q/" + detailNumber);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <form className="upload" onSubmit={modify}>
      <p className="upload-title">QNA 작성 📝</p>
      <Input name="title" placeholder="제목을 입력해 주세요" />
      <Textarea name="body" placeholder="내용을 입력해 주세요" rows={20} />
      <Button name="수정하기" backgroundColor="" />
    </form>
  );
};

export default QuestModify;
