package com.example.demo.di;

public class MuteQuack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("<<무음. 조용~~~>>");
	}

}
