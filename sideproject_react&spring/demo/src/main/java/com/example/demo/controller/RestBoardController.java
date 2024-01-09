package com.example.demo.controller;

import com.example.demo.logic.BoardLogic;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController // @Controller + @ResponseBody
@RequestMapping("/board/*")
public class RestBoardController {
    Logger logger = LoggerFactory.getLogger(RestBoardController.class);
    @Autowired
    private BoardLogic boardLogic = null;

    @GetMapping("jsonBoardList")
    public String jsonBoardList(@RequestParam Map<String, Object> pMap, HttpServletRequest req) {
        logger.info(pMap.toString());
        List<Map<String, Object>> list = null;
        list = boardLogic.boardList(pMap);
        Gson g = new Gson();
        String temp = g.toJson(list);
        return temp;
    }
    @PostMapping("jsonBoardInsert")
    public String jsonBoardInsert(@RequestBody Map<String, Object> pMap) {
        logger.info("jsonBoardInsert");
        logger.info(pMap.toString());
        int result = 0;
        result = boardLogic.boardInsert(pMap);
        return String.valueOf(result);
    }
    @GetMapping("jsonBoardDetail")
    public String jsonBoardDetail(@RequestParam Map<String, Object> pMap){
        logger.info("jsonBoardDetail");
        List<Map<String, Object>> bList = null;
        bList = boardLogic.boardDetail(pMap);
        Gson g = new Gson();
        String temp = g.toJson(bList);
        return temp;
    }
    @GetMapping("jsonBoardDelete")
    public String jsonBoardDelete(int b_no) {
        logger.info("jsonBoardDelete");
        int result = 0;
        result = boardLogic.boardDelete(b_no);
        Gson g = new Gson();
        String temp = g.toJson(result);
        return temp;
    }
    @PostMapping("jsonBoardUpdate")
    public String jsonBoardUpdate(@RequestBody Map<String, Object> pMap) {
        logger.info("jsonBoardUpdate");
        logger.info(pMap.toString());
        int result = 0;
        result = boardLogic.boardUpdate(pMap);
        return String.valueOf(result);
    }
    @PostMapping("imageUpload") // url: '/board/imageUpload',
    public String imageUpload(@RequestParam(value = "image") MultipartFile image, HttpServletRequest req) {
        String savePath = req.getSession().getServletContext().getRealPath("/pds");
        String filename = "";
        filename = boardLogic.imageUpload(image, savePath);
        // return "아바타.png";//imageGet요청을 보내서 image url정보를 받아와야 함
        return filename;
    }
    // 먼저 이미지를 업로드하고 호출된 메소드의 반환값으로 이미지 이름을 받아 아래 메소드에 다시 요청한다
    // const url = "/board/imageGet?imageName="+response;
    @GetMapping("imageGet") // url: '/board/imageUpload',
    public byte[] imageGet(@RequestParam(value = "imageName") String imageName, HttpServletRequest req) {
        String savePath = req.getSession().getServletContext().getRealPath("/pds");
        String filename = "";
        // boardLogic.imageUpload(image, savePath);
        // return "아바타.png";//imageGet요청을 보내서 image url정보를 받아와야 함
        byte[] fileArray = boardLogic.imageDownload(imageName, savePath);
        return fileArray;
    }
//    @GetMapping("imageGet") // url: '/board/imageGet',
//    public ResponseEntity<Resource> imageGet(@RequestParam(value = "image")MultipartFile image, HttpServletRequest req) {
//        String savePath = req.getSession().getServletContext().getRealPath("/pds");
//        String filename = "";
//        // boardLogic.imageUpload(image, savePath);
//        // return "아바타.png";
//        return filename;
//    }
}
