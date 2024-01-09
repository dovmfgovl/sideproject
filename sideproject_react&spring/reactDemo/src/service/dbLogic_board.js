import axios from "axios";
export const boardListDB = (board) => {
  return new Promise((resolve, reject) => {
    try {
      console.log(board);
      const response = axios({
        method: "get",
        url: process.env.REACT_APP_SPRING_IP + "board/jsonBoardList",
        params: board,
      });
      resolve(response);
    } catch (error) {
      reject(error);
    }
  });
};
export const boardDetailDB = (board) => {
  return new Promise((resolve, reject) => {
    try {
      console.log(board);
      const response = axios({
        method: "get",
        url: process.env.REACT_APP_SPRING_IP + "board/jsonBoardDetail",
        params: board,
      });
      resolve(response);
    } catch (error) {
      reject(error);
    }
  });
};
export const boardInsertDB = (board) => {
  return new Promise((resolve, reject) => {
    try {
      console.log(board);
      const response = axios({
        method: "post", //@RequestBody
        url: process.env.REACT_APP_SPRING_IP + "board/jsonBoardInsert",
        data: board, //post방식으로 전송시 반드시 data속성으로 파라미터 줄것
      });
      console.log(response);
      resolve(response);
    } catch (error) {
      reject(error);
    }
  });
};
export const boardUpdateDB = (board) => {
  console.log(board);
  return new Promise((resolve, reject) => {
    try {
      const response = axios({
        method: "post", //@RequestBody
        url: process.env.REACT_APP_SPRING_IP + "board/jsonBoardUpdate",
        data: board, //post방식으로 전송시 반드시 data속성으로 파라미터 줄것
      });
      console.log(response);
      resolve(response);
    } catch (error) {
      reject(error);
    }
  });
};

export const boardDeleteDB = (board) => {
  return new Promise((resolve, reject) => {
    try {
      const response = axios({
        method: "get",
        url: process.env.REACT_APP_SPRING_IP + "board/jsonBoardDelete",
        params: board,
      });
      resolve(response);
    } catch (error) {
      reject(error);
    }
  });
};