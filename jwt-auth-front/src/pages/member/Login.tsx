import React from "react";
import { Link } from "react-router-dom";

import "../../styles/member.scss";

const Login = () => {
  return (
    <div className="container">
      <div className="form">
        <p className="form-title">로그인 👋</p>
        <div className="form-input">
          <input type="input" placeholder="아이디" style={{ marginBottom: "0.4rem" }} />
          <input type="input" placeholder="비밀번호" />
          <button type="submit" className="form-btn">
            로그인
          </button>
          <Link to="/join" className="form-join">
            회원가입
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Login;
