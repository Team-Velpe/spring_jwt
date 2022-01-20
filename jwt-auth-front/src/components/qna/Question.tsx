import React, { useEffect, useState } from "react";

import { useRecoilState } from "recoil";
import { questState } from "../../utils/State";

import "../../styles/qna/question.scss";
import axiosI from "../../utils/AxiosI";

// const Question = (props: any) => {
//   const { question } = props;

//   return (
//     <div className="quest">
//       <div className="quest-info">
//         <div className="quest-icon">Q.</div>
//         <div>
//           <div className="quest-writer">
//             <strong>{question.nickname}</strong>님
//           </div>
//           <div className="quest-title">{question.title}</div>
//           <div className="quest-date">{question.reg_date}</div>
//         </div>
//       </div>
//       <hr />
//       <div className="quest-cont">{question.body}</div>
//     </div>
//   );
// };

// const result = axiosI
//   .get("http://localhost:8083/api/v1/qna/q/" + 1)
//   .then((response) => {
//     console.log(response);

//     const { data } = response.data;

//     return data;

//     // setQuest(data);
//   })
//   .catch((error) => {
//     console.log(error);
//   });

export default function Question() {
  // const ques: any = window.localStorage.getItem("qData");

  // console.log("ques", ques);

  // const test = JSON.parse(ques);

  // console.log("test", test);

  // const [quest, setQuest] = useRecoilState(questState);
  const [quest, setQuest] = useState([]);

  // useEffect(() => {
  //   axiosI
  //     .get("http://localhost:8083/api/v1/qna/q/" + 1)
  //     .then((response) => {
  //       console.log(response);

  //       const { data } = response.data;

  //       console.log("123", data);

  //       setQuest(data);
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // }, []);

  // console.log("dlrj!", quest);

  async function get() {
    axiosI
      .get("http://localhost:8083/api/v1/qna/q/" + 1)
      .then((response) => {
        console.log(response);

        const { data } = response.data;

        setQuest(data);

        // return data;

        // setQuest(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  console.log(quest);

  return (
    <div className="quest">
      <div className="quest-info">
        <div className="quest-icon">Q.</div>
        <div>
          <div className="quest-writer">{/* <strong>{quest}</strong>님 */}</div>
          {/* <div className="quest-title">{title}</div> */}
          {/* <div className="quest-date">{reg_date}</div> */}
        </div>
      </div>
      <hr />
      {/* <div className="quest-cont">{body}</div> */}
    </div>
  );
}
