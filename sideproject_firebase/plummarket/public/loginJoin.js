// 초기에는 회원가입 폼을 감추기
$('#register-form').hide();

// 로그인 버튼 클릭 or Enter 키 눌렀을 때 실행되는 함수
$('#login-btn, #pw-login').on('click keypress', (e) => {
  if(e.type === 'click' || (e.type === 'keypress' && e.keyCode === 13)){
      // 로그인 처리
      let email = $('#email-login').val();
      let password = $('#pw-login').val();
    
      // Firebase 인증 - 로그인
      firebase.auth().signInWithEmailAndPassword(email, password)
        .then((result) => {
          console.log('로그인 성공:', result.user);
          window.location.href = "/index.html";
        })
        .catch((error) => {
          console.error('로그인 실패:', error.message);
        });
  }
});

// 로그인 화면 내 회원가입 링크 버튼 클릭 시 실행되는 함수
$('#register-btn').click(()=>{
    // 로그인 폼 숨기고 회원가입 폼 보이기
    $('#login-form').hide();
    $('#register-form').show();
});

// 회원가입 폼에서 '로그인으로 돌아가기' 버튼 클릭 시 실행되는 함수
$('#back-to-login-btn').click(()=>{
    // 회원가입 폼 숨기고 로그인 폼 보이기
    $('#register-form').hide();
    $('#login-form').show();
});

// 회원가입 버튼 클릭 시 실행되는 함수
$('#register-submit-btn').click(()=>{
    let email = $('#email-new').val();
    let password = $('#pw-new').val();

    // Firebase 인증 - 회원가입
    firebase.auth().createUserWithEmailAndPassword(email, password)
      .then((result) => {
        console.log('회원가입 성공:', result.user);
        // 회원가입 후 로그인 화면으로 돌아가기
        $('#register-form').hide();
        $('#login-form').show();
      })
      .catch((error) => {
        console.error('회원가입 실패:', error.message);
      });
});