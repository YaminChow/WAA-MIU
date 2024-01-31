import React, { useContext } from "react";
import { ThemeColorContext } from "../../store/ThemeColor";

const Comment = (props) => {
  const colorContext = useContext(ThemeColorContext);

  return (
    <div style={{ color: colorContext.color }} className="Comment">
      {props.comment}
    </div>
  );
};

export default Comment;
