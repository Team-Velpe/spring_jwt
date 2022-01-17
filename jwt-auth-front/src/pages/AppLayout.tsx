import React from "react";
import { BrowserRouter as Router, Route, Routes, Outlet } from "react-router-dom";

import Header from "../components/Header";
import Footer from "../components/Footer";

const AppLayout = () => {
  return (
    <div className="layout">
      <Header />
      <Outlet />
      <Footer />
    </div>
  );
};

export default AppLayout;
