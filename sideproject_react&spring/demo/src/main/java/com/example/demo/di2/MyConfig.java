package com.example.demo.di2;
import org.springframework.context.annotation.Bean;
// xml 문서 대신 자바 클래스를 통해서 필요한 객체를 미리 등록해 줌
//   이러면 이른 객체 생성을 가져갈 수 있음 : 왜냐하면 서비스를 위해 필요한 클래스 설계는 개발자의 몫이니까
//   다시 말해 설계자에 따라 클래스 이름 다 달라져야 하니까. 그래서 스프링은 정할 수 없다
// @Configuration 붙여서 필요한 객체를 미리 선언해 둔다
// 이 때 "함께 사용"할 어노테이션이 Bean 어노테이션이다.
import org.springframework.context.annotation.Configuration;

@Configuration
// @Bean(빈=클래스) : 스프링에서 해당 클래스를 관리함. 즉, 스캔 - 클래스 정보 수집
// ApplicationContext 컨테이너는 이렇게 등록된 빈을 관리해 줌
public class MyConfig {
  @Bean public TestController testController(){ // 메소드를 통해 객체를 주입받는 방법(권장방식임)
    return new TestController();
  }
  @Bean public TestLogic testLogic(){
    return new TestLogic();
  }
  @Bean public TestDao testDao(){
    return new TestDao();
  }
}
