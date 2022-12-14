package com.sbs.exam.sb_app_2022_10_14.app.vo;

import com.sbs.exam.sb_app_2022_10_14.app.service.MemberService;
import com.sbs.exam.sb_app_2022_10_14.app.util.Ut;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
  @Getter
  private int loginedMemberId;
  @Getter
  private boolean isLogined;

  @Getter
  private Member loginedMember;

  private HttpServletRequest req;
  private HttpServletResponse resp;
  private HttpSession session;
  public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
    this.req = req;
    this.resp = resp;
    this.session = req.getSession();

    HttpSession httpSession = req.getSession();
    boolean isLogined = false;
    int loginedMemberId = 0;

    if (httpSession.getAttribute("loginedMemberId") != null) {
      isLogined = true;
      loginedMemberId = (int) session.getAttribute("loginedMemberId");
      loginedMember = memberService.getMemberById(loginedMemberId);
    }

    this.isLogined = isLogined;
    this.loginedMemberId = loginedMemberId;
    this.loginedMember = loginedMember;

    // 만들어진 진짜 rq를 jsp에서 쓸 수 있도록 하는 로직
    // this.req.setAttribute("rq", this);
  }

  public void printHistoryBackJs(String msg) {
    resp.setContentType("text/html; charset=UTF-8");
    print(Ut.jsHistoryBack(msg));
  }

  public void print(String str) {
    try {
      resp.getWriter().append(str);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isNotLogined() {
    return !isLogined;
  }
  public void println(String str) {
    print(str + "\n");
  }

  public void login(Member member) {
    session.setAttribute("loginedMemberId", member.getId());
  }

  public void logout() {
    session.removeAttribute("loginedMemberId");
  }

  public String historyBackJsOnView(String msg) {
    req.setAttribute("msg", msg);
    req.setAttribute("historyBack", true);
    return "common/js";
  }

  public String jsHistoryBack(String msg) {
    return Ut.jsHistoryBack(msg);
  }

  public String jsReplace(String msg, String uri) {
    return Ut.jsReplace(msg, uri);
  }

  public String getCurrentUri() {
    String currentUri = (String)req.getAttribute("javax.servlet.forward.request_uri");
    String queryString = req.getQueryString();

    if (queryString != null && queryString.length() > 0) {
      currentUri += "?" + queryString;
    }

    return currentUri;
  }

  public String getEncodedCurrentUri() {
    return Ut.getUriEncoded(getCurrentUri());
  }

  // 테스트용 코드
  /*public void runA() {
    System.out.println("A 호출됨!");
    runB();
  }

  public void runB() {
    System.out.println("B 호출됨!");
  }
   */
}
