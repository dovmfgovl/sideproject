import React, { useEffect, useState } from "react";
import { Button, Table } from "react-bootstrap";
import BoardRow from "../board/BoardRow";
import Bottom from "../include/Bottom";
import Header from "../include/Header";
import "../css/board.css";
import { useNavigate } from "react-router-dom";
//import MyPagination from "../include/MyPagination";
import {boardListDB} from "../../service/dbLogic_board";

const BoardPage = () => {
  const navigate = useNavigate();
  //const [show, setShow] = useState(false);
  //const [rno, setRno] = useState([]);
  const [gubun, setGubun] = useState();
  const [keyword, setKeyword] = useState();
  const [boards, setBoards] = useState([{}]);
  //const [listBody, setListBody] = useState([]);

  useEffect(() => {
    boardList();
  }, []);

  const boardList = async () => {
    console.log("boardList호출");
    const gubun = document.querySelector("#gubun").value;
    const keyword = document.querySelector("#keyword").value;
    console.log(`${gubun}, ${keyword}`);
    const board = {
      gubun: gubun,
      keyword: keyword,
    };
    const res = await boardListDB(board);
    document.querySelector("#gubun").value = '분류선택';
    document.querySelector("#keyword").value = '';
    console.log(res);
    setBoards(res.data);
    console.log(res.data);
  };
  //const handleShow = () => setShow(true);

  const boardSearch = (event) => {
    console.log(`boardSearch ==> ${event.key}`);
    if (event.key === 'Enter') {
      boardList();
    }
  }

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
                  onKeyUp={boardSearch}
              />
            </div>
            <div className="col-3">
              <Button variant="danger" id="btn_search" onClick={boardList}>
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
              {boards &&
                  boards.map((board, key) => (
                      <BoardRow key={key} board={board} />
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
              {/*<MyPagination rno={rno} path={"/board"} />*/}
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
