import { prettyFormat } from "@testing-library/react";
import React from "react";
import Dashboard from "../../containers/Dashboard";
var i = 0;

const PostDetail = (props) => {
  //console.log("<<> postDetail" + props);
  return (
    <div className="ContentDetail">
       <h1 a> <span className="Label">Id:  </span> {props.id}</h1>
      <div className="Label">
        <label>Title: </label> {props.title} <br />
        <label>Author: </label> {props.author}
      </div>
      {/* <button onClick={editButtonClicked}>edit</button>
      <button onClick={deleteButtonClicked}>delete</button> */}
    </div>
  );
};

export default PostDetail;
