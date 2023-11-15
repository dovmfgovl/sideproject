var express = require('express'); //외부 프레임워크 가져올 때
var router = express.Router(); //페이지 전환

router.get('/', function(req, res, next) {
  res.render('index', { title: '소소모임', pageName:"home.ejs"});
});
router.get('/create', function(req, res, next) {
  res.render('index', { title: '클럽생성', pageName:"create.ejs"});
});
router.get('/clubList/:id', function(req, res, next) {
  let id = req.params.id
  res.render('index', { title: '클럽목록' , pageName: "clubList.ejs", id:id});
});
router.get('/clubMain/:category/:clubId', function(req, res, next) {
  let category = req.params.category
  let clubId = req.params.clubId
  res.render('index', { title: '클럽메인' , pageName: "clubMain.ejs", id:category, id2:clubId});
});
// router.get('/login', function(req, res, next) { //app.js -> path 라이브러리 __dirname, views
//   res.render('index', { title: '로그인', pageName:"auth/login.ejs" });
// });
// router.get('/board/list', function(req, res, next) { //app.js -> path 라이브러리 __dirname, views
//   res.render('index', { title: '글목록', pageName:"board/list.ejs" });
// });
// router.get('/board/write', function(req, res, next) { //app.js -> path 라이브러리 __dirname, views
//   res.render('index', { title: '글쓰기', pageName:"board/write.ejs" });
// });
// router.get('/board/update/:id', function(req, res, next) { //app.js -> path 라이브러리 __dirname, views
//   let id = req.params.id;
//   res.render('index', { title: '글수정', pageName:"board/update.ejs", id: id });
// });

module.exports = router;
