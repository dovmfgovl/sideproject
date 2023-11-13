var express = require('express');
var router = express.Router();

router.get('/clubHome', function(req, res, next) {
  res.render('index', { title: 'clubHome', pageName:"clubHome.ejs"});
});
router.get('/board/list', function(req, res, next) { 
  res.render('index', { title: '글목록', pageName:"board/list.ejs" });
});
router.get('/board/write', function(req, res, next) { 
  res.render('index', { title: '글쓰기', pageName:"board/write.ejs" });
});
router.get('/board/read/:id', function(req, res, next) { 
  let id = req.params.id;
  res.render('index', { title: '상세보기', pageName:"board/read.ejs", id: id });
});
router.get('/board/update/:id', function(req, res, next) { 
  let id = req.params.id;
  res.render('index', { title: '글수정', pageName:"board/update.ejs", id: id });
});

module.exports = router;
