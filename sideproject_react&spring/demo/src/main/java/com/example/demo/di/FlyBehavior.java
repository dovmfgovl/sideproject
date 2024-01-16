package com.example.demo.di;
// 추상 - 미정이다. 결정되지 않았다. 모르겠다
//인터페이스는 추상클래스보다 더 추상적이어서 오직 추상메소드만 가짐
//abstract예약어를 생략할 수 있다.
//FlyBehavior 이름 안에서 오리에 대한 생각
//비행기도 헬리콥터도 모두 난다 - 상속으로 한다.
//그래서 나는 기능을 상속으로 재정의하지 않고 인터페이스로 처리하는 것이 클래스 사이의 결합도를 낮춰 단위테스트 가능하고 독립적인 기능을 구사할 수 있을 것이다
//추상클래스와 인터페이스 중심의 코딩을 전개하라
//구현체 클래스를 구현하기 전에 설계단계에서 명세서를 작성해보시오
//기능 확장이나 추가적인 클래스 구현 시에 도움이 됨(시간 절약, 진화, 개선, 발전...)
public interface FlyBehavior {
	//분명히 메소드 선언인데 세미콜론으로 끝남 - 추상메소드
	public void fly(); //대신 구현하는 FlyWithWings, FlyNoWay
}