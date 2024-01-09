import React, {useEffect, useState} from "react";
import {useParams} from "react-router";
import {useNavigate} from "react-router-dom";
import {boardDeleteDB, boardDetailDB} from "../../service/dbLogic_board";
import Header from "../include/Header";
import Bottom from "../include/Bottom";

const BoardDetail = () => {
    const {id} = useParams();
    const navigate = useNavigate();
    const [board, setBoard] = useState();

    const boards = {
        b_no : id
    }
    const getBoard = async () => {
        console.log("getBoard");
        const res = await boardDetailDB(boards);
        setBoard(res.data[0]);
        console.log(res.data[0]);
    }

    useEffect(() => {
        getBoard();
    }, []);

    const moveToUpdate = () => {
        navigate('/board/update/'+id);
    }

    const boardDelete = async () => {
        if (window.confirm('게시글을 삭제하시겠습니까?')) {
            const res = await boardDeleteDB(boards);
            console.log(res.data);
            if (res.status === 200) {
                navigate('/board');
                console.log("삭제 성공");
            } else {
                console.error('삭제에 실패했습니다.');
            }
        }
    }

    const comeBackHome = () => {
        navigate('/board');
    }

    return (
        <>
            <Header />
            <h2 align="center" style={{marginTop:100, marginBottom:100}}>게시글 상세정보</h2>

            <div className="post-view-wrapper">
                {board ? (
                    <>
                        <div className="post-view-row">
                            <label>게시글 번호</label>
                            <label>{board.B_NO}</label>
                        </div>
                        <div className="post-view-row">
                            <label>제목</label>
                            <label>{board.B_TITLE}</label>
                        </div>
                        <div className="post-view-row">
                            <label>작성자</label>
                            <label>{board.B_WRITER}</label>
                        </div>
                        <div className="post-view-row">
                            <label>내용</label>
                            <div>{board.B_CONTENT }</div>
                        </div>
                    </>
                ) : (
                    <p>해당 게시글을 찾을 수 없습니다.</p>
                )}
                <button className="post-view-go-list-btn" onClick={moveToUpdate}>
                    수정
                </button>
                <button className="post-view-go-list-btn" onClick={boardDelete}>
                    삭제
                </button>
                <button className="post-view-go-list-btn" onClick={comeBackHome}>
                    목록
                </button>
            </div>
            <Bottom />
        </>
    );}

export default BoardDetail