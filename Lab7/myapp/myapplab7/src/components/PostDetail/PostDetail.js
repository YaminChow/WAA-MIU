import React, { Fragment, useEffect, useState } from "react";
import axios from "axios";
import Comment from "../Comment/Comment";
var i = 0;

const PostDetail = (props) => {
  console.log("POSTDETAILS UPDATE");

  const [postDetail, setPostDetail] = useState({});

  useEffect(() => {
    if (props.id > 0) {
      axios
        .get("http://localhost:8080/api/v1/posts/" + props.id)
        .then((response) => {
          setPostDetail(response.data);
          console.log("RESPONSE:", response.data);
        })
        .catch((err) => console.log(err.message));
    }
  }, [props.id]);

  const deleteButtonClicked = (id) => {
    axios
      .delete("http://localhost:8080/api/v1/posts/" + id)
      .then((response) => {
        props.changeFetchFlag();
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const space = <Fragment>&nbsp;&nbsp;</Fragment>;

  let postDetailsDisplay = null;

  return (
    <div className="ContentDetail">
      <h1>
        {" "}
        <span className="Label">Id: </span> {props.id}
      </h1>
      <div className="Label">
        <label>Title: </label> {props.title} <br />
        <label>Author: </label> {props.author}
        <div style={{ textAlign: "left" }}>
          {space} Comments <br />
          {postDetail.commets != null
            ? postDetail.commets.map((comment) => {
                console.log("<<comment>>");
                return <Comment comment={comment.name} key={comment.id} />;
              })
            : null}
        </div>
      </div>

      <button
        onClick={() => {
          deleteButtonClicked(props.id);
        }}
      >
        {" "}
        Delete
      </button>
    </div>
  );
};

export default PostDetail;
