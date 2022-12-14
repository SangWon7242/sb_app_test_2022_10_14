package com.sbs.exam.sb_app_2022_10_14.app.interceptor;

import com.sbs.exam.sb_app_2022_10_14.app.service.MemberService;
import com.sbs.exam.sb_app_2022_10_14.app.vo.Rq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
  private Rq rq;

  public BeforeActionInterceptor(Rq rq) {
    this.rq = rq;
  }

  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
    // rq.initOnBeforeActionInterceptor(); --> 이렇게 하면 rq가 필요 없는 곳에서도 만들어지기 때문에 비효율적이다.
    req.setAttribute("rq", rq);

    return HandlerInterceptor.super.preHandle(req, resp, handler);
  }
}
