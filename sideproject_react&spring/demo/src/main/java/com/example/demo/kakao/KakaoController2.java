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
import com.vo.KakaoProfile;
import com.vo.OAuthToken;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/auth/*")
public class KakaoController2 {
  Logger logger = LoggerFactory.getLogger(KakaoController2.class);

  // @ResponseBody를 붙이면 Data를 리턴해주는 컨트롤러 메소드가 된다
  @GetMapping("/kakao/callback")
  // 1. 인가 코드 받기
  public String kakaoCallback(String code, HttpSession session) {
    logger.info("kakaoCallback");
    logger.info(code);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", "1270689e90debc4edc797c63e2f3eb5e");
    params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
    params.add("code", code);
    // 2. 토큰 값 담기
    // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
    HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(params, headers);
    RestTemplate rt = new RestTemplate();
    ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, tokenRequest,
        String.class);
    Gson g = new Gson();
    OAuthToken oat = g.fromJson(response.getBody(), OAuthToken.class); // VO에 담음
    
    // 3. 사용자 정보 가져오기
    HttpHeaders headers2 = new HttpHeaders();
    headers2.add("Authorization", "Bearer "+oat.getAccess_token());
    headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
    HttpEntity<MultiValueMap<String, String>> profileRequest = new HttpEntity<>(headers2);
    RestTemplate rt2 = new RestTemplate();
    ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, profileRequest,
        String.class);

    Gson g2 = new Gson();
    KakaoProfile kkp = g2.fromJson(response2.getBody(), KakaoProfile.class);
    logger.info("카카오 아이디(번호) : " + kkp.getId());
    logger.info("카카오 이메일 : " + kkp.getKakao_account().getEmail());
    logger.info("카카오 유저네임 : " + kkp.getProperties().nickname);
    session.setAttribute("nickname", kkp.getProperties().nickname);
    return "redirect:/index.jsp"; // redirect가 없으면 /WEB-INF/views/index.jsp로 이동함. 404뜸
    //return oat.getAccess_token();
  }
  // http://localhost:8000/auth/logout
  @GetMapping("logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/index.jsp";
  }
  

}
