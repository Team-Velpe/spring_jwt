import React from "react";
import { Link } from "react-router-dom";

import Input from "../../components/member/Input";
import Button from "../../components/member/Button";
import "../../styles/member/member.scss";

const Login = () => {
  return (
    <div className="container">
      <form className="form">
        <p className="form-title">로그인 👋</p>
        <div className="form-input">
          <Input name="loginId" placeholder="아이디" />
          <Input name="loginPw" placeholder="비밀번호" />
          <Button name="로그인" backgroundColor="#ff4800" />
          <Link to="/join" className="form-join">
            회원가입
          </Link>
        </div>
      </form>
    </div>
  );
};

export default Login;
