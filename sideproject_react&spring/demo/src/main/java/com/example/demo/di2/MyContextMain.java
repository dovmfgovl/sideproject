package com.example.demo.di2;
// 실습 제목 <ApplicationContext 무작정 따라해보기>
// 소개 목적: myBatic 같은 외부 라이브러리를 사용할 때 활용해라 - 공통코드 작성하기
//  예) DatabaseConfiguration, CorsConfiguration.java
public class MyContextMain {
  public static void main(String[] args) {
    MyContext mc = new MyContext(MyConfig.class);
    System.out.println(mc.map);
    TestController testController2 = new TestController(); // 스프링을 사용하는 것 아님. 하드 코딩 - 라이프사이클을 개발자가 관리
    System.out.println(testController2);
    TestController testController = (TestController)mc.getBean("testController"); // IoC : 스프링 컨테이너가 대신 객체 관리. byName
    TestLogic testLogic = (TestLogic)mc.getBean("testLogic"); // IoC : 스프링 컨테이너가 대신 객체 관리. byName
    TestDao testDao = (TestDao)mc.getBean(TestDao.class); // 클래스로 받을 수도 있다
    System.out.println(testController); // testController2와 주소번지 다름. 
    testController.setTestLogic(testLogic); // 관계있는 클래스에 객체 주입코드 - 이 코드 생략가능 하도록 해 주는 어노테이션 : @Autowired
    testLogic.setTestDao(testDao);
    testController.testList();
    // 같은 주소번지 출력 : 싱글톤패턴 디폴트 (이름, 타입으로 접근하면 주소번지 같다 - 복제본 만들지 않고 원본 공유)
    // 멀티스레드를 운영하여 한정된 자원을 여러 사용자가 누릴 수 있다.
    TestController testController3 = (TestController)mc.getBean(TestController.class); // IoC, 관리 받음. byType
    System.out.println(testController3); // testController와 주소번지 같음.
  }
}
