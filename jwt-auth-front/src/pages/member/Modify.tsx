import React from "react";
import { Link } from "react-router-dom";

import InputDiv from "../../components/member/InputDiv";
import Button from "../../components/member/Button";
import "../../styles/member/member.scss";

import { useRecoilState } from "recoil";

import { loginState } from "../../utils/State";
import axiosI from "../../utils/AxiosI";

const Modify = () => {
  let pathUrl = window.location.pathname;
  let path = pathUrl.split("/");
  let detailNumber = path[path.length - 1];

  const token: any = window.localStorage.getItem("accessToken");
  const base64Payload = token.split(".")[1];
  const result = JSON.parse(atob(base64Payload));

  async function modify(e: any) {
    e.preventDefault();

    const { title, body } = e.target;

    const data = {
      title: title.value,
      body: body.value,
      loginId: result.sub,
    };

    await axiosI
      .put("http://localhost:8083/api/v1/qna/q/" + detailNumber, data)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  }

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
