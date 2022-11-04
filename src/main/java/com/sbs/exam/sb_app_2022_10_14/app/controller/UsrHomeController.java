package com.sbs.exam.sb_app_2022_10_14.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrHomeController {
  @RequestMapping("/usr/home/main")
  public String showMain() {
    return "usr/home/main";
  }

  @RequestMapping("/jsp")
  public String showJsp() {
    return "test";
  }

  @RequestMapping("/")
  public String showRoot() {
    return "redirect:/usr/home/main";
  }
}


