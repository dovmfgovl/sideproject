package com.example.demo.step1;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/user/*")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    // test url - http://localhost:8000/user/login?mem_id=kiwi&mem_pw=123
    // 쿼리스트링을 사용하여 값을 넘기는 건 GET 방식이다
    @GetMapping("login")
    public String login(HttpServletRequest req){
        String mem_id = req.getParameter("mem_id");
        String mem_pw = req.getParameter("mem_pw");
        logger.info(mem_id+", "+mem_pw); // kiwi, 123
        return "redirect:/index.jsp";
    }
    @GetMapping("login2")
    public String login2(@RequestParam("mem_id") String mem_id, @RequestParam("mem_pw") String mem_pw){
        logger.info(mem_id+", "+mem_pw); // kiwi, 123
        return "redirect:/index.jsp";
    }
    @GetMapping("login3")
    //public String login3(@RequestParam("mem_id") String mem_id, @RequestParam("mem_pw") String mem_pw){
    public String login3(String mem_id, String mem_pw){
        // test url - http://localhost:8000/user/login3 =====> mem_id=null
        // test url - http://localhost:8000/user/login3?mem_id =====> mem_id="", js연결되는 경우 주의할 것
        logger.info(mem_id+", "+mem_pw); // kiwi, 123
        return "redirect:/index.jsp";
    }
    @GetMapping("login4")
    //public String login3(@RequestParam("mem_id") String mem_id, @RequestParam("mem_pw") String mem_pw){
    public String login4(@RequestParam String mem_id, String mem_pw){
        // test url - http://localhost:8000/user/login4 =====> 400 Bad Request(@RequestParam 뒤 매개변수 없는 경우. required=true라서 에러(필수입력사항))
        // test url - http://localhost:8000/user/login4?mem_id =====> mem_id="", js연결되는 경우 주의할 것
        logger.info(mem_id+", "+mem_pw); // kiwi, 123
        return "redirect:/index.jsp";
    }
    @GetMapping("login5")
    public String login5(@RequestParam Map<String, Object> pmap){
        // test url - http://localhost:8000/user/login5 =====> 
        // test url - http://localhost:8000/user/login5?mem_id=kiwi&mem_pw=123&mem_name=이순신 =====> 
        logger.info(pmap.get("mem_id")+", "+pmap.get("mem_pw")+", "+pmap.get("mem_name")); // kiwi, 123
        return "redirect:/index.jsp";
    }
    
    // test url - http://localhost:8000/user/loginForm
    @GetMapping("loginForm")
    public String loginForm() {
        logger.info("loginForm");
        //<ViewResolver>
        //접두어 : /WEB-INF/views/ (마지막 슬래쉬까지 그어놓음)
        //접미어 : .jsp
        // -> WEB-INF/views/.jsp
        return "/user/loginForm"; //여기가 XXXX매핑 값
        // -> WEB-INF/views/user/loginForm.jsp

        //..(. 두 개)면 상대경로에서 상위 경로. .(. 한 개)면 현재 내가 바라보는 곳(/user/*)
        //POJO : upmu[0] - workname - @RequestMapping(/user/*) <- 요청 시 이미 알고 있음
        //      upmu[1] - 메소드이름, 화면이름이기도 하다
        //return "redirect:./loginForm.jsp"; //@RequestMapping 값을 쥐고 있기 때문에 .만 적어도 됨
    }
    // ModelAndView : WEB-INF 찾음, forward 유효범위, scope : request
    //    url은 그대로인데 하면은 바뀐다
    // 화면 이름을 생략하면 메소드이름이 화면이름이 된다 - 스프링이 그렇게 주입함
    @GetMapping("loginForm2")
    public ModelAndView loginForm2() {
        logger.info("loginForm2");
        //ModelAndView mav = new ModelAndView();
        //mav.setViewName("loginForm2");
        return new ModelAndView(); // WEB-INF/views/user/loginForm2.jsp
        //return mav;
    }
}
