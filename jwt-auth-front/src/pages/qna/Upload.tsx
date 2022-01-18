import React from "react";
import Input from "../../components/member/Input";

import "../../styles/qna/upload.scss";
import Textarea from "../../components/qna/Textarea";
import Button from "../../components/member/Button";

const Upload = () => {
  return (
    <form className="upload">
      <p className="upload-title">QNA 작성 📝</p>
      <Input name="title" placeholder="제목을 입력해 주세요" />
      <Textarea name="body" placeholder="내용을 입력해 주세요" cols={2} />
      <Button name="작성하기" backgroundColor="" />
    </form>
  );
};

export default Upload;
