package com.example.demo.logic;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.NoticeDao;

@Service
public class NoticeLogic {
  Logger logger = LoggerFactory.getLogger(NoticeLogic.class);
  @Autowired
  NoticeDao noticeDao = null;
  // 조건검색 - select
  public List<Map<String, Object>> noticeList(Map<String, Object> pMap) {
    logger.info("noticeList");
    List<Map<String, Object>> list = new ArrayList<>();
    ///// @Service, @Autowired
    // noticeDao 변수는 NoticeLogic에서 선언만 되었다. new가 없다 : ApplicationContext에서 생성해 줌
    //  왜냐하면 NoticeLogic 앞에 @Service가 붙어있기 때문!
    //  이렇게 주입받은 객체의 인스턴스 변수로 NoticeDao에 선언된 noticeList 메소드를 호출하고 있다
    //  NoticeLogic과 NoticeDao의 연관관계에 대해 고민해 볼 것
    list = noticeDao.noticeList(pMap);
    return list;
  }
  // 등록 - insert
  public int noticeInsert(Map<String, Object> pMap) {
    logger.info("noticeInsert");
    int result = 0;
    result = noticeDao.noticeInsert(pMap);
    return result;
  }
  // 수정 - update
  public int noticeUpdate(Map<String, Object> pMap) {
    logger.info("noticeUpdate");
    int result = 0;
    result = noticeDao.noticeUpdate(pMap);
    return result;
  }
  // 삭제 -delete
  public int noticeDelete(int n_no) {
    logger.info("noticeDelete");
    int result = 0;
    result = noticeDao.noticeDelete(n_no);
    return result;
  }
  
  
}
