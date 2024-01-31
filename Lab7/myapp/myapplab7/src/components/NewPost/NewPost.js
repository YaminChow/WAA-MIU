import axios from "axios";
import React, { useRef } from "react";

const NewPost = (props) => {
  const newPostForm = useRef();
  const addButtonClicked = () => {
    const form = newPostForm.current;
    const data = {
      author: form["author"].value,
      title: form["title"].value,
      content: form["content"].value,
    };
    axios
      .post("http://localhost:8080/api/v1/posts", data)
      .then((response) => {
        props.changeFetchFlag();
      })
      .catch();
  };

  return (
    <div className="NewPost">
      <form ref={newPostForm}>
        <h1> Add Post</h1>

        <label>Title</label>
        <input type="text" label={"title"} name={"title"} />

        <label>Author</label>
        <input type="text" label={"author"} name={"author"} />

        <label>Content</label>
        <input type="text" label={"Content"} name={"content"} />
      </form>

      <button onClick={addButtonClicked}> Add Product</button>
    </div>
  );
};

export default NewPost;
