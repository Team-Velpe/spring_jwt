import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import AppLayout from "./pages/AppLayout";
import { Main, Join, Login, NotFound, Modify, List } from "./pages/Pages";

import "./scss/global.scss"; // 전역 css 설정

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<AppLayout />}>
          <Route path="" element={<List />} />
          <Route path="join" element={<Join />} />
          <Route path="login" element={<Login />} />
          <Route path="modify" element={<Modify />} />
          <Route path="qna" element={<List />} />
        </Route>
        <Route path="/*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
