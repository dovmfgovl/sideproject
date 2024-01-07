import React from 'react';
import { Link } from 'react-router-dom';

//const BoardRow = (props) => {
const BoardRow = ({board}) => {
  return (
    <>
      <tr>
        <td>{board.BM_NO}</td>
        <td>
          <Link to={"/boarddetail/"+board.BM_NO} className='btn btn-primary'>{board.BM_TITLE}</Link>
        </td>
        <td>{board.BM_WRITER}</td>
      </tr>      
    </>
  )
}

export default BoardRow