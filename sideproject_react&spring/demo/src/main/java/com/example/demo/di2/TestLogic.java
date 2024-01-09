package com.example.demo.di2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogic {
  Logger logger = LoggerFactory.getLogger(TestLogic.class);
  TestDao testDao = null;
  public void setTestDao(TestDao testDao) {
    this.testDao = testDao;
  }
  public List<Map<String, Object>> testList() {
    System.out.println("TestLogic testList() 호출");
    List<Map<String, Object>> list = new ArrayList<>();
    list = testDao.testList();
    return list; // null이 들어오지 않고 size가 0이 들어오기 때문에 NullPointerException 발동 막을 수 있음
  }

}
