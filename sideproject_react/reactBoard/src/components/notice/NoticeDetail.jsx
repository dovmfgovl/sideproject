import React, { useEffect, useState } from "react";
import {noticeDeleteDB, noticeDetailDB} from "../../service/dbLogic";
import {useNavigate, useParams} from "react-router";
import Header from "../include/Header";
import Bottom from "../include/Bottom";
import "../css/notice.css";

const NoticeDetail = () => {
  const {id} = useParams(); // URL 파라미터로부터 id 추출
  const navigate = useNavigate();
  const [notice, setNotice] = useState([{}]); // 공지사항 담기

  const notices = {
    n_no : id
  }
  console.log(notices);

  const getNotice = async () => {
    const res  = await noticeDetailDB(notices);
    setNotice(res.data);
    console.log(res.data);

    const selectNotice = res.data.find(notice => notice.N_NO === parseInt(id));
    if (selectNotice) {
      setNotice(selectNotice); // 선택된 공지사항 설정
    } else {
      console.error(`ID가 ${id}에 해당하는 공지사항을 찾을 수 없습니다.`);
    }
  }

  useEffect(() => {
    getNotice();
  }, []);

  const moveToUpdate = () => {
    navigate('/notice/update/'+ id);
  }

  const noticeDelete = async () => {
    if (window.confirm('게시글을 삭제하시겠습니까?')) {
      const res = await noticeDeleteDB(notices);
      if (res.status === 200) {
        navigate('/notice');
      } else {
        console.error('삭제에 실패했습니다.');
      }
    }
  }

  return (
    <>
      <Header />
      <h2 align="center" style={{marginTop:100, marginBottom:100}}>게시글 상세정보</h2>

      <div className="post-view-wrapper">
        {notice ? (
          <>
            <div className="post-view-row">
              <label>게시글 번호</label>
              <label>{notice.N_NO}</label>
            </div>
            <div className="post-view-row">
              <label>제목</label>
              <label>{notice.N_TITLE}</label>
            </div>
            <div className="post-view-row">
              <label>작성자</label>
              <label>{notice.N_WRITER}</label>
            </div>
            <div className="post-view-row">
              <label>내용</label>
              <div>{notice.N_CONTENT }</div>
            </div>
          </>
        ) : (
          <p>해당 게시글을 찾을 수 없습니다.</p>
        )}
        <button className="post-view-go-list-btn" onClick={moveToUpdate}>
          수정
        </button>
        <button className="post-view-go-list-btn" onClick={noticeDelete}>
          삭제
        </button>
        <button className="post-view-go-list-btn" onClick={() => window.location.href='/notice'}>
          목록
        </button>
      </div>
      <Bottom />
    </>
  );

}

export default NoticeDetail;