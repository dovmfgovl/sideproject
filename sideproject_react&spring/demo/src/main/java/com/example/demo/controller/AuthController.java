package com.example.demo.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/authc/*")
public class AuthController {
  Logger logger = LoggerFactory.getLogger(AuthController.class);
  // 화면이지만 스프링을 경유하도록 한다.
  // 왜냐하면 뭔가 인터셉트하여 전처리 해야하는 경우라면 단순한 화면을 부르는 과정도 스프링을 태운다.
  @GetMapping("loginForm")
  public String loginForm(){
    logger.info("loginForm");
    return "redirect:/authc/loginForm.jsp"; // 배포위치가 webapp이다.
  }
  // jsp 페이지를 요청하여 처리해도 결과는 같을 것이다.
  // 다만 스프링 컨테이너를 굳이 경유하도록 학습해 본다 - (B to C 서비스 형태)
  @GetMapping("logout")
  public String logout(HttpSession session){
    logger.info("logout");
    // 서버측의 cache메모리에 저장된 모든 세션값을 지운다
    session.invalidate();
    return "redirect:/index.jsp"; // 배포위치가 webapp이다.
  }
  @PostMapping("login")
  public String login(String mem_email, String mem_pw2, boolean remember, HttpServletResponse res) // 쿠키에 이메일을 기억하기 위해 res 사용함
  throws Exception
  { 
    logger.info(mem_email+", "+mem_pw2+", "+remember);
    // 민약 로그인이 실패했다면
    if (!loginCheck(mem_email, mem_pw2)) {
      String msg = URLEncoder.encode("이메일 또는 비번이 일치하지 않습니다.", "utf-8");
      return "redirect:/loginForm?msg="+msg;
    }
    if (remember) {
      // 쿠키 생성하기
      Cookie c = new Cookie("mem_email", mem_email);
      // 현재 바라보는 곳(loginForm.jsp가 있는 위치) : auth
      // 성공하면 http://localhost:8000/index.jsp
      // 이런 경우 쿠키가 생성되는 도메인과 달라서 반드시 setPath 추가할 것
      c.setPath("/");
      c.setMaxAge(60*60);
      res.addCookie(c);
    } else {
      // 쿠키 삭제하기
      Cookie c = new Cookie("mem_email", "");
      c.setPath("/");
      c.setMaxAge(0);
      res.addCookie(c);
    }
    // 기억하기를 체크하면 쿠키에 이메일을 기억해줄게(브라우저)
    return "redirect:/index.jsp"; // 배포위치가 webapp이다.
  }
  private boolean loginCheck(String mem_email, String mem_pw) {
    return "kiwi@hot.com".equals(mem_email) && "123".equals(mem_pw);
  }
}
