package com.example.demo.kakao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.vo.OAuthToken;

@Controller
@RequestMapping("/auth1/*")
public class KakaoController1 {
  Logger logger = LoggerFactory.getLogger(KakaoController1.class);
  // @ResponseBody를 붙이면 Data를 리턴해주는 컨트롤러 메소드가 된다
  @GetMapping("/kakao/callback")
  public @ResponseBody String kakaoCallback(String code){
    logger.info("kakaoCallback");
    logger.info(code);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", "1270689e90debc4edc797c63e2f3eb5e");
    params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
    params.add("code", code);
    // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
    HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity(params, headers);
    RestTemplate rt = new RestTemplate();
    ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, tokenRequest, String.class);
    Gson g = new Gson();
    OAuthToken oat = g.fromJson(response.getBody(), OAuthToken.class);
    //return "카카오 제공하는 code값"+code;
    //return response.getBody();
    return oat.getAccess_token();
  }
  
}
