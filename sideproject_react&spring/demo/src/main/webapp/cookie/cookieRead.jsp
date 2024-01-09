<%@ page language="java"	contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8"%>
<%
// 쿠키를 읽어올 땐 request객체를 사용함 - 로컬PC 관리됨
  Cookie[] cs = request.getCookies();
  for(int i=0; i<cs.length; i++){
    // 단 같은 이름이면 덮어짐
    if("ename".equals(cs[i].getName())){ // 쿠키 이름을 비교한다
      out.print(cs[i].getValue());
    }
    if("ename2".equals(cs[i].getName())){
      out.print(cs[i].getValue());
    }
  }
%>