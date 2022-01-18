import React from "react";
import "../../styles/member/input.scss";

import Input from "./Input";

interface InputDivProps {
  info: string;
  name: string;
  placeholder: string;
}

const InputDiv: React.FC<InputDivProps> = ({ info, name, placeholder }) => {
  return (
    <div className="form-input-name">
      <p>
        {info} <span>*</span>
      </p>
      <Input name={name} placeholder={placeholder} />
    </div>
  );
};

export default InputDiv;
