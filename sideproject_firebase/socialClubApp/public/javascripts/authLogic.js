import axios from "axios";
import {getAuth
      , GithubAuthProvider
      , GoogleAuthProvider} from "https://www.gstatic.com/firebasejs/9.22.1/firebase-auth.js";
class AuthLogic {
  constructor(){
    this.auth = getAuth();
    this.gitProvider = new GithubAuthProvider();
    this.googleProvider = new GoogleAuthProvider();
  }
  getUserAuth = () => {
    return this.auth;
  }
  getGoogleAuthProvider = () => {
    return this.googleProvider;
  }
}//end of AuthLogic
export default AuthLogic;
// const params = {
//   uid: '12232222',
//   email: 'kaaa@hot.com'
// }

export const loginGoogle = (params) => {
  return new Promise((resolve, reject) => {
    signInWithEmailAndPassword(auth, googleProvider)
      .then((result) => {})
      .catch((error) => {
        const errorCode = error.code;
        const errorMessage = error.message;
      });
  });
}; //end of loginGoogle

export const loginKakao = (params) => {
  return new Promise((resolve, reject) => {
    try {
      const response = axios({
        method: "get",
        url: "카카오토큰을 받아올 URL주소 - 카카오개발자 센터 긁어옴",
        params: params
      });
      console.log(response);
      resolve(response)
    } catch (error) {
      reject(error);
    }
  });
};//end of loginKakao