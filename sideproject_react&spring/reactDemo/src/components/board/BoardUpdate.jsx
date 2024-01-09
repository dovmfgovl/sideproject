import React, {useEffect, useState} from "react";
import {useParams} from "react-router";
import {useNavigate} from "react-router-dom";
import Header from "../include/Header";
import Bottom from "../include/Bottom";
import {boardDetailDB, boardUpdateDB} from "../../service/dbLogic_board";

const BoardUpdate = () => {
    const {id, B_TITLE, B_WRITER, B_CONTENT} = useParams();
    const navigate = useNavigate();
    const [board, setBoard] =useState({});

    //const { B_TITLE, B_WRITER, B_CONTENT } = board;

    const onChange = (event) => {
        const {value, name} = event.target;
        setBoard({...board, [name]: value});
    }

    const boards = {
        b_no : id,
        b_title : B_TITLE,
        b_writer : B_WRITER,
        b_content : B_CONTENT
    }
    const getBoard = async () => {
        console.log("getBoard");
        const res = await boardDetailDB(boards);
        setBoard(res.data[0]);
        console.log(res.data[0]);
        console.log(res.b_title);
    }

    useEffect(() => {
        getBoard();
    }, []);

    const updateBoard = async () => {
        try {
            await boardUpdateDB(board);
            navigate(`/board/${id}`);
        } catch (e) {
            console.error("게시글을 수정하는 중 오류가 발생헀습니다.", e);
        }
    }

    const backToDetail = () => {
        navigate(`/board/${id}`);
    }

    return (
        <>
            <Header />
            <div>
                <div>
                    <span>제목</span>
                    <input type="text" name="b_title" value={b_title || ''} onChange={onChange} />
                </div>
                <br />
                <div>
                    <span>작성자</span>
                    <input type="text" name="b_writer" value={b_writer || ''} readOnly={true} />
                </div>
                <br />
                <div>
                    <span>내용</span>
                    <textarea
                        name="b_content"
                        cols="30"
                        rows="10"
                        value={b_content || ''}
                        onChange={onChange}
                    ></textarea>
                </div>
                <br />
                <div>
                    <button onClick={updateBoard}>수정</button>
                    <button onClick={backToDetail}>취소</button>
                </div>
            </div>
            <Bottom />
        </>
    );

}

export default BoardUpdate;