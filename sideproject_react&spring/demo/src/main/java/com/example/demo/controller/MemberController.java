package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.logic.MemberLogic;
import org.springframework.web.multipart.MultipartFile;

// 굳이 해당 클래스의 인스턴스 이름을 바꾸지 않는다 - 클래스 관계 - 생성자처리 - 이름이 다르다 - NullPointerException - 객체 주입 실패
// MemberController memberController = new MemberController();
//  인스턴스 이름을 마음대로 작명하면 스프링은 찾지 못한다 - 줄여쓰면 안 됨
//  인스턴스의 fullname이 사용되는 것이 디폴트라서 줄여쓰면 안 됨
//  예전에는 xml기반으로 객체주입처리) <bean id="memberController" class="com.example.demo.controller.MemberController"/>
@Controller("memberController") // p.928
@RequestMapping("/member/*") // @RequestMapping은 @Controller 있을 때 사용가능 함
public class MemberController {
  Logger logger = LoggerFactory.getLogger(MemberController.class);
  // 클래스의 관계 : @Autowired
  @Autowired
  private MemberLogic memberLogic;
  @GetMapping("fileInsert")
  public String fileInsert(){
    memberLogic.fileInsert();
    return "redirect:/index.jsp";
  }
  /************************************************************************************
    제목: 회원 가입 구현
    작성자: 이순신
    작성일자: 2023년 12월 29일
    @param : Map
    @return : int(1:입력성공, 0: 입력실패)
    참조 : 화면 정의서를 참고하여 구현해주세요
    개발패턴 : jsp - action(insert) - action(select) - jsp
    테이블 : 1:n관계에 있으면서 2개 이상의 테이블에 insert처리할 때 - 업무적인 depth 깊다

    INSERT INTO member231228(mem_no, mem_id, mem_pw, mem_email
                          , mem_name, zipcode, address, gubun)
    VALUES(seq_member231228_no.NEXTVAL, 'kiwi', '123', 'kiwi@hot.com'
                                  , '키위', '12345', '서울시 마포구 공덕동', '0');
  **************************************************************************************/
  @PostMapping("memberInsert")
  // 첨부파일 추가
  public String memberInsert(HttpServletRequest req, @RequestParam("mem_picture") MultipartFile mem_picture, @RequestParam Map<String, Object> pmap){
    logger.info("memberInsert");
    logger.info(mem_picture.toString());
    int result = -1;
    String path = "";
    String savePath = req.getSession().getServletContext().getRealPath("/pds");
    String memPicture = null;
    if(mem_picture != null && mem_picture.toString().length()>2){
        memPicture = mem_picture.getOriginalFilename();
    }
    logger.info("memPicture" + memPicture);
    // 첨부파일이 존재할 때 추가될 코드 : 첨부파일이 없다면 실행되지 않는 구간이다
    if(mem_picture != null){
        logger.info("mem_picture이 null이 아닐 때");
        String fullPath = savePath + "\\" + memPicture;
        // 파일이름을 객체로 만들어주는 클래스이다 : 파일이름만 생성됨. 내용은 미포함
        File file = new File(fullPath);
        try {
            byte[] bytes = mem_picture.getBytes();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(bytes); // 이름만 생성된 파일에 내룡을 쓰기 처리
            bos.close();
            long size = file.length();
            double d_size = Math.floor(size/(1024.0));
            pmap.put("mem_picture", memPicture);
            //pmap.put("file_size", d_size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // insert here
    result = memberLogic.memberInsert(pmap);

    if (result == 1) {
      path = "redirect:memberList";
    } else if (result == 0) {
      path = "redirect:memberError.jsp";
    }
    return path;
  }/////////////////// end of memberInsert /////////////////////

  /************************************************************************************
   제목: 회원 정보 수정 구현
   작성자: 이순신
   작성일자: 2024년 1월 3일
   @param : Map
   @return : int(1:수정성공, 0: 수정실패)
   참조 : 화면 정의서를 참고하여 구현해주세요
   개발패턴 : jsp - action(update) - action(select) - jsp
   테이블 : 1:n관계에 있으면서 2개 이상의 테이블에 insert처리할 때 - 업무적인 depth 깊다

   // 쿼리문을 보고 리턴타입과 파라미터를 결정할 수 있다
   UPDATE member231228
     set mem_pw =:pw
         , mem_name =:NAME
         , zipcode =:zipcode
         , address =:address
         , mem_email =:email
     WHERE mem_no =:NO;
   목업(Mock-up) or JUnit
   POST방식
   - 바이너리타입을 전송해야 한다면 form-data 선택해서 테스트 해 볼 것
    <form method="post" enctype="multipart/form-data">
   - 바이너리타입은 제외일 때
    application/x-www-form-urlencoded : 카카오로그인 - 토큰사용. Oauth2 사용 시
    <form method="post">
   - 비동기통신으로 처리해야 할 때 (부분갱신 처리하는 방법)
       const param = {};
       "name" = "홍길동";
       "tel" = "010-1234-5678";
       "address" = "율도국";

       //ajax 호출
       $.ajax({
       //요청 서비스 url 지정
       url : "/member/memberInsert",
       //메소드 타입 지정
       type : "POST",
       //요청 시 서버로 전달할 데이터 지정
       data : { // @RequestBody
           "name" = "홍길동";
           "tel" = "010-1234-5678";
           "address" = "율도국";
        },
       //요청 성공 시 동작할 콜백 함수 지정
       success : function(data) {
       console.log(data);
       //p요소에 서버로부터 응답받은 데이터 표출
       $("#resp").html(data);
       }
       });
   - 자바스크립트 코드에서 값을 넘겨받을 때 - Object. 자바스크립트
   {
       "mem_id":"kiwi",
       "mem_pw":"123",
       "mem_name":"키위"
   }

   **************************************************************************************/
  @PostMapping("memberUpdate")
  public String memberUpdate(@RequestParam Map<String, Object> pmap){
    logger.info("memberUpdate");
    int result = -1;
    String path = "";
    // insert here
    //result = memberLogic.memberUpdate(pmap);
    if (result == 1) {
      path = "redirect:memberList";
    } else if (result == 0) {
      path = "redirect:memberError.jsp";
    }
    return path;
  }/////////////////// end of memberUpdate /////////////////////

  /*******************************************************************************************
  제목 : 회원 목록 구현
  @param : 조건 검색에 사용될 컬럼명과 키워드 받아오기
  @return : n건이 조회되면 List<Map>, List<Member231228>
  Map과 XXXVO의 사용 기준이 있나요? : 타입, 계산식
  : Map이면 size에 제한 없다(G) - but, NumberFormatException, CastException 대상임(B)
  : XXXVO이면 컬럼을 매번 추가해줘야 하는 제한 있다(B) - but, 타입이 정해져 있어 형전환코드 필요없다(G)

  SELECT mem_no, mem_id, mem_pw, mem_name, zipcode, address
      , mem_email, gubun -- gubun : 상태관리대상이 되는 컬럼이다
    FROM member231228
  WHERE mem_id = :x
    AND address like '%'||:y||'%';
   데이터의 영속성을 활용한 결과 값들은 viewModel에 꼭 필요하다 : 클래스 이름 추가할 때
   화면(<table:로우+컬럼>)과 데이터는 밀접한 관계가 있다
   DefaultTableModel, JTable
   하이브리드웹 = 웹 + 앱(디바이스)
  ********************************************************************************************/
  @GetMapping("memberList")
  public String memberList(@RequestParam Map<String, Object> rmap, Model model){
    logger.info("memberList");
    List<Map<String, Object>> mList = null;
    mList = memberLogic.memberList(rmap);
    return "forward:./memberList.jsp";
  }

  /************************************************************************************
   제목: 회원 정보 삭제 구현
   작성자: 이순신
   작성일자: 2024년 1월 2일
   @param : int mem_no (number(5))
   @return : int(1:삭제성공, 0: 삭제실패)
   참조 : 화면 정의서를 참고하여 구현해주세요
   개발패턴 : jsp - action(delete) - action(select) - jsp
   테이블 : 1:n관계에 있으면서 2개 이상의 테이블에 insert처리할 때 - 업무적인 depth 깊다

   DELETE FROM member231228 WHERE mem_no = ?
   **************************************************************************************/
  @GetMapping("memberDelete")
  public String memberDelete(int mem_no){
    logger.info("memberDelete");
    int result = -1;
    String path = "";
    // insert here
    result = memberLogic.memberDelete(mem_no);
    if (result == 1) {
      path = "redirect:memberList";
    } else if (result == 0) {
      path = "redirect:memberError.jsp";
    }
    return path;
  }/////////////////// end of memberInsert /////////////////////
}
