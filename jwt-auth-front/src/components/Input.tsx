import React from "react";
import "../styles/components/input.scss";

interface InputProps {
  name: string;
  placeholder: string;
}

const Input: React.FC<InputProps> = ({ name, placeholder }) => {
  return <input type="input" name={name} placeholder={placeholder} />;
};

export default Input;
