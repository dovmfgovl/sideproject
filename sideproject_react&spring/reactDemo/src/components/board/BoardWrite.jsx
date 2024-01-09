import React, {useCallback, useState} from "react";
import {boardInsertDB} from "../../service/dbLogic_board";
import {BButton, ContainerDiv, FormDiv, HeaderDiv} from "../style/FormStyle";

const BoardWrite = () => {
    const [title, setTitle] = useState();
    const [writer, setWriter] = useState();
    const [content, setContent] = useState();
    const handleTitle = useCallback((value) => {
        setTitle(value);
    }, []);
    const handleWriter = useCallback((value) => {
        setWriter(value);
    }, []);
    const handleContent = useCallback((value) => {
        setContent(value);
    }, []);

    const boardInsert = async () => {
        console.log("boardInsert");
        const board = {
            b_title : title,
            b_writer : writer,
            b_content : content
        }
        console.log(board);
        const res = await boardInsertDB(board);
        console.log(res.data);
        window.location.replace(`/board`);
    }
    return (
        <>
            <ContainerDiv>
                <HeaderDiv>
                    <h3 style={{marginLeft:"10px"}}>게시판 글작성</h3>
                </HeaderDiv>
                <FormDiv>
                    <div style={{width:"100%", maxWidth:"2000px"}}>
                        <div style={{display: 'flex', justifyContent: 'space-between', marginBottom:'5px'}}>
                            <h3>제목</h3>
                            <BButton onClick={()=>{boardInsert()}}>글쓰기</BButton>
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

export default BoardWrite