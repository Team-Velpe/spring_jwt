import React from "react";
import { Link } from "react-router-dom";

import "../../scss/member.scss";
import { ReactComponent as Logo } from "../../utils/images/logo.svg";

const Join = () => {
  return (
    <div className="container">
      <div className="form">
        <p className="form-title">회원가입 🙌</p>
        <div className="form-input">
          <div className="form-input-name">
            <p>
              아이디 <span>*</span>
            </p>
            <input type="input" placeholder="아이디를 입력해 주세요" />
          </div>
          <div className="form-input-name">
            <p>
              비밀번호 <span>*</span>
            </p>
            <input type="input" placeholder="비밀번호를 입력해 주세요" />
          </div>
          <div className="form-input-name">
            <p>
              비밀번호 확인 <span>*</span>
            </p>
            <input type="input" placeholder="비밀번호를 확인해 주세요" />
          </div>
          <div className="form-input-name">
            <p>
              이메일 <span>*</span>
            </p>
            <input type="input" placeholder="이메일을 입력해 주세요" />
          </div>
          <button type="submit" className="form-btn">
            회원가입
          </button>
          <Link to="/login" className="form-join">
            로그인
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Join;
