import React, { useEffect, useState } from "react";
import Posts from "./Posts/Posts";
import PostDetail from "../components/PostDetail/PostDetail";
import { ThemeColorContext } from "../store/ThemeColor";
import NewPost from "../components/NewPost/NewPost";
import axios from "axios";
import { fetchService } from "../services/fetchServices";

export default function Dashboard() {
  const [themeColorState, setThemeColorState] = useState({ color: "red" });
  const [postsState, setPostsState] = useState([
    { id: 111, title: "Happiness", author: "John" },
    { id: 112, title: "MIU", author: "Dean" },
    { id: 113, title: "Life", author: "Sandra" },
  ]);
  const [postState, setPostState] = useState({
    title: "",
  });
  const [seletedId, setSeletedId] = useState();
  const [selectedTitle, setSelectedTitle] = useState();
  const [selectedAuthor, setSelectedAuthor] = useState();
  const [postDetailState, setPostDetailState] = useState({
    id: "",
    title: "",
    author: "",
  });
  const [selectedState, setSelectedState] = useState(null);

  const onChange = (events) => {
    setPostState(events.target.value);
  };
  const editButtonClicked = () => {
    const copy = [...postsState];
    console.log("<<>>" + postState);
    const updatedPosts = copy.map((post) => {
      if (post.id === 111) {
        return { ...post, title: postState };
      }
      return post;
    });

    setPostsState(updatedPosts);

    console.log("<<>>" + postsState[0].author);

    setPostState(postsState[0]);
  };

  const [fetchFlag, setFetchflag] = useState(true);

  const changeFetchFlag = () => {
    setFetchflag(!fetchFlag);
  };
  const setSelected = (id, author, title) => {
    setSeletedId(id);
    setSelectedTitle(title);
    setSelectedAuthor(author);
  };

  return (
    <div>
      <ThemeColorContext.Provider value={themeColorState}>
        <div className="Post">
          <Posts
            posts={postsState}
            setSelected={setSelected}
            fetchFlag={fetchFlag}
          />
        </div>
        <div>
          <input type="text" name={"title"} onChange={onChange} />
          <br />
          <button onClick={editButtonClicked}>Change Name </button>

          {(event) => {
            onChange(event);
          }}
          {editButtonClicked}
        </div>
        <div className="PostDetail">
          <PostDetail
            id={seletedId}
            title={selectedTitle}
            author={selectedAuthor}
            changeFetchFlag={changeFetchFlag}
          />
        </div>
        <div>
          <NewPost changeFetchFlag={changeFetchFlag} />
          {/* {fetchFlag?<NewPost
                        changeFetchFlag={changeFetchFlag} />:null}  */}
        </div>
      </ThemeColorContext.Provider>
    </div>
  );
}
