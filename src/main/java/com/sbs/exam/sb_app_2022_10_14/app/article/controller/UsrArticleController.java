package com.sbs.exam.sb_app_2022_10_14.app.article.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrArticleController {
  int count;

  public UsrArticleController() {
    count = -1;
  }

  @RequestMapping("/usr/home/main")
  @ResponseBody
  public String showMain() {
    return "안녕하세요.";
  }

  @RequestMapping("/usr/home/main2")
  @ResponseBody
  public String showMain2() {
    return "반갑습니다.";
  }

  @RequestMapping("/usr/home/main3")
  @ResponseBody
  public String showMain3() {
    return "또 만나요.";
  }

  @RequestMapping("/usr/home/main4")
  @ResponseBody
  public int showMain4() {
    count++;
    return count;
  }
}
