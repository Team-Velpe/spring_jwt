import React, { Component, useEffect, useState } from "react";

import "../../styles/qna/qna.scss";

import Question from "../../components/qna/Question";
import Textarea from "../../components/qna/Textarea";
import Button from "../../components/member/Button";
import axiosI from "../../utils/AxiosI";
import AnswerInput from "../../components/qna/AnswerInput";

function useAxios(detailNumber: String) {
  const [questionDetail, setQuestionDetail] = useState();
  const [loading, setLoading] = useState(true);

  async function fetchUrl() {
    const result = await axiosI.get("http://localhost:8083/api/v1/qna/q/" + detailNumber);

    const json = await result.data.data;

    setQuestionDetail(json);
    setLoading(false);
  }

  useEffect(() => {
    fetchUrl();
  }, []);

  return [questionDetail, loading];
}

function Detail() {
  let pathUrl = window.location.pathname;
  let path = pathUrl.split("/");
  let detailNumber = path[path.length - 1];

  const [questionDetail, loading]: any = useAxios(detailNumber);

  if (loading) {
    return <div> LOADING ....</div>;
  } else {
    return (
      <div className="qna">
        <Question question={questionDetail} />
        <AnswerInput />
      </div>
    );
  }
}

export default Detail;
