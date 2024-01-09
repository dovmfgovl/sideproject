<%@ page language="java"	contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8"%>
<%
  // 스크립틀릿(java 코드 작성해야하니 사용)
  // 쿠키를 삭제하는 별도의 메소드는 없다
  // 쿠키를 생성하고 시간을 0으로 설정하면 파기된다 - 개발자센터에서 확인가능함
  Cookie c_name = new Cookie("ename", ""); // ""
  c_name.setMaxAge(0); // 0초를 주어 삭제
  response.addCookie(c_name); // 삭제하더라도 클라이언트 측에 내려보내야 함
  Cookie c_name2 = new Cookie("ename2", "");
  c_name2.setMaxAge(0); 
  response.addCookie(c_name2);
%>