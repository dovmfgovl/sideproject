package com.example.demo.dao;

import java.util.Map;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.MemberController;
@Service
public class MemberDao {
  Logger logger = LoggerFactory.getLogger(MemberDao.class);
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;

  // 한 번에 여러 건을 등록하는 실습 예제 입니다.
  public int fileInsert(List<Map<String, Object>> picList){
    int result = 0;
    result = sqlSessionTemplate.insert("fileInsert", picList);
    logger.info(String.valueOf(result)); // 등록요청 결과 출력해보기
    return result;
  }

  public int memberInsert(Map<String, Object> pmap) {
    int result = 0;
    // 관전포인트 : 시퀀스로 채번된 번호를 파라미터 변수인 map에 담겨 있는지 확인하기
    result = sqlSessionTemplate.insert("memberInsert", pmap);
    // mem_no는 화면에서 입력 받는 값이 아님
    // 오라클에서는 자동채번 해주는 시퀀스 오브젝트가 제공된다
    // 시퀀스는 에러가 발동하더라도 반드시 1 증가된 값이 채번된다
    // 문제제기 ) 채번된 숫자를 유지해야 한다 - 왜냐하면 테이블이 board_master테이블에 사용된 번호를
    //  첨부파일이 등록되는 board_sub테이블에 한 번 더 사용 해야한다 - 동일한 번호를 사용해야 된다
    // 결과) insert메소드의 리턴타입은 성공유무를 반환하는 1 또는 0만 반환되는 함수이다
    //  그런데 우리는 오라클 서버에서 채번된 숫자를 자바로 꺼내와야 한다 - 파라미터에 담아준다 - myBatis
    //  이 옵션이 useGeneratedKeys가 true여야 한다 - 디폴트는 false임. 꺼져있음
    logger.info(pmap.get("mem_no").toString()); // 테스트 하는 목적이 얘
    return result; // 1이면 입력 성공, 0이면 실패
  }

  public List<Map<String, Object>> memberList(Map<String, Object> rmap) {
    List<Map<String, Object>> mList = null;
    mList = sqlSessionTemplate.selectList("memberList", rmap);
    return mList;
  }
  public List<Map<String, Object>> commentList(Map<String, Object> rmap) {
    List<Map<String, Object>> cList = null;
    cList = sqlSessionTemplate.selectList("commentList", rmap);
    return cList;
  }

}
