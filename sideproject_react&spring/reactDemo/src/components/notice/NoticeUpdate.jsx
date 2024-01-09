import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router";
import Header from "../include/Header";
import Bottom from "../include/Bottom";
import { noticeUpdateDB, noticeDetailDB } from "../../service/dbLogic";

const NoticeUpdate = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [notice, setNotice] = useState({});

    const notices = {
        n_no : id
    }
    console.log(notices);

    const { N_TITLE, N_WRITER, N_CONTENT } = notice;

    // input 요소의 변화가 일어날 때마다 상태인 notice를 업데이트
    const onChange = (event) => {
        const { value, name } = event.target;
        setNotice({ ...notice, [name]: value });
    };

    useEffect(() => {
        getNotice();
    }, []);

    const getNotice = async () => {
        const res  = await noticeDetailDB(notices);
        setNotice(res.data);
        console.log(res.data);

        const selectNotice = res.data.find(notice => notice.N_NO === parseInt(id));
        if (selectNotice) {
            setNotice(selectNotice); // 선택된 공지사항 설정
        } else {
            console.error(`ID가 ${id}에 해당하는 공지사항을 찾을 수 없습니다.`);
        }
    }

    const updateNotice = async () => {
        try {
            // 공지사항 수정 로직 추가
            await noticeUpdateDB(notice);
            // 수정 후 처리 로직 추가
            navigate(`/notice/${id}`); // 수정된 공지사항의 상세 페이지로 이동
        } catch (error) {
            console.error("공지사항을 수정하는 중 오류가 발생했습니다.", error);
        }
    };

    const backToDetail = () => {
        // 상세 페이지로 돌아가는 함수
        navigate(`/notice/${id}`); // 수정 취소 후 상세 페이지로 이동
    }

    return (
        <>
            <Header />
            <div>
                <div>
                    <span>제목</span>
                    <input type="text" name="N_TITLE" value={N_TITLE || ''} onChange={onChange} />
                </div>
                <br />
                <div>
                    <span>작성자</span>
                    <input type="text" name="N_WRITER" value={N_WRITER || ''} readOnly={true} />
                </div>
                <br />
                <div>
                    <span>내용</span>
                    <textarea
                        name="N_CONTENT"
                        cols="30"
                        rows="10"
                        value={N_CONTENT || ''}
                        onChange={onChange}
                    ></textarea>
                </div>
                <br />
                <div>
                    <button onClick={updateNotice}>수정</button>
                    <button onClick={backToDetail}>취소</button>
                </div>
            </div>
            <Bottom />
        </>
    );
};

export default NoticeUpdate;
