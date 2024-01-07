import React from "react";
import Comment from "./Comment";


const comments = [
  {
    name: "이슬기",
    comment: "안녕하세요. 슬기입니다."
  },
  {
    name: "유재석",
    comment: "리액트 재밌어요~~"
  },
  {
    name: "김지유",
    comment: "저도 리액트 배워보고 싶어요!"
  }
]
function CommentList(props) {

  return (
    <div>
      {comments.map((comment) => {
        return (
          <Comment name={comment.name} comment={comment.comment} />
        );
      })}
    </div>
  );
}
export default CommentList;