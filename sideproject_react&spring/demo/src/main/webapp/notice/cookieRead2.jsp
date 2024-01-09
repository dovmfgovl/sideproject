<%@ page language="java"	contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8"%>
<%
  Cookie[] cs = request.getCookies();
  for(int i=0; i<cs.length; i++){
    if("ename".equals(cs[i].getName())){
      out.print(cs[i].getValue());
    }
    if("ename2".equals(cs[i].getName())){
      out.print(cs[i].getValue());
    }
  }
%>
<!--  
  setPath를 추가로 정의하지 않았기 때문에 같은 경로에서 생성된 페이지만 쿠키 정보를 읽을 수 있다.
  실습결과)
  notice폴더 아래 cookieRead2.jsp에서는 쿠키값을 읽을 수 없다.
-->