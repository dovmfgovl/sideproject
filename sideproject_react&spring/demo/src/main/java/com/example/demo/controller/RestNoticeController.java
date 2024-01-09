package com.example.demo.controller;

import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.logic.NoticeLogic;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
// 마임타입 - text/plain경우 or application/json인 경우 사용함
// Front-End와 Back-End 완전 분리하는 클래스 설계/아키텍쳐 설계
// @RestController 필요성 공감
// Back-End가 무엇을 제공하는가? - 데이터셋(Data Set - JSON포맷)
// [{}] : [] array, List계열 - 한 개의 로우. 여러 컬럼 존재할 수 있다(제네릭)
//        {} VO, Map계열
// 유효범위 : 자바 로컬에는 없는 개념
// 액션태그 : xml문법 사용함 - maven - pom.xml
// <jsp:useBean id="myCar" class="com.XXX.Sonata" scope="page|request|session|application"/>
//  <title> -> <movie:title>, <book:title> -> movie - xml에서는 namespace가 있다.
//  <input type=text name="mem_id"> input태그의 name이 VO멤버이거나 Map 키가 된다
// 서버 단 : request.getParameter("mem_id"), ${param.mem_id} -> pageContext의 존재사용이 가능함
//  <input type=text name="mem_pw", ${param.mem_pw}
//  <input type=text name="mem_name", ${param.mem_name} 
@RestController // 화면없이 조회 결과를 문자열 포맷으로 처리할 때 사용 - @ResponseBody 대체로 제공됨
@RequestMapping("/notice/*") // 반복되는 업무이름은 공통으로 뺀다 : RequestMapping + Contoller = WebServlet
public class RestNoticeController {
  Logger logger = LoggerFactory.getLogger(RestNoticeController.class);
  @Autowired
  NoticeLogic noticeLogic = null;
  // get방식으로 사용자가 입력한 값을 받을 땐 : @RequestParam 사용
  // post방식을 받을 땐 : @RequestBody 사용 (리액트)
  // 컨트롤 계층의 리턴타입은 String
  // redirect:./XXXX.jsp, redirect:./XXXX - 또다른 메소드를 호출하는 것
  // forward:./XXXX.jsp(select일 때 .내가 바라보는 경로)
  //  주의) 메소드 안에서 forward방식으로 다른 메소드를 호출하는 것은 불가
  //  redirect -> forward 가능. forward -> forward 불가
  // Model은 사용할 필요 없다. 직접 문자열(text/plain)로 반환하거나 JSON(application/json)으로 반환함
  @GetMapping("jsonNoticeList") // 메소드마다 url매핑하는 어노테이션 제공됨 - 서블릿에는 없는 점
  public String jsonNoticeList(@RequestParam Map<String, Object> pMap, HttpServletRequest req){
    // Test url : http://localhost:8000/notice/jsonNoticeList?gubun=n_title&keyword=휴관
    logger.info(pMap.toString()); // n_title ro n_writer or n_content, keyword=휴관
    // logger.info(pMap.get("gubun").toString());
    // logger.info(req.getParameter("gubun").toString());
    // logger.info(pMap.get("keyword").toString());
    List<Map<String, Object>> list = null;
    list = noticeLogic.noticeList(pMap);
    // List, Map -> JSON 변경하기/ JSON -> List, Map/ JSON -> Array
    Gson g = new Gson(); // 오픈소스(해커뉴스, 유튜브, 날씨 API) API - JSON형식 데이터셋 다양하게 관찰
    String temp = g.toJson(list); // toJson() : 파라미터로 받은 List<Map<>>형태를 JSON형식으로 전환해 줌
    return temp;
  }
  // React는 JSON으로 문자열을 주고 받기 때문에 @RestController에서 @RequestBody를 사용함
  @PostMapping("noticeInsert2")
  public String noticeInsert2(@RequestBody Map<String, Object> pMap){
    logger.info("noticeInsert2");
    logger.info(pMap.toString());
    int result = 0;
    result = noticeLogic.noticeInsert(pMap);
    return String.valueOf(result); // 성공 : 1, 실패 : 0 반환
  }
  // @Controller를 사용할 때와 다르게(화면이 아닌) JSON형식의 데이터셋으로 내보내야 할 때
  // 꼭 React뿐 아니라 다른 솔루션이라더라도 데이터셋을 json을 사용하고 있다면 적용 가능한 공통코드임
  // 화면으로 내보내는게 아니라 즉시 JSON형식으로 내보내기 때문에 Model 필요없음
  @GetMapping("jsonNoticeDetail")
  public String noticeDetail(@RequestParam Map<String, Object> pMap){
    logger.info("jsonNoticeDetail");
    List<Map<String, Object>> nList = null; // List<Map<>> : [{}, {}, {}]
    nList = noticeLogic.noticeList(pMap);
    Gson g = new Gson();
    String temp = g.toJson(nList); // toJson() : 파라미터로 받은 List<Map<>>형태를 JSON형식으로 전환해 줌
    return temp;
  }
  @GetMapping("jsonNoticeDelete")
  public String jsonNoticeDelete(int n_no){
    logger.info("jsonNoticeDelete");
    int result = 0;
    result = noticeLogic.noticeDelete(n_no);
    Gson g = new Gson();
    String temp = g.toJson(result); // toJson() : 파라미터로 받은 List<Map<>>형태를 JSON형식으로 전환해 줌
    return temp;
  }
  @PostMapping("jsonNoticeUpdate")
  public String jsonNoticeUpdate(@RequestBody Map<String, Object> pMap){
    logger.info("jsonNoticeUpdate");
    logger.info(pMap.toString());
    int result = 0;
    result = noticeLogic.noticeUpdate(pMap);
    return String.valueOf(result); // 성공 : 1, 실패 : 0 반환
  }
}
