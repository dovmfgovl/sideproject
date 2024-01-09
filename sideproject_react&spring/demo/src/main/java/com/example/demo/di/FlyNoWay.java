package com.example.demo.di;
//implements는 그 추상메소드를 재정의 하는 것이다
//메소드 선언만 되어 있으므로 오리 클래스가 되기 위한 명세서의 역할
public class FlyNoWay implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("나는 날 수 없습니다");
	}

}
