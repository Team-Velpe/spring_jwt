import React, { Component, useEffect, useState } from "react";

import "../../styles/qna/qna.scss";

import Question from "../../components/qna/Question";
import Textarea from "../../components/qna/Textarea";
import Button from "../../components/member/Button";
import axiosI from "../../utils/AxiosI";

function useAxios(detailNumber: String) {

  const [questionDetail, setQuestionDetail] = useState();
  const [loading, setLoading] = useState(true);


  async function fetchUrl() {

    const result = await axiosI.get("http://localhost:8083/api/v1/qna/q/"+detailNumber)
    
    const json = await result.data.data

    setQuestionDetail(json);
    setLoading(false);

  }

  useEffect(()=>{
    fetchUrl();
  },[]);

  return [questionDetail, loading];

}


async function addAnswer(e: any) {

  let pathUrl = window.location.pathname;
  let path = pathUrl.split("/");
  let detailNumber = path[path.length - 1];

  const token: any = window.localStorage.getItem("accessToken");
  console.log(token);

  const base64Payload = token.split(".")[1];
  const result = JSON.parse(atob(base64Payload));

  e.preventDefault();

  const { comment } = e.target;

  const data = {
    body : comment.value,
    loginId : result.sub,
    q_id : detailNumber
  };

  await axiosI.post("http://localhost:8083/api/v1/qna/a", data)
  .then(

    (response) => {
      if ( response.status === 201 ) {
        window.location.reload();
      }
    }

  )
  .catch(

    (error) => {
      console.log(error);
    }

  )

}

function Detail() {

  let pathUrl = window.location.pathname;
  let path = pathUrl.split("/");
  let detailNumber = path[path.length - 1];

  const [questionDetail, loading]:any = useAxios(detailNumber);

  if ( loading ) {
    return (
      <div> LOADING ....</div>
    );
  } else {

    return (
      <div className="qna">
        <Question question={questionDetail} />
        <form className="comment" onSubmit={addAnswer}>
          <Textarea name="comment" placeholder="답변을 작성해 주세요" rows={4} />
          <Button name="답변 작성" backgroundColor="#ff4800" />
        </form>
      </div>
    );
  }
  


}

export default Detail;


