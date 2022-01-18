import React from "react";

import "../../styles/qna/textarea.scss";

interface TextareaProps {
  name: string;
  placeholder: string;
  cols: number;
}

const Textarea: React.FC<TextareaProps> = ({ name, placeholder, cols }) => {
  return <textarea name={name} cols={cols} placeholder={placeholder}></textarea>;
};

export default Textarea;
