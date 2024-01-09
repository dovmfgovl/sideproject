package com.example.demo.di;

public class Quack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("꽥~꽥");
	}
}