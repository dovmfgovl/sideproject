<%@ page language="java"	contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8"%>
<%
  // 스크립틀릿(java 코드 작성해야하니 사용)
  // 쿠키는 같은 폴더에 있는 문서끼리만 공유됨 - 다른 폴더 사용 원하면 setPath
  Cookie c_name = new Cookie("ename", "smith");
  c_name.setMaxAge(60*30); // 60초의 유효 시간
  // path를 지정했을 경우 다른 도메인 path 모두 공유 불가함
  // path의 아래는 공유 가능함
  //c_name.setPath("/aaaa");
  response.addCookie(c_name); // C_name을 클라이언트 측에 내려보냄
  Cookie c_name2 = new Cookie("ename2", "scott");
  c_name2.setMaxAge(60*20); 
  // 생성 후에는 반드시 클라이언트측으로 내려야 함
  response.addCookie(c_name2);
%>
<!--  
  쿠키는 문자열
  쿠키는 시간 제어함
  쿠키는 한글처리 필요함
  쿠키는 로컬컴퓨터에 기록됨
  쿠키는 여러 개 생성 가능함
  쿠키를 생성하면 반드시 클라이언트 쪽으로 내려야 함 : addCookie()
  setPath를 추가로 정의하지 않았기 때문에 같은 경로에서 생성된 페이지만 쿠키 정보를 읽을 수 있다.
  실습결과)
  notice폴더 아래 cookieRead2.jsp에서는 쿠키값을 읽을 수 없다.
-->