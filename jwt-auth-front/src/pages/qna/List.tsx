import React from "react";

import QnaList from "../../components/qna/QnaList";
import "../../styles/qna/qna.scss";

const List = () => {
  return (
    <div className="qna">
      <QnaList question="이건 무슨 오류인가요?" date="2022-01-18" nickname="관리자" />
    </div>
  );
};

export default List;
