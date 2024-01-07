import React from 'react'
import { Link } from 'react-router-dom'
// 전체 조회결과 13건에서 한 건씩 처리할 화면을 그려줄 함수를 따로 선언하였다.
// 함수를 태그이름으로 사용할 수 있다 - props를 통해 현재 페이지의 주소번지를 하위 페이지에 넘길 수 있다.
const NoticeRow = ({notice}) => { //  NoticeRow의 {props}
  console.log(notice);
  console.log(notice.N_TITLE);
  return (
    <>
      <tr>
        <td>{notice.N_NO}</td>
        <td>
          <Link to={"/notice/"+notice.N_NO} className='btn btn-primary'>{notice.N_TITLE}</Link>
        </td>
        <td>{notice.N_WRITER}</td>
      </tr>  
    </>
  )
}

export default NoticeRow