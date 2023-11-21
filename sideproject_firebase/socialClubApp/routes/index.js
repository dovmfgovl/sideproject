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
router.get('/login', function(req, res, next) { 
  res.render('index', { title: '로그인', pageName:"auth/login.ejs" });
});
router.get('/join', function(req, res, next) { 
  res.render('index', { title: '회원가입', pageName:"auth/join.ejs" });
});
router.get('/clubMain/:category/:clubId', function(req, res, next) {
  let category = req.params.category
  let clubId = req.params.clubId
  res.render('index', { title: '클럽메인' , pageName: "clubMain.ejs", id:category, id2:clubId});
});
router.get('/clubMain/:category/:clubId/write', function(req, res, next) { 
  let category = req.params.category
  let clubId = req.params.clubId
  res.render('index', { title: '글쓰기', pageName:"clubActivity/board/write.ejs", id:category, id2:clubId });
});
router.get('/clubMain/:category/:clubId/detail/:readId', function(req, res, next) { 
  let category = req.params.category
  let clubId = req.params.clubId
  let readId = req.params.readId
  res.render('index', { title: '글 상세보기', pageName:"clubActivity/board/read.ejs", id:category, id2:clubId, id3:readId });
});
router.get('/clubMain/:category/:clubId/update/:readId', function(req, res, next) {
  let category = req.params.category
  let clubId = req.params.clubId
  let readId = req.params.readId
  res.render('index', { title: '글수정', pageName:"clubActivity/board/update.ejs", id:category, id2:clubId, id3:readId });
});
router.get('/clubMain/:category/:clubId/album', function(req, res, next) { 
  let category = req.params.category
  let clubId = req.params.clubId
  let readId = req.params.readId
  res.render('index', { title: '앨범목록', pageName:"clubActivity/album/albumList.ejs", id:category, id2:clubId, id3:readId });
});
router.get('/clubMain/:category/:clubId/album/upload', function(req, res, next) { 
  let category = req.params.category
  let clubId = req.params.clubId
  res.render('index', { title: '사진업로드', pageName:"clubActivity/album/upload.ejs", id:category, id2:clubId });
});
router.get('/clubMain/:category/:clubId/comment', function(req, res, next) {
  let category = req.params.category
  let clubId = req.params.clubId
  res.render('index', { title: '코멘트' , pageName: "clubActivity/comment.ejs", id:category, id2:clubId});
});
router.get('/clubMain/:category/:clubId/schedule', function(req, res, next) {
  let category = req.params.category
  let clubId = req.params.clubId
  res.render('index', { title: '일정관리' , pageName: "clubActivity/schedule.ejs", id:category, id2:clubId});
});
router.get('/clubMain/:category/:clubId/info', function(req, res, next) {
  let category = req.params.category
  let clubId = req.params.clubId
  res.render('index', { title: '클럽정보' , pageName: "clubActivity/info.ejs", id:category, id2:clubId});
});

module.exports = router;
