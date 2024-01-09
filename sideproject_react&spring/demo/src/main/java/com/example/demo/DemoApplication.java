package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;

@ServletComponentScan
// @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
@SpringBootApplication
public class DemoApplication {
	// IoC, DI, AOP : 면접 준비용. 코딩은 별개다
	// Spring환경설정, application.properties(Maven-xml기반), application.yml(Gradle-Android-빌드방식)
	// 프로젝트 생성하기 - 환경설정하기 연습 -> 시큐리티 코딩, 메이븐 수업 진행 - 최고의 면접준비는 프로젝트
	// BeanFactory, ApplicationContext -> lazy, early, 싱글톤패턴
	// 트랜잭션 처리 기술, 방법

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(DemoApplication.class, args);
    // 등록된 빈의 목록 - 미리 로딩되어 있는 객체 목록을 확인해보는 코드임 - 로그 확인 시 불편하여 주석처리함
    //String[] beanNames = ac.getBeanDefinitionNames(); // 스프링에 등록된 클래스 빈이름을 배열에 담음
    //Arrays.sort(beanNames); // 정렬하기
    // 배열 스트림으로 변환하여 목록 출력
    //Arrays // 배열에 있는 정보를 그대로 출력할 수 없어 stream으로 변환시킴
    //.stream(beanNames) // 스트림 변환
    //.forEach(System.out::println); // 빈 목록 출력 - 이른 인스턴스화 확인 코드
	}

}
/*
	가급적이면 코딩을 적게 한다 - 적게하지만 유지보수 좋음 - 다형성을 활용
 * DemoApplication에서 main 실행하는 것으로 서버 기동됨
 * Spring Boot - 3.1.6버전 - Gradle{빌드도구-배포}
 * Tomcat 10.1.16 - Dynamic Web Application 6.0버전 - javax패키지는 활용불가 - jakarta패키지 
 * 스프링 부트 플젝에서 서블릿 실습을 하려면 반드시 @ServletComponentScan 추가해야 함
 * @WebServlet("*.do")
 * 설정파일은 
 * 1) application.properties - Properties클래스 동일함
 * 2) application.yml - json형식 - 반복코드 생략함
 * 
 * server:
  port: 8000 포트번호 설정하기
  servlet:
    context-path: / - 루트패스 설정
    encoding:
      charset: UTF-8 - 한글 인코딩
      enabled: true
      force: true
spring:
  output:
    ansi:
      enabled: always - 로그 출력 옵션
  mvc:
    view:
      prefix: /WEB-INF/views/ - 출력설정 - ModelAndView -  ViewResolver
      suffix: .jsp
  servlet:
    multipart: - 첨부파일 설정
      enabled: true
 * 
 */