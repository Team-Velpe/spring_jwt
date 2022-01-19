import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import AppLayout from "./pages/AppLayout";
import { Main, Join, Login, NotFound, Modify, List, Detail, Upload } from "./pages/Pages";

import { useRecoilState, useRecoilValue, useSetRecoilState, useResetRecoilState } from "recoil";

import "./styles/global.scss"; // 전역 css 설정
import { loginState } from "./utils/State";

const App: React.FC = () => {
  const [isLogin, setIsLogin] = useRecoilState(loginState);
  // const currentCount = useRecoilValue(loginState);

  // console.log(currentCount);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<AppLayout />}>
          <Route path="" element={<Main />} />
          <Route path="join" element={<Join />} />
          <Route path="login" element={<Login />} />
          <Route path="modify" element={<Modify />} />
          <Route path="write" element={<Upload />} />
          <Route path="qna" element={<Detail />} />
        </Route>
        <Route path="/*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
