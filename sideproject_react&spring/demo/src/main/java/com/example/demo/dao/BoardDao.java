package com.example.demo.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

// 빈 컨테이너 : BeanFactory, ApplicationContext(NullPointerException 발동하지 않도록 미리 해 둘게)
//      ApplicationContext의 부모클래스가 BeanFactory이다. 그래서 아들이 더 많은 것을 쥐고 있다(API-field method)
// Persistance 계층
// myBatis-spring.jar
@Repository
public class BoardDao {
    Logger logger = LoggerFactory.getLogger(BoardDao.class);
    // 주의사항 : 스프링이 ComponentScan(com.example.demo 모든 클래스 - 하위 폴더에 있는 것 포함)
    // BoardController -> BoardLogic -> BoardDao {여기까지가 자바 - mybatis-spring 지원하는 자바} -> SqlSessionTemplate
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate; // mybatis-spring.jar가 지원하는 자바클래스임

    public List<Map<String, Object>> boardList(Map<String, Object> pMap) { //gubun=n_title, keyword=휴관
        logger.info("boardList");
        // JAVA -> MyBatis -> Oracle
        List<Map<String, Object>> list = sqlSessionTemplate.selectList("boardList", pMap); //p.852
        logger.info(list.toString());
        return list;
    }

    public int hitCount(Map<String, Object> pMap) {
        logger.info("hitCount");
        int result = 0;
        result = sqlSessionTemplate.update("hitCount", pMap);
        return result;
    }
    // int b_no = pMap.get("b_no"); // pMap 안에 b_no가 들어있다
    // int b_no = pMap.get("B_NO"); // 대소문자 주의! (boardList, boardDetail, board.xml - 규칙성 있게 정할 것)
    public List<Map<String, Object>> commentList(Map<String, Object> pMap) {
        logger.info("commentList");
        // JAVA -> MyBatis -> Oracle
        List<Map<String, Object>> commentList = sqlSessionTemplate.selectList("commentList", pMap); //p.852
        logger.info(commentList.toString());
        return commentList;
    }

    // 댓글쓰기
    public int commentInsert(Map<String, Object> pMap) {
        logger.info("commentInsert");
        int result = 0;
        result = sqlSessionTemplate.insert("commentInsert", pMap);
        return result;
    }

    public int commentDelete(int bc_no) {
        logger.info("commentDelete");
        int result = 0;
        result = sqlSessionTemplate.delete("commentDelete", bc_no); // #{value}, String, double
        return result;
    }

    public int boardInsert(Map<String, Object> pMap) {
        logger.info("boardInsert");
        int result = 0;
        result = sqlSessionTemplate.insert("boardInsert", pMap);
        return result;
    }

    public int boardDelete(int b_no) {
        logger.info("boardDelete");
        int result = 0;
        try {
            result = sqlSessionTemplate.delete("boardDelete", b_no);
        } catch (Exception e) {
            logger.info(e.toString());
        }
        return result;
    }

    public int boardUpdate(Map<String, Object> pMap) {
        logger.info("boardUpdate");
        int result = 0;
        try {
            result = sqlSessionTemplate.update("boardUpdate", pMap);
        } catch(Exception e) {
            logger.info(e.toString());
        }
        return result;
    }
}
