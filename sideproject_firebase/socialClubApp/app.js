/*
웹 애플리케이션을 위한 기본적인 설정을 가지고 있다
모듈을 로딩하고 템플릿 엔진을 설정하여 라우터 설정함
파일 상단에는 사용할 모듈을 로딩하는 코드가 작성됨
외부 모듈을 해당 파일에서 사용하고 싶다면 require() 함수를 호출해야 함
자바의 입장에서 보면 - Servlet, JSP
그 중에 뭐? app.use(req,res,next)
app.get(req,res,next)
app.post(req,res,next)
*/
var createError = require('http-errors');
var express = require('express'); 
var path = require('path'); //물리적인 경로 때문에 쓰는 라이브러리
var cookieParser = require('cookie-parser');
var logger = require('morgan');
// 라우트 코드를 로딩하는 코드임. require()함수를 사용하며
//로딩한 라우트 함수들을 지정된 변수로 사용할 수 있게 해 줌
var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
//express 객체를 app변수로 선언함
var app = express();

// view engine setup
//익스프레스에서 사용할 템플릿 엔진을 설정하는 코드임
//__dirname(별다른 의미가 있는 것은 아님. 없을 문자를 적은 것)은 현재 디렉토리를 의미하며 path.join() 함수는 경로를 연결하는 기능을 함
app.set('views', path.join(__dirname, 'views')); //views를 물리적인 위치로 하겠다
app.set('view engine', 'ejs'); //템플릿 엔진의 종류(태그 안에 데이터를 심음 - for문 - 자스태그섞어쓰기를 하게 되는데)로 ejs를 쓰겠다는 선언
//파라미터에 지정된 인자를 실행하는 함수임
//여기서는 각각의 모듈을 사용하도록 설정함
app.use(logger('dev'));
app.use(express.json()); //express에 보내는 정보는 json형식으로 보내주겠다
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
//정적페이지의 물리적인 위치를 설정함
//static을 붙여서 정적으로 처리함
app.use(express.static(path.join(__dirname, 'public'))); //퍼블릭에 관리하겠다
//라우트를 설정하는 코드임. 여기서 라우트는 url경로의 뒷부분을 의미함
//'/'와 관련된 라우트는 routes 폴더의 index 파일에 설정된 라우트 함수를 통해 처리됨
app.use('/', indexRouter); // '/' 인증받지 않은 사용자도 쓰게 하겠다
//'/users'와 관련된 라우트는 users 파일에 작성된 라우트 함수를 통해 처리됨 - MyPage, 대시보드, 장바구니
app.use('/users', usersRouter); //users.js

// catch 404 and forward to error handler
// use함수는 get방식, post방식 구분없이 사용
// use함수는 왜 사용하지? 요청객체와 응답객체를 주입해주니까..
// 요청 - 사용자가 입력한 값을 서버측에서 요청할 수 있다.
// 응답 - 앞에 요청에 대한 처리 후 응답페이지의 이동이나 출력결과를 text or json형식 출력을 받아낸다.
// 내장객체이고 express가 자동 주입해줌 - dependency injection - 클래스와 클래스, 라이브러리와 라이브러리
// next - 다음 미들웨어로 이동할 때 꼭 필요하다
app.use(function(req, res, next) { //콜백함수 - system(express)에서 호출해준다. //rep: 요청, res: 응답, next: 다음 app.use로 넘어가게 함(미들웨어)
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500); //실행오류
  res.render('error'); //render: 화면에 그려달라 
  //res.send(msg);
  //res.render(html);
});

module.exports = app;
