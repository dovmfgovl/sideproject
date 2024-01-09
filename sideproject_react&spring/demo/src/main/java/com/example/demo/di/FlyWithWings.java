package com.example.demo.di;
//동일한 메소드를 호출했다고 하더라도 구현체 클래스에 따라서
//어쩔 땐 날고, 어떤 클래스일 땐 날지 못한다 - 다형성(폴리모피즘)
//다형성 전제조건: 선언부의 타입과 생성부의 타입이 무조건 다를 때
//ex) List = new ArrayList();
// 	List = new Vector();
//		List = new LinkedList();
// 이 클래스 이름 안 어디서도 오리의 느낌이 없다 - 비행기, 드론..에서도 사용하고 싶기 때문
//		결합도 낮은 코드를 작성하기 위해 인터페이스를 먼저 설계함
//		유연해야 한다. 왜냐하면 기능이 자꾸 바뀌고 다른 요구사항 추가되니까 - 확장성
public class FlyWithWings implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("나는 날고 있어요^^");
	}

}
