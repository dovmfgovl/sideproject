package com.example.demo.step1;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//스프링에서는 서블릿과 다르게 메소드마다 url맵핑이 가능함
import org.springframework.web.bind.annotation.GetMapping;

//Spring3.0에서는 @ResponseBody로 사용됨
@RestController // @Controller와 다르게 응답이 page가 아닌 text/plain이다. 또는 페이지 출력이 아닌 모든 경우(json 포함)에 사용 가능
// @Controller를 사용하면 메소드 이름이 페이지 이름이 된다
@RequestMapping("/step1/*")
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    // 스프링에서는 req.getParmeter를 쓰지 않고 파라미터 자리에 넣어주는 것 만으로도 담김
    // url - http://localhost:8000/step1/home?param=1
    // 스프링에서 클래스 = 빈 <bean id=myDuck class=MallardDuck>
    // 어떻게 이런 일이 가능한가?
    //  스프링에서 의존성 주입을 담당하니까 가능한 것
    // - 빈관리 : spring-context.jar -> ApplicationContext
    // 환경설정 - Spring-core.jar - 환경설정 - myBatis, Hikaricp 외부 라이브러리 : IoC
    //  라이브러리에는 없는 제어권을 스프링이 갖는다
    //  스프링을 사용하면 객체에 대한 라이프 사이클 관리가 빼앗긴다 - 감사
    // 개발자가 객체에 대한 관리, 책임(관심사) 없다(IoC)

    @GetMapping("home") // 메소드가 호출되는 매핑이름이다. home만 적어도 home 메소드가 자동 호출됨.
    public String home(String param) {
        logger.info("home");
        logger.info(param); // 1
        return "반환되는 문자열";
        // return "home";// WEB-INF/views/home.jsp
    }
    // test url - http://localhost:8000/step1/jsonTest
    @GetMapping("jsonTest") // 메소드가 호출되는 매핑이름이다.
    public String jsonTest() {
        logger.info("jsonTest");
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("deptno", 10);
        map.put("dname", "총무부");
        map.put("loc", "인천");
        list.add(map);
        logger.info(list.toString());
        Gson g = new Gson();
        String temp = g.toJson(list);
        return temp;
    }//end of jsonTest

}
/*
 * 컨트롤계층 갖는 특징 - 역할 - 가진것
 * 상속을 받지는 않았지만 req, res는 여전히(순수성-다른언어와 조합, 이종간에 조립) 사용이 가능하다
 * 입력(@RequestParam- HashMapBinder소개- 지원함) -> 처리 -> 출력
 * 사람은 처리만 해줄래 - ok
 * 컨트롤계층 이라면
 * 응답페이지 처리에 대한 책임(관심사)
 * 필요할 때 요청객체와 응답 객체를 사용할 수도 있다. - 없어진게 아니다
 * 메소드의 파라미터 갯수에 제약이 없다
 * 실제 처리는 하지 않는다 - 처리는 미룬다 - XXXLogic클래스가 담당함
 */