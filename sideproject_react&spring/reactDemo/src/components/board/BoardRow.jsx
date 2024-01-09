import React from 'react';
import { Link } from 'react-router-dom';

//const BoardRow = (props) => {
const BoardRow = ({board}) => {
    console.log(board);
    return (
        <>
            <tr>
                <td>{board.B_NO}</td>
                <td>
                    <Link to={"/board/"+board.B_NO} className='btn btn-primary'>{board.B_TITLE}</Link>
                </td>
                <td>{board.B_WRITER}</td>
            </tr>
        </>
    )
}

export default BoardRow