import React from "react";
import "../styles/components/button.scss";

interface ButtonProps {
  name: string;
  backgroundColor: string;
}

const Button: React.FC<ButtonProps> = ({ name, backgroundColor }) => {
  return (
    <button type="submit" className="form-btn" style={{ backgroundColor }}>
      {name}
    </button>
  );
};

export default Button;
