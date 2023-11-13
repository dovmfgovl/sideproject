import { initializeApp } from "https://www.gstatic.com/firebasejs/9.22.1/firebase-app.js";
// https://firebase.google.com/docs/web/setup#available-libraries
// Your web app's Firebase configuration
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