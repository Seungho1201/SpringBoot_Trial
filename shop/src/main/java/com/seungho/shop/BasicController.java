package com.seungho.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {

    @GetMapping("/")
    //@ResponseBody 는 문자 그대로 보내주세요란 의미 빼야 html 파일이 return됨

    String hello(){
        return "index.html";
    }


    @GetMapping("/about")
    @ResponseBody

    String hello2(){
        return "Hello World2";
    }
}
