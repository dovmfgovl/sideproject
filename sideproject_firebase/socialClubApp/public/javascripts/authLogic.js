import {
  signInWithEmailAndPassword,
  getAuth,
  GoogleAuthProvider,
  signInWithPopup
} from "https://www.gstatic.com/firebasejs/9.22.1/firebase-auth.js";

class AuthLogic{
  constructor(){
    this.auth = getAuth();
    this.googleProvider = new GoogleAuthProvider();
  }
  getUserAuth = ()=>{
    return this.auth;
  }
  getGoogleAuthProvider = ()=>{
    return this.googleProvider;
  }
}
export default AuthLogic;

export const loginGoogle = (auth, googleProvider) => {
  console.log('loginGoogle호출 성공');
  console.log(googleProvider);
  return new Promise((resolve, reject) => {
    signInWithPopup(auth, googleProvider)
    .then((result) => {
      console.log(result);//object Object - 안보임 - uid, displayName-realname, email
      console.log(JSON.stringify(result));
      const user = result.user;
      localStorage.setItem("uid",user.uid)
      localStorage.setItem("displayName",user.displayName)
      localStorage.setItem("email",user.email)
      resolve(user)
    }).catch((error) => reject(error));
  });
};

export const loginEmail = (auth, email, password) =>{
  signInWithEmailAndPassword(auth, email, password)
  .then((response) => {
  const user = response.user;
  //JSON.stringify(user)->string 변환됨 - 글자를 알아볼 수 있다. 
  console.log(`user ===> ${JSON.stringify(user)}`);//[object,Object] - JSON.parse(): Json->array로 변경
  console.log(`uid===>${user.uid}`);
  console.log(`email===>${user.email}`);
  localStorage.setItem("uid",`${user.uid}`);//로컬브라우저 저장소에 담아줌
  localStorage.setItem("email",`${user.email}`);
  location.href="/";

  })
  .catch((error) => {
  const errorCode = error.code;
  const errorMessage = error.message;
  });
}