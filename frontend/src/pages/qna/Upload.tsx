import React from "react";
import { useNavigate } from "react-router-dom";

import Input from "../../components/member/Input";
import "../../styles/qna/upload.scss";
import Textarea from "../../components/qna/Textarea";
import Button from "../../components/member/Button";

import axiosI from "../../utils/AxiosI";

const Upload = () => {
  const navigate = useNavigate();

  const token: any = window.localStorage.getItem("accessToken");

  const base64Payload = token.split(".")[1];
  const result = JSON.parse(atob(base64Payload));

  async function upload(e: any) {
    e.preventDefault();

    const { title, body } = e.target;

    const data = {
      title: title.value,
      body: body.value,
      loginId: result.sub,
    };

    const axios = await axiosI
      .post("http://localhost:8083/api/v1/qna/q", data)
      .then((response) => {
        console.log(response);
        console.log(response.data.data);
        window.location.replace("/qna/q/" + String(response.data.data));
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <form className="upload" onSubmit={upload}>
      <p className="upload-title">QNA 작성 📝</p>
      <Input type="input" name="title" placeholder="제목을 입력해 주세요" />
      <Textarea name="body" placeholder="내용을 입력해 주세요" rows={20} />
      <Button name="작성하기" backgroundColor="" />
    </form>
  );
};

export default Upload;
