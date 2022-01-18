import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import {
  RecoilRoot
} from 'recoil';
// import logger from "redux-logger";

ReactDOM.render(
  <RecoilRoot>
      <App />
  </RecoilRoot>,
  document.getElementById("root")
);
