import React from "react";
import { BrowserRouter as Router, Route, Routes, Outlet, useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import { loginState } from "../utils/State";

import Header from "../components/Header";
import IsLoginedHeader from "../components/IsLoginedHeader";
import Footer from "../components/Footer";

const AppLayout = () => {
  const [isLogin, setIsLogin] = useRecoilState(loginState);

  return (
    <div className="layout">
      {isLogin === true ? <IsLoginedHeader /> : <Header />}
      <Outlet />
      <Footer />
    </div>
  );
};

export default AppLayout;
