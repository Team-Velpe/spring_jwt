import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { ReactComponent as Logo } from "../images/logo.svg";

import "../styles/member/member.scss";

import { useRecoilState } from "recoil";
import { loginState } from "../utils/State";
import axiosI from "../utils/AxiosI";
import { setMaxListeners } from "process";



const IsLoginedHeader = () => {

  const [ isLogin, setIsLogin ] = useRecoilState(loginState);

  function doLogout() {

    const accessToken = window.localStorage.getItem('accessToken');
    const refreshToken = window.localStorage.getItem('refreshToken');
  
    if ( accessToken !== null ) {
      window.localStorage.removeItem('accessToken');
    }
  
    if ( refreshToken !== null ) {
      window.localStorage.removeItem('refreshToken');
    }
  
    setIsLogin(false);

  }


  return (
    <header className="header">
      <nav className="header-nav">
        <p className="header-logo">
          <Link to="/">
            <Logo width="60" height="auto" fill="#ff4800" />
          </Link>
        </p>

        <ul>
          <li>
            <Link to="/">QNA</Link>
          </li>
        </ul>
      </nav>

      <nav className="header-btn">
        <ul>
          <li>
            <Link to="/write">질문하기</Link>
          </li>
          <li>
            <Link to="/modify">회원정보 수정</Link>
          </li>
          <li>
            <Link to="/" onClick={doLogout}>로그아웃</Link>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default IsLoginedHeader;
