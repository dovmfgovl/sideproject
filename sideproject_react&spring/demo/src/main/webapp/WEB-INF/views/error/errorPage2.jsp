<%--
  Created by IntelliJ IDEA.
  User: SeulGi
  Date: 2024-01-05
  Time: AM 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ErrorPage{WEB-INF}</title>
</head>
<body>
    <h2>에러가 발생했습니다.</h2>
    발생한 예외 : ${ex}<br> <%--GlobalException의 매개변수 Model에 추가된 정보를 읽어옴--%>
    예외메시지 : ${ex.message} <%--exception.getMessage(), e.toString()--%>
</body>
</html>
<%--
Expression Language의 약자
JSP 2.0에서 새롭게 추가된 스크립트 언어
기존의 Script tag의 표현식(<%= 정보 %>) tag에서 업그레이드된 버전 ( ${ 정보 } )
[ 주요 특징 ]
1) JSP 속성영역 (request,  response, session, application) 저장된 속성 객체의 property를 출력한다
2) 리터럴 데이터, 다양한 연산결과 출력이 가능하다
3) JSTL과 연동이 가능하다
--%>
