package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// XXXDao클래스는 MVC패턴에 영향을 주는 클래스는 아니다 - 디자인 패턴

@Service
//@Repository
// 모델계층 (비즈니스 로직 + 퍼시스턴스 계층)
public class NoticeDao { // 데이터 영속성 처리하는 계층(퍼시스턴스 계층)
  Logger logger = LoggerFactory.getLogger(NoticeDao.class);
  //DatabaseConfiguration에서 SqlSessionFactory를 갖고 있어 여기서 오라클 서버와 연결통로 만듦 
  // -> application.yml의 정보를 읽어들여 커넥션 정보를 쥐게 됨. 스프링 컨테이너가
  // -> notice.xml을 통해 mapper
  // 오라클 서버와도 연결해야 하니 여기서도 @Autowired를 추가!
  @Autowired
  SqlSessionTemplate sqlSessionTemplate = null; // POJO에서의 SqlSession-mybatis.jar -> 여기서는 mybatis-spring.jar이기 때문에 SqlSessionTemplate이라는 이름
  public List<Map<String, Object>> noticeList(Map<String, Object> pMap) { //gubun=n_title, keyword=휴관
    logger.info("noticeList");
    // JAVA -> MyBatis -> Oracle
    List<Map<String, Object>> list = sqlSessionTemplate.selectList("noticeList", pMap); //p.852
    logger.info(list.toString());
    return list;
  }
  public int noticeInsert(Map<String, Object> pMap) {
    logger.info("noticeInsert");
    int result = 0;
    result = sqlSessionTemplate.insert("noticeInsert", pMap);    
    return result;
  }
  public int noticeUpdate(Map<String, Object> pMap) {
    logger.info("noticeUpdate");
    int result = 0;
    try {
        //java와 myBatis 연계될때
        result = sqlSessionTemplate.update("noticeUpdate", pMap);
    } catch (Exception e) {
        logger.info(e.toString());
    }
    return result; // NullPointerException의 원인이거나 호출되는 페이지 경로가 달라지니 주의할 것
  }
  public int noticeDelete(int n_no) {
    logger.info("noticeDelete");
    int result = 0;
    try {
        result = sqlSessionTemplate.delete("noticeDelete", n_no);
    } catch (Exception e) {
        logger.info(e.toString());
    }
    return result;
  }
}
