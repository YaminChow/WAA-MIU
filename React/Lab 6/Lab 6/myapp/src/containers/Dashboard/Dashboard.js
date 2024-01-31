import React, { useState } from 'react'
import Posts from '../Posts/posts';


export default function Dashboard(){
const [postsState, setpostsState] = useState(
    [
        { id: 111, title: "Happiness", author: "John" },
        { id: 112, title: "MIU", author: "Dean" },
        { id: 113, title: "Life", author: "Sandra" }
    ]
);


  return (
    <div>
      <Posts posts={postsState}/>
    </div>
  )

}


