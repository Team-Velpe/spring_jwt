import React from "react";
import "../../styles/member/input.scss";

interface InputProps {
  type: string;
  name: string;
  placeholder: string;
}

const Input: React.FC<InputProps> = ({ type, name, placeholder }) => {
  return <input type={type} name={name} placeholder={placeholder} />;
};

export default Input;
