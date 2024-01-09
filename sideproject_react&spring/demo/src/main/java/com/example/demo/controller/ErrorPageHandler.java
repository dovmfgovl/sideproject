package com.example.demo.controller;

//에러 페이지를 상태코드별로 처리할 수 있는 클래스 샘플입니다.

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageHandler implements ErrorController {
    // 언제 사용하는가? : throw new Exception() 발동했는데 200 OK 뜬다 - 문제제기
    // 실제로 발동한 에러 상태 코드값을 원하는 번호로 변경하여 페이지 처리할때 사용할 것
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        // error로 들어온 에러의 status를 불러온다 (ex:404,500,403...)

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "redirect:/error/error400.jsp";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "redirect:/error/error404.jsp";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "redirect:/error/error500.jsp";
            } else {
                return "redirect:/error/error.jsp";
            }
        }
        return "error";
    }
}
