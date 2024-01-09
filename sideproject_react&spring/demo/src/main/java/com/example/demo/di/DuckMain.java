package com.example.demo.di;

public class DuckMain {

  public static void main(String[] args) {
    // 고무오리 타입이 호출되도록 해보시오.
    Duck duck = Duck.getDuck(1);
    if(duck instanceof RubberDuck){
      System.out.println("RubberDuck");
    }
    Duck duck2 = Duck.getDuck(1.0);
    if(duck2 instanceof WoodDuck){
      System.out.println("WoodDuck");
    }
  }
}
/* 
  선언부 생성부
  객체를 사용하려는 쪽에서는 개발자가 수정해야 할 코드가 많아짐 - 문제
  : 왜냐하면 선언부와 생성부 모두 수정해야 함(생성자 오버로딩 - 전역변수 - 고유명사 - 모두 수정해야 함)
    : 여기에서 이 제어권을 외부에서 갖자(new를 쓰지마라) - 스프링 컨테이너 IoC
  <기존 방식의 문제점(상속받고 프레임워크 사용하지 않는 경우)>
  컴포넌트 간의 결합도가 높아서 컴포넌트 확장 및 재사용이 어려운 문제 발생함
  <IoC 사용 시>
  제어권이 컨테이너에게 넘어가 객체 생명주기를 컨테이너가 전담하게 됨(빼앗김 때문에 자유 억압. 대신 생명주기 관리라는 부담을 덜어줌)
  1. ApplicationContext : 싱글톤으로 미리 로딩함
  2. BeanFactory : 게으른. getBean 호출될 때까지 기다림

  MallardDuck myDuck = new MallardDuck();
  WoodDuck himDuck = new WoodDuck();
 */