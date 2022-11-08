package com.sbs.exam.sb_app_2022_10_14.app.vo;

import lombok.Getter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Rq {

  @Getter
  private int loginedMemberId;
  @Getter
  private boolean isLogined;

  public Rq(HttpServletRequest req) {
    HttpSession httpSession = req.getSession();
    boolean isLogined = false;
    int loginedMemberId = 0;

    if (httpSession.getAttribute("loginedMemberId") != null) {
      isLogined = true;
      loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
    }

    this.isLogined = isLogined;
    this.loginedMemberId = loginedMemberId;
  }

}
