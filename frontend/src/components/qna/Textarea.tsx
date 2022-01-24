import React from "react";

import "../../styles/qna/textarea.scss";

interface TextareaProps {
  name: string;
  placeholder: string;
  rows: number;
}

const Textarea: React.FC<TextareaProps> = ({ name, placeholder, rows }) => {
  return <textarea name={name} rows={rows} placeholder={placeholder}></textarea>;
};

export default Textarea;
