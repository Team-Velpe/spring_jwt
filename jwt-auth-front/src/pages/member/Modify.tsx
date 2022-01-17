import React from "react";
import { Link } from "react-router-dom";

import "../../styles/member.scss";

const Modify = () => {
  return (
    <div className="container">
      <div className="form">
        <p className="form-title">회원정보 수정 👍</p>
        <div className="form-input">
          <div className="form-input-name">
            <p>
              닉네임 <span>*</span>
            </p>
            <input type="input" placeholder="닉네임을 변경해 주세요" />
          </div>
          <button type="submit" className="form-btn">
            회원정보 수정
          </button>
          <button type="submit" className="form-btn">
            회원 탈퇴
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
