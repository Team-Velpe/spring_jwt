import React from "react";
import "../../styles/member/input.scss";

import Input from "./Input";

interface InputDivProps {
  info: string;
  type: string;
  name: string;
  placeholder: string;
}

const InputDiv: React.FC<InputDivProps> = ({ info, type, name, placeholder }) => {
  return (
    <div className="form-input-div">
      <p>
        {info} <span>*</span>
      </p>
      <Input type={type} name={name} placeholder={placeholder} />
    </div>
  );
};

export default InputDiv;
