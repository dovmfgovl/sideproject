package com.example.demo.controller;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
[
    {},
    {comments:[{},{},{}]}
]
*/
public class BoardComment {
    public static void main(String[] args) {
        List<Map<String, Object>> boardDetail = new ArrayList<>();
        Map<String, Object> dMap = new HashMap<>();
        dMap.put("b_title", "5개월 PT권 양도합니다.");
        dMap.put("b_content", "급작스런 지방발령으로 양도합니다. 원하시면 아래 이메일로 연락주세요.");
        dMap.put("b_date", "2024-01-03");
        boardDetail.add(dMap);
        List<Map<String, Object>> commentList = new ArrayList<>();
        Map<String, Object> rMap = new HashMap<>();
        rMap.put("bc_writer", "키위");
        rMap.put("bc_comment", "혹시 몇 회가 남은 건지 궁금합니다.");
        commentList.add(rMap);
        rMap = new HashMap<>();
        rMap.put("bc_writer", "사과");
        rMap.put("bc_comment", "혹시 5개월동안 헬스 이용도 가능한가요?");
        commentList.add(rMap);
        rMap = new HashMap<>();
        rMap.put("bc_writer", "바나나");
        rMap.put("bc_comment", "혹시 사용기간이 제한되어 있는 걸까요?");
        commentList.add(rMap);
        if (commentList.size()>0) {
            Map<String, Object> cMap = new HashMap<>();
            cMap.put("comments", commentList);
            boardDetail.add(1, cMap);
        }

        System.out.println("상세보기 내용 출력");
        // 리스트에서 0번째 방에 있는 정보를 출력하면 된다.
        Map<String, Object> detail = boardDetail.get(0);
        System.out.println(detail);
        System.out.println("============[[ 댓글목록 보기 ]]============");
        // 댓글을 출력해본다 - 동일한 list에서 가져오는데 단 1번째 방에 있는 정보를 출력한다
        Map<String, Object> comments = (Map)boardDetail.get(1);
        if (comments.containsKey("comments")) {
            List<Map<String, Object>> comList = (List)comments.get("comments");
            for (int i=0; i<comList.size(); i++) {
                Map<String, Object> bcMap = comList.get(i);
                System.out.println(bcMap);
            }
        } else {
            System.out.println("댓글이 없습니다.");
        }


    } // end of main
}
