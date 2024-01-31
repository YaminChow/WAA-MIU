import React, { useEffect, useState } from "react";
import Post from "../../components/Post/Post";
import axios from "axios";

const Posts = (props) => {
  const [postsState, setPostsState] = useState([
    { id: 111, title: "Happiness", author: "John" },
    { id: 112, title: "MIU", author: "Dean" },
    { id: 113, title: "Life", author: "Sandra" },
  ]);

  const fetchPosts = () => {
    axios
      .get("http://localhost:8080/api/v1/posts")
      .then((response) => {
        setPostsState(response.data);
      })
      .catch(() => {
        console.log("Error ");
      });
  };
  useEffect(() => {
    fetchPosts();
  }, [props.fetchFlag]);
  const posts = postsState.map((post) => {
    return (
      <Post
        title={post.title}
        author={post.author}
        id={post.id}
        key={post.id}
        setSelected={() => {
          props.setSelected(post.id, post.author, post.title);
        }}
      />
    );
  });
  return posts;
};

export default Posts;
