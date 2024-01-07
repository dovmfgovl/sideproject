import React, { useEffect, useState } from "react";
import { Button, Table } from "react-bootstrap";
import BoardRow from "../board/BoardRow";
import Bottom from "../include/Bottom";
import Header from "../include/Header";
import "../css/board.css";
import { useNavigate } from "react-router-dom";
import MyPagination from "../include/MyPagination";

const BoardPage = () => {
  const navigate = useNavigate();
  const [show, setShow] = useState(false);
  const [rno, setRno] = useState([]);
  const [boards, setBoards] = useState({
    1: {
      b_no: 1,
      b_title: "Ajax프로그래밍",
      b_writer: "이재선",
      b_content: "내용1",
    },
    2: {
      b_no: 2,
      b_title: "JSP프로그래밍",
      b_writer: "김유신",
      b_content: "내용2",
    },
    3: {
      b_no: 3,
      b_title: "Ajax프로그래밍",
      b_writer: "강감찬",
      b_content: "내용3",
    },
  });
  const [listBody, setListBody] = useState([]);

  useEffect(() => {
    boardList();
  }, []);

  const boardList = async () => {
    console.log("boardList호출");
    const gubun = document.querySelector("#gubun").value;
    const keyword = document.querySelector("#keyword").value;
    const board = {
      gubun: gubun,
      keyword: keyword,
    };
  };
  const handleShow = () => setShow(true);

  return (
    <>
      <Header />
      <div className="container">
        <div className="page-header">
          <h2>
            게시판 <small>글목록</small>
          </h2>
          <hr />
        </div>

        <div className="row">
          <div className="col-3">
            <select id="gubun" className="form-select" aria-label="분류선택">
              <option defaultValue>분류선택</option>
              <option value="b_title">제목</option>
              <option value="b_writer">작성자</option>
              <option value="b_content">내용</option>
            </select>
          </div>
          <div className="col-6">
            <input
              type="text"
              id="keyword"
              className="form-control"
              placeholder="검색어를 입력하세요"
              aria-label="검색어를 입력하세요"
              aria-describedby="btn_search"
            />
          </div>
          <div className="col-3">
            <Button variant="danger" id="btn_search">
              검색
            </Button>
          </div>
        </div>

        <div className="board-list">
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>#</th>
                <th>제목</th>
                <th>작성자</th>
              </tr>
            </thead>
            <tbody>
              {listBody &&
                Object.keys(listBody).map((key) => (
                  <BoardRow key={key} board={listBody[key]} />
                ))}
            </tbody>
          </Table>

          <div
            style={{
              margin: "10px",
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              justifyContent: "center",
              width: "100%",
            }}
          >
            <MyPagination rno={rno} path={"/board"} />
          </div>

          <hr />
          <div className="boardlist-footer">
            <Button variant="warning" onClick={boardList}>
              전체조회
            </Button>
            &nbsp;
            <Button
              variant="success"
              onClick={() => {
                navigate(`/board/write`);
              }}
            >
              글쓰기
            </Button>
          </div>
        </div>
      </div>
      <Bottom />
    </>
  );
};

export default BoardPage;
