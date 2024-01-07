import React from "react";
import Book from "./Book";

function Library(props){
  return (
    <div>
      {/* 3개의 Book 컴포넌트를 렌더링 하고 있다. */}
      <Book name="처음 만난 파이썬" numOfPage={300} />
      <Book name="처음 만난 AWS" numOfPage={400} />
      <Book name="처음 만난 리액트" numOfPage={500} />
    </div>
  );
}
export default Library;