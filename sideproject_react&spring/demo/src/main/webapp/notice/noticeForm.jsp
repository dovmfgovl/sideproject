<%@ page language="java"	contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8"%>
<form method="post" id="f_notice" action="/notice/noticeInsert">
  <a href="javascript:noticeInsert()">저장</a>
</form>
<script>
  const noticeInsert = () => {
    console.log("noticeInsert");
    document.querySelector("#f_notice").submit(); /* Post 방식이라 이렇게 해야 테스트 가능 */ 
  }
</script>