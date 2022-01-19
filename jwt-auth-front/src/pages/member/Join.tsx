import axios from "axios";
import React from "react";
import { Link } from "react-router-dom";

import InputDiv from "../../components/member/InputDiv";
import Button from "../../components/member/Button";
import "../../styles/member/member.scss";

const Join = () => {
  async function join(e: any) {
    const { loginId, loginPw, name, nickname, email } = e.target;

    const data = {
      loginId: loginId.value,
      loginPw: loginPw.value,
      name: name.value,
      nickname: nickname.value,
      email: email.value,
    };

    const result = await axios
      .post("http://localhost:8083/api/v1/sign/up", data)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });

    console.log(result);

    return;
  }

  return (
    <div className="container" style={{ margin: "100px 0 100px 0" }}>
      <form onSubmit={join} className="form">
        <div className="form-div">
          <p className="form-title">회원가입 🙌</p>
          <div className="form-input">
            <InputDiv info="아이디" name="loginId" placeholder="아이디를 입력해 주세요" />
            <InputDiv info="비밀번호" name="loginPw" placeholder="비밀번호를 입력해 주세요" />
            <InputDiv info="이름" name="name" placeholder="이름을 입력해 주세요" />
            <InputDiv info="닉네임" name="nickname" placeholder="닉네임을 입력해 주세요" />
            <InputDiv info="이메일" name="email" placeholder="이메일을 입력해 주세요" />
            <Button name="회원가입" backgroundColor="#ff4800" />
            <Link to="/login" className="form-join">
              로그인
            </Link>
          </div>
        </div>
      </form>
    </div>
  );
};

export default Join;
