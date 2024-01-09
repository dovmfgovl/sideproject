<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  // 스크립틀릿의 위치는 html태그 위치와 의존관계 없다. 맨 아래에 있어도 상관없음. - 누가 처리하는 코드? : 톰캣 (html은 브라우저)
  // Session, Application, Request에서 꺼낼 때 type을 맞춰야함. 캐스팅
  String s_nickname = (String)session.getAttribute("nickname"); // 소스보기에서 확인할 수 없다. 서버사이드에서 처리완료했기 때문
  // 로그인을 하지 않았다면 null 출력됨. 로그인 했다면 카카오 계정에 등록된 네 이름 출력됨.
  // out.print(s_nickname);
%>    
<nav class="navbar navbar-expand-sm bg-light navbar-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="">여기내GYM</a>
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="/auth/loginForm.jsp">로그인</a>
        </li>
        <li class="nav-item">
        <!-- 
        확장자가 jsp이면 서블릿을 경유하지 않는다. - 목록에 보여줄 데이터가 없다?
        조회버튼 -> /notice/noticeList.gd요청하자 -  오라클 서버를 경유함
        확장자가 .gd이면 오라클 서버를 경유하니까 조회결과를 쥐고 있다.
        쥔다 - request.setAttribute() - 화면 출력하기
        -->
          <a class="nav-link active" href="/notice/noticeList">공지사항</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/boardList">게시판</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/member/memberList.jsp">회원관리</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">QnA게시판</a>
        </li>
      </ul>

<!--  
  1. 세션이 있는지 비교한다 - 스크립틀릿
  2. 로그아웃 디자인 코드를 삽입한다
  3. 이벤트 처리한다 - 자바스크립트가
    왜 3번이지? DOM을 먼저 스캔해야 접근 가능하기 때문
-->
<%
  // 세션값을 가지고 있니? - 세션이 null이 아닐때만 보여질 화면과 이벤트 처리코드들을 가둔다
  if(s_nickname != null){
%>
  <!-- 여기가 디자인 코드 삽입 위치 - insert here -->
  <form class="d-flex mb-2 mb-lg-0" role="search">
    <div class="me-auto mt-2 mb-lg-0"><%=s_nickname %>님.&nbsp;</div>
    <input type="button" class="btn btn-outline-success" onclick="logout()" value="로그아웃">
  </form>
  <script>
    const logout = () => {
      location.href="/authc/logout";
    }
  </script>
<%
  }
%>
    </div>
  </div>
</nav>