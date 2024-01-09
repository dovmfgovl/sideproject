package com.example.demo.di2;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller // or @RestController : getBean()호출되지 않아도 미리 다 생성되어 있다
public class TestController {
  Logger logger = LoggerFactory.getLogger(TestController.class);
  //@Autowired
  TestLogic testLogic = null; // new를 붙이면 하드코딩 됨. 안 됨! //@Autowired를 붙이면 NullPointerException 되지 않고 이른 인스턴스화 됨
  // setter 객체 주입법 코드
  public void setTestLogic(TestLogic testLogic){
    this.testLogic = testLogic;
  }
  public String testList(){
    System.out.println("TestController testList() 호출");
    List<Map<String, Object>> list = null;
    list = testLogic.testList();
    logger.info("list : "+list.toString());
    return "OK";
  }
}
