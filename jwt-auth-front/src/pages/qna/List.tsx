import React, { useEffect, useState } from "react";

import NotFound from "../NotFound";
import QnaList from "../../components/qna/QnaList";
import "../../styles/qna/qna.scss";
import axiosI from "../../utils/AxiosI";
import Question from "../../components/qna/Question";
import { Route, Link } from "react-router-dom";

const List = () => {
  const [qList, setQlist] = useState([]);

  useEffect(() => {
    axiosI
      .get("http://localhost:8083/api/v1/qna/q")
      .then((response) => {
        const { content } = response.data.data;
        console.log(content);
        setQlist(content);
      })
      .catch((error) => {});
  }, []);

  console.log("111", qList);

  return (
    <div className="qna">
      {qList.length === 0 && <NotFound />}

      {qList.map((q: any) => {
        return <QnaList question={q} key={q.id} />;
      })}
    </div>
  );
};

export default List;
