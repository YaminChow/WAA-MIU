import React from 'react'
import Post from '../../components/Post/post';

const Posts = (props) => {
    const posts = props.posts.map( post =>{
  return <Post
    id={post.id}
    title={post.title}
    author={post.author}
    key={
        post.id
    }
      
    />
});
return Posts;
}

export default Posts
