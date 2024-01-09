package com.example.demo.controller;

import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.logic.NoticeLogic;

import jakarta.servlet.http.HttpServletRequest;
// 스프링에서는 URL매핑이 4번을 디폴트로 하고 있다
// 서블릿컨테이너와는 다르게 메소드마다 URL을 지정할 수 있다
// URL 요청 : 요청방식 - get, post(바이너리 처리 - 첨부파일 처리 가능함), put, delete - 전송방식 차이
// @Controller는 클래스 선언 앞에 온다 - 페이지 출력일 때 사용함
// 만일 @Controller가 있는 클래스에서 JSON포맷을 예외적으로 사용하고 싶다면 @ResponseBody를 사용
// @RestController도 @Controller와 같이 컨트롤 계층을 지원하는 어노테이션 : 처리 결과가 페이지가 아닌 경우 사용
// 프론트 계층(React.js사용)과 백엔드 계층 분리
// RequestMapping 위치는 클래스 이름 앞, 메소드 이름 앞에도 가능한데 최근에는 GetMapping과 PostMapping이 지원됨에 따라 
//    대체로 클래스 선언 앞에서만 주로 사용함
// @RequestParam 메소드의 파라미터 자리에 사용되는 어노테이션
// @Service는 서비스 계층(모델계층 - 처리 - 기능) : 비즈니스 로직 + 퍼시스턴스 계층
// DB 연동을 위한 클래스를 따로 설계 추천함 - myBatis, hibernate 라이브러리, 프레임워크 지원되므로
@Controller
@RequestMapping("/notice/*")
public class NoticeController {
  Logger logger = LoggerFactory.getLogger(NoticeController.class);
  @Autowired // 클래스와 클래스 사이 관계 연결. 필요한 객체를 주입시켜줌 - NullPointerException 발생하지 않고 NoticeLogic까지 연결시켜줌
  NoticeLogic noticeLogic = null;
  // 전체 조회 및 조건 검색일 때
  // SELECT * FROM notice WHERE gubun=? AND keyword=?
  // Spring에서는 더이상 서블릿이 아니더라도 단독으로 서비스를 제공할 수 있도록 발전됨(Model)
  // 서블릿 : 상속은 결합도가 높다 - 메소드 오버라이드 - 강제사항
  //  그런데 스프링은 결합도를 낮춘다 : 더이상 HttpServletRequeset나 HttpServletResponse를 사용하지 않는다.
  //  req.setAttribute(), req.getParameter()
  // 사용자로부터 입력받는 값을 읽어오기 : @RequestParam 지원. 따라서 req.getParameter() 필요없다 - req 없어도 됨.
  // Post방식에서 Body로 받아올 때 {이름:값, 이름1:값1} 유형이라면 @RequestBody 사용
  // 문제제기 ) 동일한 select(1건, n건) 인데 메소들르 굳이 나눠야 하나?
  @GetMapping("noticeList")
  public String noticeList(Model model, @RequestParam Map<String, Object> pMap){
    logger.info("noticeList");
    // logger.info(pMap.get("gubun").toString());
    // logger.info(req.getParameter("gubun").toString());
    // logger.info(pMap.get("keyword").toString());
    List<Map<String, Object>> nList = null; // List<Map<>> : [{}, {}, {}]
    nList = noticeLogic.noticeList(pMap);
    model.addAttribute("nList", nList);
    return "forward:noticeList.jsp"; // forward니까 webapp 아래에서 찾는다
  }
  // DB설계 시 : 화면에 보이지 않는 컬럼이 추가되었는지 꼭 확인할 것!
  // XXXVO.java 수정해야 함, XXX.xml 문서의 컬럼명을 추가해야 한다.
  //  화면에 드러나지 않은 컬럼은 거의 개발자에게만 필요한 정보임. 파라미터 값이 변경됨. XXXController, Logic, Dao, jsp 계속 수정해야 할 수도
  //  <input type="hidden" name=?>
  // 관계형태(1:1, 1:n, n:n)및 관계를 잘못 그린게 아니면 비교적 리스크는 적은 편

  // 공지사항 게시판 테이블 설계에 조회수가 포함되어 있다면?
  // 그래서 1건 조회가 발생할때 마다 조회수를 1씩 증가시켜야 하는 프로세스를 추가해야 한다면 
  // - NoticeLogic클래스에 상세보기를 하는 메소드에서 한 번은 select를 또 한 번은 조회수를 update해야 한다면?
  //    (update notice set n_hit = n_hit +1 where n_no=3)
  // 만일 별도의 UI솔루션을 사용하고 있다면 추가 클래스 설계가 필요하다 - RestNoticeController.java
  // 우리 화면을 html(jsp) or jstl과 el 섞어쓰기 or ReactJS(데이터셋을 JSON포맷으로)
  // 폭포수모델 / MVC패턴 기본 - MVVM 패턴
  @GetMapping("noticeDetail")
  public String noticeDetail(Model model, @RequestParam Map<String, Object> pMap){
    logger.info("noticeDetail");
    List<Map<String, Object>> nList = null; // List<Map<>> : [{}, {}, {}]
    nList = noticeLogic.noticeList(pMap);
    model.addAttribute("nList", nList);
    return "forward:noticeDetail.jsp"; // forward니까 webapp 아래에서 찾는다
  }
  // get방식만 단위테스트가 가능하다
  //insert into notice(n_no, n_title, n_content, n_writer) values(?,?,?,?)
  @PostMapping("noticeInsert") //Post방식 - Post는 단위 테스트하기 힘드니 Get으로 개발한 뒤, 마지막으로 git에 push하기 전에 Post로 바꾸는 게 좋다
  public String noticeInsert(@RequestParam Map<String, Object> pMap){
    logger.info("noticeInsert");
    // logger.info(pMap.get("n_title").toString());
    // logger.info(pMap.get("n_content").toString());
    int result = 0;
    String path = "";
    result = noticeLogic.noticeInsert(pMap);
    if(result == 1){ // 입력이 성공했을 때
      path = "redirect:noticeList"; // 목록으로 다시 가게하니까 ~List까지만 적음 - 화면을 호출하는게 아니라 url을 호출한다(9번 라인으로)
    } else { // 입력이 실패했을 때
      path = "redirect:noticeError.jsp";
    }
    return path;
  }
  // update notice set n_title=?, n_content=?, n_writer=? where n_no=?
  // PathVariable : 해쉬값을 넘겨서 처리하는 방법을 지원하는 어노테이션 - 보안, 한글처리도 신경써야 함. 따라서 @RequestParam을 쓰는 게...
  @GetMapping("noticeUpdate")
  public String noticeUpdate(@RequestParam Map<String, Object> pMap){
    //logger.info("noticeUpdate");
    int result = 0;
    String path = null;
    result = noticeLogic.noticeUpdate(pMap);
    if(result == 1){
      path = "redirect:noticeList";
    } else {
      path = "redirect:noticeError.jsp";
    }
    return path;
  }
  // delete from notice where n_no=?
  @GetMapping("noticeDelete")
  //n_no 식별자 하나만 있으면 되니까 이렇게 해도 무방
  public String noticeDelete(int n_no){
    //logger.info("noticeDelete");
    int result = 0;
    String path = null;
    result = noticeLogic.noticeDelete(n_no);
    if(result == 1){
      path = "redirect:noticeList";
    } else {
      path = "redirect:noticeError.jsp";
    }
    return path;
  }
}
