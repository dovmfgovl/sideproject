package com.example.demo.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.MemberController;
import com.example.demo.dao.MemberDao;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberLogic {
  Logger logger = LoggerFactory.getLogger(MemberLogic.class);
  @Autowired
  private MemberDao memberDao = null;
  public int fileInsert(){
    int result = 0;
    List<Map<String, Object>> picList = new ArrayList<>();
    Map<String, Object> pmap = new HashMap<>();
    pmap.put("file_no", 4);
    pmap.put("file_name", "a.png");
    pmap.put("file_size", 1.5);
    picList.add(pmap);
    pmap = new HashMap<>();
    pmap.put("file_no", 5);
    pmap.put("file_name", "b.png");
    pmap.put("file_size", 3.5);
    picList.add(pmap);
    pmap = new HashMap<>();
    pmap.put("file_no", 6);
    pmap.put("file_name", "c.png");
    pmap.put("file_size", 3.5);
    picList.add(pmap);
    result = memberDao.fileInsert(picList);
    return result;
  }
  public int memberInsert(Map<String, Object> pmap) {
    logger.info("memberInsert");
    int result = memberDao.memberInsert(pmap);
    Map<String, Object> pmap2 = new HashMap<>();
    if (result == 1) {
      int mem_no = 0;
      if (pmap.containsKey("mem_no")) {
        mem_no = Integer.parseInt(pmap.get("mem_no").toString());
        System.out.println("mem_no ====> "+ mem_no);
        pmap2.put("mem_no", mem_no);
      }
      // 파일 추가 테이블에 insert하기
    //   memberDao.fileInsert(pmap); // mem_no
    //   fileDao.fileInsert(pmap2);
    }
    return result;
  }///////// end of 회원등록
  public int memberDelete(int mem_no) {
    logger.info("memberDelete");
    int result = 0;

    return result;
  }
  // Logic클래스를 꼭 구현해야 하나요? : 업무적인 복잡도가 높아질수록 필요해짐
  // 정규식 : java.util.
  // pointcut express : * com.example.demo.*Logic.cud*(..) - 간섭 - 자동 코드 추가함(일괄처리)
  //@Transactional
  public List<Map<String, Object>> memberList(Map<String, Object> rmap) {
    List<Map<String, Object>> mList = null;
    // before advice con.setAutoCommit(false);
    mList = memberDao.memberList(rmap);

    // after advice con.commit();
    //con.setAutoCommit(true);
    return mList;
  }
  public List<Map<String, Object>> memberDetail(Map<String, Object> rmap) {
    List<Map<String, Object>> mList = null;
    // before advice con.setAutoCommit(false);
    mList = memberDao.memberList(rmap);

/*    <댓글 처리 시 아래 코드 사용>
    List<Map<String, Object>> cList = null;
    cList = memberDao.commentList(rmap);
    Map<String, object> rmap = new HashMap<>();
    rmap.put(1, cList);
    mList.add(rmap);
    // [{1건}, {comments:[{1건}, {1건}, {1건}]}]*/

    // after advice con.commit();
    //con.setAutoCommit(true);
    return mList;
  }

//  public int memberUpdate(Map<String, Object> pmap) {
//    List<Map<String, Object>> mList = null;
//    mList = memberDao.memberList(pmap);
//    return mList;
//  }
}