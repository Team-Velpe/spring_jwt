import React from "react";
import { Link } from "react-router-dom";
import { ReactComponent as Logo } from "../utils/images/logo.svg";

const Header = () => {
  return (
    <header className="header">
      <nav className="header-nav">
        <p className="header-logo">
          <Link to="/">
            <Logo width="60" height="auto" fill="#ff6600" />
          </Link>
        </p>

        <ul>
          <li>
            <Link to="/qna">QNA</Link>
          </li>
        </ul>
      </nav>

      <nav className="header-btn">
        <ul>
          <li>
            <Link to="/join">회원가입</Link>
          </li>
          <li>
            <Link to="/login">로그인</Link>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
