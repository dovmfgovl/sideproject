package com.example.demo.controller;

import com.example.demo.logic.BoardLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
/*
 입력처리 : boardInsert 1개
 수정처리 : boardUpdate 1개
 삭제처리 : boardDelete 1개
 조회(n건) : boardList -> forward:./boardList.jsp
 상세조회              -> forward:./boardDetail.jsp - 댓글처리 포함
*/

@Controller
@RequestMapping("/board/*")
public class BoardController {
    Logger logger = LoggerFactory.getLogger(BoardController.class);
    @Autowired
    BoardLogic boardLogic = null;
    @PostMapping("boardInsert")
    public String boardInsert(@RequestParam Map<String, Object> pMap) {
        logger.info("boardInsert");
        int result = 0;
        result = boardLogic.boardInsert(pMap);
        return "redirect:boardList";
    }
    @GetMapping("boardList")
    public String boardList(Model model, @RequestParam Map<String, Object> pMap){
        logger.info("boardList");
        List<Map<String, Object>> bList = null; // List<Map<>> : [{}, {}, {}]
        bList = boardLogic.boardList(pMap);
        model.addAttribute("bList", bList);
        return "forward:boardList.jsp"; // forward니까 webapp 아래에서 찾는다
    }
    // <boardDetail을 따로 분리해야하는 이유>
    // 1) 위 아래는 모두 select인 경우지만 응답페이지 이름이 달라서 메소드를 분리함.
    // 2) 위에서는 board230527테이블만 조회하지만
    //    아래에서는 댓글정보를 가져오기 위해 board230527_comment테이블의 정보도 추가로 가져옴.
    // 3) 아래에서는 b_hit도 증가시킨다. update board230527 set b_hit = b_hit+1 where b_no=2
    @GetMapping("boardDetail")
    public String boardDetail(Model model, @RequestParam Map<String, Object> pMap){
        logger.info("boardDetail");
        logger.info("b_no : "+pMap.get("b_no")); // 2
        List<Map<String, Object>> bList = null; // List<Map<>> : [{}, {}, {}]
        // 동일한 조회인데 왜 메소드를 나눠야 하나? oard230527_comment테이블의 정보도 추가로 가져와야 하니까
        // 컨트롤 계층에 대한 경유없이 처리해야한다 - MVC패턴
        bList = boardLogic.boardDetail(pMap); // select 2번, update 1번 : 업무적인 depth
        model.addAttribute("bList", bList);
        return "forward:boardDetail.jsp"; // forward니까 webapp 아래에서 찾는다
    }
    @GetMapping("commentInsert")
    public String commentInsert(@RequestParam Map<String, Object> pMap) {
        logger.info("commentInsert");
        int result = 0;
        result = boardLogic.commentInsert(pMap);
        return "redirect:./boardDetail?b_no="+pMap.get("b_no").toString();
    } // end of commentInsert

    @GetMapping("commentDelete")
    public String commentDelete(int bc_no, int b_no) {
        logger.info("commentDelete");
        int result = 0;
        result = boardLogic.commentDelete(bc_no);
        return "redirect:./boardDetail?b_no="+b_no;
    } // end of commentDelete
}