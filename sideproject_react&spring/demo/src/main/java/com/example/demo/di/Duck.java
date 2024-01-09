package com.example.demo.di;

import java.io.FileReader;
import java.util.Properties;

//추상클래스
//추상클래스는 추상메소드와 일반메소드 모두를 가짐
//생성자도 가짐
//변수선언도 가능함
//인터페이스는 일반메소드는 못 가짐
//추상클래스와 인터페이스의 공통점: 둘 다 반드시 구현체 클래스가 있어야 함
//추상클래스의 구현체 일 땐 extends 사용
//인터페이스의 구현체 일 땐 implements 사용
public abstract class Duck {
	FlyBehavior flyBehavior = null; // 날다 구현체 클래스
	QuackBehavior quackBehavior = null; // 울음소리 구현	
	// 추상클래스에 정의된 생성자가 직접 호출되는 구조가 아니라 자손 클래스가 호출될 때 부모 생성자를 먼저 호출한다.
	// 메소드 오버라이딩 규칙을 적용할 수 있다 - 컴파일 타입에
	public Duck() {} // Duck myDuck = new MallardDuck();	
	public abstract void display(); // 메소드가 ; 끝나면 호출이다 - 메소드 오버라이딩 대상이다 - 반드시 구현 // 아직 결정되지 않은 추상 메소드
	public void performFly() { // A a = new A(); 하드코딩(직접적) - 코드 수정을 많이 해야하는 컨셉
		// 추상클래스이므로 기능에 직접 관여하지 않음(직접 관여하는 건 나무오리, 청둥오리...)
		flyBehavior.fly(); // 가이드 - 메소드 구현을 직접 하지 않으려고 이렇게 코딩함
	}
	public void performQuack() {
		quackBehavior.quack();
	}
	public void swimming() {
		System.out.println("모든 오리는 물 위에 뜬다."); // 이미 결정된 일반 메소드
	}
	  static Duck getDuck(int i){ // 객체를 제공하는 쪽
    //메소드로 객체를 제공하는 쪽이 변경할 코드가 적다!!
    return new RubberDuck();
  }
  static Duck getDuck(double d){ // 객체를 제공하는 쪽
    return new WoodDuck();
  }
  @SuppressWarnings("deprecation")
  static Object getObject(String key) throws Exception{
      Properties p = new Properties();
      // duckInfo.txt를 읽어서 Properties에 저장함
      p.load(new FileReader("duckInfo.txt"));
      // Class는 클래스 설계도를 얻어내는 클래스
      Class<?> clazz = Class.forName(p.getProperty(key)); // rubber, wood
      return clazz.newInstance();
  }
}
