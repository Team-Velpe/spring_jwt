import { atom } from "recoil";

const loginState = atom({
  key: "login",
  default: false,
});

const questState = atom({
  key: "quest",
  default: "",
});

export { loginState };
export { questState };
