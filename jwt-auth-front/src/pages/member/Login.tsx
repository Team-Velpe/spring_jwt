import React from "react";
import { Link, useNavigate } from "react-router-dom";

import Input from "../../components/member/Input";
import Button from "../../components/member/Button";

import { useRecoilState } from 'recoil';


import "../../styles/member/member.scss";
import { loginState } from "../../utils/State";
import axiosI from "../../utils/AxiosI";

const Login = () => {

  const navigate = useNavigate();

  const [ isLogin, setIsLogin ] = useRecoilState(loginState);

  console.log("지금 ? " , isLogin);

  async function fn(e: any) {

    e.preventDefault();

    const { loginId, loginPw } = e.target;

    const data = {
        loginId : loginId.value,
        loginPw : loginPw.value,
    }

    const result = await axiosI.post("http://localhost:8083/api/v1/sign/in", data)
    .then(

      (response) => {

        console.log(response);

        console.log(response.data.data);

        const { accessToken, refreshToken } = response.data.data;

        window.localStorage.setItem("accessToken", accessToken);
        window.localStorage.setItem("refreshToken", refreshToken);

        setIsLogin(true);

        navigate("/");

      }

    )
    .catch(

      (error) => {

        console.log(error);

      }

    )

    console.log(result);

    

  }

  return (
    <div className="container">
      <form className="form" onSubmit={fn}>
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
