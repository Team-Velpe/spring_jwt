import React from "react";
import { Link } from "react-router-dom";

import { ReactComponent as Logo } from "../images/logo.svg";

const NotFound = () => {
  return (
    <div className="notfound">
      <Link to="/" className="notfound-logo">
        <Logo width="100" height="auto" fill="#ff6600" />
      </Link>
      <p className="notfound-title">페이지를 찾을 수 없습니다.</p>
      <Link to="/" className="notfound-link">
        메인페이지로 이동
      </Link>
    </div>
  );
};

export default NotFound;
