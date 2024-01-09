import React, { useCallback, useState } from 'react'
import { BButton, ContainerDiv, FormDiv, HeaderDiv } from '../style/FormStyle'
import { noticeInsertDB } from '../../service/dbLogic';

const NoticeWrite = () => {
  // input type의 속성을 굳이 state Hook으로 하는 건 값이 변경될 때 마다 동기화 처리하기 위함
  // 또한 리액트는 상태값이 바뀔 때마다 함수가 자꾸 호출됨
  // 아래 선언한 함수들이 반복적으로 생성됨 - 비효율적 -> 따라서 useCallback() 사용함(메모리에 저장했다가 사용)
  const[title, setTitle] = useState('');
  const[writer, setWriter] = useState('');
  const[content, setContent] = useState('');
  const handleTitle = useCallback((value) => {
    setTitle(value);
  },[]);  
  const handleWriter = useCallback((value) => {
    setWriter(value);
  },[]);  
  const handleContent = useCallback((value) => {
    console.log(value);
    setContent(value);
  },[]);
  const noticeInsert = async() => {
    console.log('noticeInsert');
    const notice = {
      n_title : title,
      n_writer : writer,
      n_content : content
    }
    console.log(notice);
    const res = await noticeInsertDB(notice);
    console.log(res.data); // 1만 출력됨
    window.location.replace(`/notice`);    
  }  
  return (
    <>
      <ContainerDiv>
        <HeaderDiv>
          <h3 style={{marginLeft:"10px"}}>공지사항 글작성</h3>
        </HeaderDiv>
        <FormDiv>
          <div style={{width:"100%", maxWidth:"2000px"}}>
            <div style={{display: 'flex', justifyContent: 'space-between', marginBottom:'5px'}}>
              <h3>제목</h3> 
              <BButton onClick={()=>{noticeInsert()}}>글쓰기</BButton>
            </div>
            <input id="dataset-title" type="text" maxLength="50" placeholder="제목을 입력하세요."
              style={{width:"100%",height:'40px' , border:'1px solid lightGray', marginBottom:'5px'}} onChange={(e)=>{handleTitle(e.target.value)}}/>          
            <input id="dataset-writer" type="text" maxLength="50" placeholder="작성자를 입력하세요."
              style={{width:"30%",height:'40px' , border:'1px solid lightGray', marginBottom:'5px'}} onChange={(e)=>{handleWriter(e.target.value)}}/>          
            <div style={{display: 'flex', justifyContent: 'space-between', marginBottom:'5px'}}></div>
            <h3>상세내용</h3>
            <hr style={{margin:'10px 0px 10px 0px'}}/>
            <textarea placeholder="내용을 입력하세요." className="form-control" name='content' onChange={(e)=>{handleContent(e.target.value)}} rows="15"></textarea>
          </div>
        </FormDiv>
      </ContainerDiv>
    </>
  )
}

export default NoticeWrite