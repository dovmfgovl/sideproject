package com.example.demo.restful;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vo.MemberVO;
// React UI 사용할 때는 @RestController 사용 빈도가 더 높을 것
@RestController //화면없이 test 해보고 싶을 때
@RequestMapping("/http/*")
public class RestfulController {
  Logger logger = LoggerFactory.getLogger(RestController.class);
  // 테스트 방법
  // http://localhost:8000/http/get?mem_id=kiwi&mem_name=키위
  @GetMapping("get")
  public String getTest(MemberVO mVO){ // RequestParam 생략하고도 vo를 가져올 수 있는지 해 보는 과정..
    return "get요청 : "+ mVO.getMem_id()+", "+ mVO.getMem_name();
  }
  // URL요청이름 == 메소드이름 == 화면이름
  //  : 왜 세 가지를 맞춰야하나? - 디버깅으로 추적하려면 같은 이름인 게 찾기 수월하니까. 같은 이름이라 혼동할 수도 있지만 로그 라인번호, 시간으로 구분하면 됨
  // demo_member테이블이름 == VO이름 == 제네릭 맵 사용 시 키 값
  // 테스트 방법
  // http://localhost:8000/http/lombokTest?mem_no=3&mem_id=apple
  @GetMapping("lombokTest")
  public String lombokTest(MemberVO mVO){ 
    MemberVO mVO2 = MemberVO.builder().mem_id(mVO.getMem_id()).mem_no(mVO.getMem_no()).build(); // lombok의 builder 사용 시: MemberVO.java의 순서를 지키지 않아도 됨
    return "lombok테스트 : "+ mVO.getMem_id()+", "+ mVO.getMem_name().length()+"||"+ mVO2.getMem_no();
  }

  // @PostMapping("post")
  // public String postTest(MemberVO mVO){
  //   return "post요청 : "+ mVO.getMem_id()+", "+ mVO.getMem_name();
  // }
  // 또 다른 방법
  @PostMapping("post")
  public String postTest(@RequestBody MemberVO mVO){
    return "post요청 : "+ mVO.getMem_id()+", "+ mVO.getMem_name();
  }
  @PostMapping("post1")
  public String postTest1(@RequestParam Map<String, Object> pMap){
    return "post1요청 : "+ pMap.get("Mem_id")+", "+ pMap.get("Mem_name");
  }

  // http://localhost:8000/http/update/7
  @PutMapping("update/{id}")
  public MemberVO update(@PathVariable int id){
    logger.info(String.valueOf(id)); // 7
    MemberVO mVO = new MemberVO();
    mVO.setMem_id("nice");
    return mVO;
  }

  // http://localhost:8000/http/delete/7
  @DeleteMapping("delete/{no}")
  // delete from demo_member where no=7
  // @PathVariable 값은 상세조회, 삭제 시 pk값으로 사용됨
  public String delete(@PathVariable int no){
    logger.info(String.valueOf(no)); // 7
    return String.valueOf(no);
  }

}
/* 
  1. Get 요청(select시 사용)
  : 주소에 데이터를 담아 보낸다
  : 데이터 형태는 key=value

  2. Post, Put, Delete 요청
  : Body에 담아 보낸다
  : 데이터 형태는 json으로 통일
  : form 태그 요청은 get요청과 post요청만 가능함
  : put, delete는 자바스크립트로 요청함 - 테스트 번거로움

  템플릿 엔진, 리액트와 같은 추가적인 솔루션 이용하는 경우)
  자바스크립트로 요청해야 함(ajax, fetch, axios(리액트!)) - 비동기처리 지원 API
  자바스크립트는 기본적으로 동기가 defalut. 비동기를 위해선 추가 처리 필요함(setTimeout 등)
*/