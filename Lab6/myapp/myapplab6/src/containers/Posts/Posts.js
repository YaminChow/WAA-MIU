import React from "react";
import Post from "../../components/Post/Post";

const Posts = (props) => {
  const posts = props.posts.map((post) => {
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
