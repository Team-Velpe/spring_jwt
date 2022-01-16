import React from "react";
import { Link } from "react-router-dom";

import "../../utils/member.scss";

const Modify = () => {
  return (
    <div className="container">
      <div className="form">
        <p className="form-title">회원정보 수정 👍</p>
        <div className="form-input">
          <input type="input" placeholder="비밀번호 확인" style={{ marginBottom: "0.4rem" }} />
          <input type="input" placeholder="비밀번호 변경" />
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

export default Modify;
