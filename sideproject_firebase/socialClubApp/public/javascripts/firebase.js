import { initializeApp } from "https://www.gstatic.com/firebasejs/9.22.1/firebase-app.js";
//import { initializeApp } from "firebase/app"; //로컬에 설치되어 있을 때 사용하기
//type = module 기억 - 모듈지원 - 자바스크립트 쪼개기 가능함
//여기서 생성된 객체는 어떻게 사용되죠?
  const firebaseConfig = {
    apiKey: "AIzaSyBYazfldnXtDlnlQ74_GPBff-aDNNqLaKY",
    authDomain: "clubboard-baddb.firebaseapp.com",
    projectId: "clubboard-baddb",
    storageBucket: "clubboard-baddb.appspot.com",
    messagingSenderId: "4700559554",
    appId: "1:4700559554:web:87bc784eb439462d7cc35b"
  };

  // Initialize Firebase
  export const app = initializeApp(firebaseConfig);