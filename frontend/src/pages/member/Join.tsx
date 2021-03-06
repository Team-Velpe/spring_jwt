import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import InputDiv from "../../components/member/InputDiv";
import Button from "../../components/member/Button";
import "../../styles/member/member.scss";

const Join = () => {
  const navigate = useNavigate();

  async function join(e: any) {
    e.preventDefault();

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
        navigate("/");
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
          <p className="form-title">ํ์๊ฐ์ ๐</p>
          <div className="form-input">
            <InputDiv type="input" info="์์ด๋" name="loginId" placeholder="์์ด๋๋ฅผ ์๋ ฅํด ์ฃผ์ธ์" />
            <InputDiv type="password" info="๋น๋ฐ๋ฒํธ" name="loginPw" placeholder="๋น๋ฐ๋ฒํธ๋ฅผ ์๋ ฅํด ์ฃผ์ธ์" />
            <InputDiv type="input" info="์ด๋ฆ" name="name" placeholder="์ด๋ฆ์ ์๋ ฅํด ์ฃผ์ธ์" />
            <InputDiv type="input" info="๋๋ค์" name="nickname" placeholder="๋๋ค์์ ์๋ ฅํด ์ฃผ์ธ์" />
            <InputDiv type="input" info="์ด๋ฉ์ผ" name="email" placeholder="์ด๋ฉ์ผ์ ์๋ ฅํด ์ฃผ์ธ์" />
            <Button name="ํ์๊ฐ์" backgroundColor="#ff4800" />
            <Link to="/login" className="form-join">
              ๋ก๊ทธ์ธ
            </Link>
          </div>
        </div>
      </form>
    </div>
  );
};

export default Join;
