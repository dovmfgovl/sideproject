package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
  첨부파일처리와 이미지 파일 처리, 다운로드 처리 추가
  응답이 페이지가 아닐 때 선택함
  응답이 어떤 mime타입일 때 인가?
  1) text/plain : 단순 문자열, 2) application/json : JSON포맷
  @RestContoller에 선언하는 메소드의 리턴타입은 String이다. 
  주의사항) 절대 redirect, forward 붙이지 말 것
  세션을 주입받을 수 있다 
    - 상태관리(쿠키(로컬에서 관리 - 따라서 로컬에서 볼 수 있다), 세션(서버의 cache메모리 - 따라서 로컬에서 볼 수 없다))
*/
@RestController
@RequestMapping("/member/*")
public class RestMemberController {
  Logger logger = LoggerFactory.getLogger(RestMemberController.class);
  @GetMapping("fileUpload")
  public String fileUpload(){
    logger.info("fileUpload");
    return "fileUpload";
  }
  @GetMapping("imageUpload")
  public String imageUpload(){
    logger.info("imageUpload");
    return "imageUpload";
  }
  @GetMapping("imageDownload")
  public String imageDownload(){
    logger.info("imageDownload");
    return "imageDownload";
  }
  @GetMapping("imageGet")
  public String imageGet(){
    logger.info("imageGet");
    return "imageGet";
  }
}
