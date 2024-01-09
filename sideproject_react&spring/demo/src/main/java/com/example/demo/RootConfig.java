package com.example.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
// 단 반드시 최초 생성했던 패키지 아래에서 생성할 것
@Configuration // @Bean : 라이프사이클 관리
@ComponentScan(basePackages = {"pk.gym.controller"})
@ComponentScan(basePackages = {"pk.gym.logic"})
@ComponentScan(basePackages = {"pk.gym.dao"})
//@ComponentScan(basePackages = {"pk.gym.config"})
public class RootConfig {
    // 아키텍쳐 설계 시 개발자가 추가한 패키지. 즉 스프링 프로젝트가 생성될 때
    // 만들어진 패키지 말고 다른 패키지 일때는 반드시 여기에 추가할 것
}
