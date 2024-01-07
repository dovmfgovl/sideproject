import axios from 'axios'
import React, { useEffect, useState } from 'react'
import Header from '../include/Header';
import Bottom from '../include/Bottom';
import Home from '../home/Home';

const HomePage = () => {
  // 상태값은 컴포넌트가 아닌 페이지가 들고 있게 하는게 좋다 
  const [boards, setBoards] = useState([]);
  const [number, setNumber] = useState(0);
  const [user, setUser] = useState({});
  const [users, setUsers] = useState([]);

  useEffect(() => {
    axios.get('https://jsonplaceholder.typicode.com/users')
    .then(response => {
      console.log(response);
      console.log('data:'+response.data);
      console.log('data:'+response.data[0]);
      console.log('data:'+response.data[0].name);
      console.log('data:'+response.data[0].address['city']);
      setUsers(response.data);
    });
  },[]);

  useEffect(() => {
    //console.log('useEffect 호출');
    // 다운로드 가정 - 여기서는 io가 발생됨. 네트워크발생
    let data = [
      { b_no:1, b_title:'Ajax프로그래밍',  b_writer: '이재선', b_content: '내용1' },
      { b_no:2, b_title:'JSP프로그래밍',  b_writer: '김유신', b_content: '내용2' },
      { b_no:3, b_title:'Ajax프로그래밍',  b_writer: '강감찬', b_content: '내용3' },   
    ];
    //setBoards(data);//기존에 있는거 무시하고 새로 넘김
    // 다운로드가 안된 상태에서 빈데이터가 setBoards에 들어간다
    setBoards([...data]);//기존에 있는게 있으면 뒤에 붙여서 넘김
    setUser({ id: 1, username: 'good' });
  },[]);// 빈 배열은 어디에도 의존하고 있지 않으므로 한번만 실행됨
  const boardList = () => {
    let data = [
      { b_no:1, b_title:'Ajax프로그래밍',  b_writer: '이재선', b_content: '내용1' },
      { b_no:2, b_title:'JSP프로그래밍',  b_writer: '김유신', b_content: '내용2' },
      { b_no:3, b_title:'Ajax프로그래밍',  b_writer: '강감찬', b_content: '내용3' },   
    ];
    setBoards([...data]);  
  };
  const increase = () => {
    setNumber(number+1);
  }  
  return (
    <>
        <Header />
        <Home boards={boards} user={user} users={users}
            setBoards={setBoards} boardList={boardList} number={number} 
            increase={increase}/>
        <Bottom />
    </>
  )
}

export default HomePage