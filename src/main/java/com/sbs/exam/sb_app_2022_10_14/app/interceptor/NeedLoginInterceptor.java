package com.sbs.exam.sb_app_2022_10_14.app.interceptor;

import com.sbs.exam.sb_app_2022_10_14.app.vo.Rq;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor {
  private Rq rq;

  public NeedLoginInterceptor(Rq rq) {
    this.rq = rq;
  }

  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
    if( !rq.isLogined() ) {
      rq.printHistoryBackJs("로그인 후 이용해주세요.");
      return false;
    }

    return HandlerInterceptor.super.preHandle(req, resp, handler);
  }
}
