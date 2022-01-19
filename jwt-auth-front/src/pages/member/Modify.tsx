import React from "react";
import { Link } from "react-router-dom";

import InputDiv from "../../components/member/InputDiv";
import Button from "../../components/member/Button";
import "../../styles/member/member.scss";

import { useRecoilState } from "recoil";

import { loginState } from "../../utils/State";
import axiosI from "../../utils/AxiosI";

const Modify = () => {
  return (
    <div className="modify">
      <form className="form">
        <div className="form-div">
          <p className="form-title">회원정보 수정 👍</p>
          <div className="form-input">
            <InputDiv info="닉네임" name="nickname" placeholder="변경할 닉네임을 입력해 주세요" />
            <Button name="회원정보 수정" backgroundColor="#ff4800" />
            <Button name="회원 탈퇴" backgroundColor="#7e7d82" />
            <Link to="/join" className="form-join">
              회원가입
            </Link>
          </div>
        </div>
      </form>
    </div>
  );
};

export default Modify;
