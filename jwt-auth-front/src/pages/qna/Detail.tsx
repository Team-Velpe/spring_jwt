import React, { useEffect, useState } from "react";

import "../../styles/qna/qna.scss";

import Answer from "../../components/qna/Answer";
import Question from "../../components/qna/Question";
import Textarea from "../../components/qna/Textarea";
import Button from "../../components/member/Button";
import axiosI from "../../utils/AxiosI";
import Q from "q";

// const Detail = () => {

//   let pathUrl = window.location.pathname;
//   let path = pathUrl.split("/");
//   let detailNumber = path[path.length - 1];

//   const [question, setQuestion] = useState();
//   const [qTest, setQtest] = useState();

//   useEffect(() => {
//     axiosI
//       .get("http://localhost:8083/api/v1/qna/q/" + detailNumber)
//       .then((response) => {
//         const qData = response.data.data;
//         setQuestion(qData);

//         if (window.localStorage.getItem("qData") !== null) {
//           window.localStorage.removeItem("qData");
//         }
//         window.localStorage.setItem("qData", JSON.stringify(qData));

//         const qTestString: any = window.localStorage.getItem("qData");
//         // setQtest(JSON.parse(qTestString));
//         setQuestion(JSON.parse(qTestString));
//       })
//       .catch((error) => {
//         console.log(error);
//       });
//   }, []);

//   console.log(qTest);
//   // console.log(qTest);

//   return (
//     <div className="qna">
//       <Question question={question} />
//       {/* {question === null ? <Question question={qTest} /> : <Question question={question} />} */}
//       <Answer writer="답변자1" date="2022년 1월 18일 오후 9시" content="content" />
//       <Answer writer="답변자2" date="2022년 1월 18일 오후 9시" content="content" />
//       <form className="comment">
//         <Textarea name="comment" placeholder="답변을 작성해 주세요" rows={4} />
//         <Button name="답변 작성" backgroundColor="#ff4800" />
//       </form>
//     </div>
//   );
// };

function Detail() {
  let pathUrl = window.location.pathname;
  let path = pathUrl.split("/");
  let detailNumber = path[path.length - 1];

  // const [question, setQuestion] = useState("");

  // const [question, setQuestion] = useRecoilState(questionState);

  // async function fn() {
  //   const result = await axiosI
  //     .get("http://localhost:8083/api/v1/qna/q/" + detailNumber)
  //     .then((response) => {
  //       console.log(response);

  //       const qData = response.data.data;
  //       const qDataString = JSON.stringify(qData);
  //       console.log("qdata", qDataString);
  //       console.log("type", typeof qDataString);
  //       // setQuestion(qDataString);
  //       window.localStorage.setItem("qData", qDataString);
  //       // console.log("1." + question);
  //       console.log("2." + window.localStorage.getItem("qData"));
  //       const a: any = window.localStorage.getItem("qData");
  //       setQuestion(a);
  //       console.log("3. ", question);

  //       // window.localStorage.setItem("qData", qDataString);
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // }

  // async function fc() {
  //   await fn();
  //   console.log(question);
  //   // window.localStorage.setItem("qData", question);
  // }

  // useEffect(() => {
  //   fc();
  // }, []);
  // //console.log(window.localStorage.getItem("qData"))
  // setTimeout(() => setQuestion(window.localStorage.getItem("qData")), 1000);

  // console.log("!!", question);

  return (
    <div className="qna">
      <Question />
      {/* {question === null ? <Question question={qTest} /> : <Question question={question} />} */}
      <Answer writer="답변자1" date="2022년 1월 18일 오후 9시" content="content" />
      <Answer writer="답변자2" date="2022년 1월 18일 오후 9시" content="content" />
      <form className="comment">
        <Textarea name="comment" placeholder="답변을 작성해 주세요" rows={4} />
        <Button name="답변 작성" backgroundColor="#ff4800" />
      </form>
    </div>
  );
}

export default Detail;
