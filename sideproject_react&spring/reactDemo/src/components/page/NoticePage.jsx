import React, { useEffect, useState } from 'react'
import Header from '../include/Header';
import { Button, Table } from 'react-bootstrap';
import Bottom from '../include/Bottom';
import { useNavigate } from 'react-router';
import NoticeRow from '../notice/NoticeRow';
import { noticeListDB } from '../../service/dbLogic';

const NoticePage = () => {
  console.log('first');
  const navigate = useNavigate();
  const [gubun, setGubun] = useState('')
  const [keyword, setKeyword] = useState('')
  const [notices, setNotices] = useState([{}]) // List<Map<>>의 형태 // 전체 목록을 notices가 쥐고 있음
  console.log(notices); // 아직 DB를 경유하지 않았기 때문에 빈 깡통

  // useEffect 안에 실행문을 넣지 않고 함수 호출로 대체한 이유 
  //  : 최초 목록 출력할 때 한 번, 검색버튼이나 text박스에 엔터 쳤을 때 한 번 - 여러 번 필요하다
  useEffect(() => { // effect Hook : 이름 앞에 useXXX 형태면 Hook이다(React 16.8 ver부터)
    console.log('effect');
    noticeList();
  }, []); // 의존성 배열이 빈 깡통이면 최초 한 번만 실행됨
  // 배열 안에 여러가지의 상태값이 올 수 있는데 이 상태가 바뀔때마다 매번 실행됨

  // 함수 파라미터 앞에 async 붙인 건 비동기처리 - 스프링 플젝과 연계!(await noticeListDB() - jsonNoticeList())
  const noticeList = async () => {
    console.log("noticeList호출");
    const gubun = document.querySelector("#gubun").value; // n_title, n_writer, n_content : select combo
    const keyword = document.querySelector("#keyword").value; // 휴관, 이벤트..
    console.log(`${gubun}, ${keyword}`);
    const notice = { // 괄호가 있는 것 : JS 객체이다
      gubun: gubun,
      keyword: keyword,
    };
    const res = await noticeListDB(notice) // 스프링 플젝에서 요청하기 - 비동기 상황 연출 : 지연되는 것은 8000번 서버 -> 1521번 경유
    // 조회결과를 요청한 다음에 초기화 한다
    document.querySelector("#gubun").value = '분류선택'; // 분류선택이 default니까
    document.querySelector("#keyword").value = ''; // 빈 문자열을 주면 자동으로 '검색어를 입력하세요'가 뜨니까
    console.log(res);
    setNotices(res.data); // 여기서 useState에 값이 채워짐
    console.log(notices);
  } // end of noticeList

  const noticeSearch = (event) => {
    console.log(`noticeSearch ==> ${event.key}`); // Enter 출력
    if(event.key === 'Enter'){ // == 값만 비교, === 타입까지 비교
      noticeList();
    }
  }

  return (
    <>
      <Header />
      <div className="container">
        <div className="page-header">
          <h2>
            공지관리 <small>글목록</small>
          </h2>
          <hr />
        </div>

        <div className="row">
          <div className="col-3">
            <select id="gubun" className="form-select" aria-label="분류선택">
              <option defaultValue>분류선택</option>
              <option value="n_title">제목</option> {/* gubun에 있는 값을 notice.xml의 n_title과 같은지 비교해야 하기 때문에 value를 n_title로 */}
              <option value="n_writer">작성자</option>
              <option value="n_content">내용</option>
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
              onKeyUp={noticeSearch}
            />
          </div>
          <div className="col-3">
            <Button variant="danger" id="btn_search" onClick={noticeList}>
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
              {notices &&
                notices.map((notice, key) => ( // notices : Array 형태 // Notice : 값
                  /* props : 리액트에서는 태그 안에 속성값으로 현재 함수의 주소번지를 넘길 수 있다 */
                  /* key에 대응하는 값은 반드시 유일무이 해야 함. 알고리즘이 값을 비교해서 변경되면 새로 그린다 */
                  <NoticeRow key={key} notice={notice} /> // notice : 한 개의 row // 한 건씩 반복 // notice의 주소번지 값을 {}에 넣음
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
          </div>

          <hr />
          <div className="boardlist-footer">
            <Button variant="warning" onClick={noticeList}>
              전체조회
            </Button>
            &nbsp;
            <Button
              variant="success"
              onClick={() => {
                navigate(`/notice/write`);
              }}
            >
              글쓰기
            </Button>
          </div>
        </div>
      </div>
      <Bottom /> {/* header, cotent, bottom : 삼단 구성 */}
    </>
  )
}

export default NoticePage
/*  
최초 실행 페이지 : HomePage.jsx 출력
상단(Header.jsx) 메뉴바에서 공지사항을 누르면 NoticePage함수 호출됨
이 때 useEffect Hook에서 noticeListDB 함수를 최초 한 번만 호출함. 의존성 배열이 빈깡통이기 때문.
*/