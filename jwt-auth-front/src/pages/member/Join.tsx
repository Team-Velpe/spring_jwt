import axios from "axios";
import React from "react";
import { Link } from "react-router-dom";

import "../../styles/member.scss";

const Join = () => {
  async function join(e: any) {
    const { loginId, loginPw, email, nickname, name } = e.target;

    const data = {
      loginId: loginId.value,
      loginPw: loginPw.value,
      email: email.value,
      nickname: nickname.value,
      name: name.value,
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
    <div className="container">
      <form onSubmit={join} className="form">
        <p className="form-title">회원가입 🙌</p>
        <div className="form-input">
          <div className="form-input-name">
            <p>
              아이디 <span>*</span>
            </p>
            <input type="input" name="loginId" placeholder="아이디를 입력해 주세요" />
          </div>
          <div className="form-input-name">
            <p>
              비밀번호 <span>*</span>
            </p>
            <input type="input" name="loginPw" placeholder="비밀번호를 입력해 주세요" />
          </div>
          {/* <div className="form-input-name">
            <p>
              비밀번호 확인 <span>*</span>
            </p>
            <input type="input" name="loginId" placeholder="비밀번호를 확인해 주세요" />
          </div> */}
          <div className="form-input-name">
            <p>
              이메일 <span>*</span>
            </p>
            <input type="input" name="email" placeholder="이메일을 입력해 주세요" />
          </div>
          <div className="form-input-name">
            <p>
              닉네임 <span>*</span>
            </p>
            <input type="input" name="nickname" placeholder="닉네임을 입력해 주세요" />
          </div>
          <div className="form-input-name">
            <p>
              이름 <span>*</span>
            </p>
            <input type="input" name="name" placeholder="이름을 입력해 주세요" />
          </div>
          <button type="submit" className="form-btn">
            회원가입
          </button>
          <Link to="/login" className="form-join">
            로그인
          </Link>
        </div>
      </form>
    </div>
  );
};

export default Join;
