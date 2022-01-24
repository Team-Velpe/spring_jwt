import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import AppLayout from "./pages/AppLayout";
import { Main, Join, Login, NotFound, MemModify, QuestModify, AnswerModify, List, Detail, Upload } from "./pages/Pages";

import { useRecoilState, useRecoilValue, useSetRecoilState, useResetRecoilState } from "recoil";

import "./styles/global.scss"; // 전역 css 설정
import { loginState } from "./utils/State";

const App: React.FC = () => {
  const token = window.localStorage.getItem("refreshToken");

  const [isLogin, setIsLogin] = useRecoilState(loginState);

  if (token !== null) {
    setIsLogin(true);
  } else {
    setIsLogin(false);
  }

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<AppLayout />}>
          <Route path="" element={<Main />} />
          <Route path="join" element={<Join />} />
          <Route path="login" element={<Login />} />
          <Route path="account/modify/*" element={<MemModify />} />
          <Route path="write" element={<Upload />} />
          <Route path="qna/q/*" element={<Detail />} />
          <Route path="qna/q/modify/*" element={<QuestModify />} />
          <Route path="qna/a/modify/*" element={<AnswerModify />} />
        </Route>
        <Route path="/*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
