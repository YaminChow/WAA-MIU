import React from "react";

const Post = (props) => {
  return (
    <div className="Content" onClick={props.setSelected}>
      <h1 className="Field">
        <span className="Label"> Id </span> {props.id}
      </h1>
      <div className="Field">
        <label>Title: </label> {props.title} <br />
        <label>Author: </label> {props.author}
      </div>
    </div>
  );
};

export default Post;
