var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});
router.get('/myPage', function(req, res, next) {
  res.render('index', { title: 'myPage' , pageName: "users/myPage.ejs"});
});
router.get('/update', function(req, res, next) {
  res.render('index', { title: 'myPageUpdate' , pageName: "users/myPageUpdate.ejs"});
});

module.exports = router;
