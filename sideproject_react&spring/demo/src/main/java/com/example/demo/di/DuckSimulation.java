package com.example.demo.di;
//Spring 사전학습 - 결합도는 낮추고 독립성은 높여서 구현
//-> 추상메소드 중심, 인터페이스 중심의 코딩을 전개 해야함
public class DuckSimulation {

	public static void main(String[] args) {
		// 인스턴스화 3가지 유형
		//선언부와 생성부의 타입이 다를 수 있다 - 다형성을 위한 전제조건
		//생성부의 이름으로 결정됨
		Duck myDuck = new RubberDuck();
		myDuck.performFly(); //날지 못합니다
		myDuck.swimming();
		RubberDuck myDuck2 = new RubberDuck();
		myDuck2.swimming();	 //자손메소드 호출
		myDuck = new MallardDuck();
		myDuck.performFly(); //날고 있어요
		Duck himDuck = new WoodDuck();
		himDuck.performFly(); //나는 날지 못해요
		himDuck.performQuack(); //무음 조용~~~
	}

}
