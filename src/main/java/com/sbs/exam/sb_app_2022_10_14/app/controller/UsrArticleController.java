package com.sbs.exam.sb_app_2022_10_14.app.controller;

import com.sbs.exam.sb_app_2022_10_14.app.service.ArticleService;
import com.sbs.exam.sb_app_2022_10_14.app.util.Ut;
import com.sbs.exam.sb_app_2022_10_14.app.vo.Article;
import com.sbs.exam.sb_app_2022_10_14.app.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UsrArticleController {

  @Autowired
  private ArticleService articleService;

  @RequestMapping("/usr/article/doAdd")
  @ResponseBody
  public ResultData<Article> doAdd(HttpSession httpSession, String title, String body) {
    boolean isLogined = false;
    int loginedMemberId = 0;

    if (httpSession.getAttribute("loginedMemberId") != null) {
      isLogined = true;
      loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
    }

    if(isLogined == false) {
      return ResultData.from("F-A", "로그인 후 이용해주세요.");
    }

    if ( Ut.empty(title) ) {
      return ResultData.from("F-1", "title(을)를 입력해주세요.");
    }

    if ( Ut.empty(body) ) {
      return ResultData.from("F-2", "body(을)를 입력해주세요.");
    }

    ResultData<Integer> writeArticleRd = articleService.writeArticle(loginedMemberId , title, body);
    int id = writeArticleRd.getData1();

    Article article = articleService.getForPrintArticle(id);

    return ResultData.newData(writeArticleRd, "article", article);
  }

  @RequestMapping("/usr/article/list")
  public String showList(Model model) {
    List<Article> articles = articleService.getForPrintArticles();

    model.addAttribute("articles", articles);

    return "usr/article/list";
  }

  @RequestMapping("/usr/article/detail")
  public String showDetail(Model model, int id) {
    Article article = articleService.getForPrintArticle(id);
    model.addAttribute("article", article);

    return "usr/article/detail";
  }

  @RequestMapping("/usr/article/doDelete")
  @ResponseBody
  public ResultData<Integer> doDelete(HttpSession httpSession, int id) {
    boolean isLogined = false;
    int loginedMemberId = 0;

    if (httpSession.getAttribute("loginedMemberId") != null) {
      isLogined = true;
      loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
    }

    if(isLogined == false) {
      return ResultData.from("F-A", "로그인 후 이용해주세요.");
    }

    Article article = articleService.getForPrintArticle(id);

    if ( article.getMemberId() !=  loginedMemberId) {
      return ResultData.from("F-2", "권한이 없습니다.");
    }

    if ( article == null ) {
      return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
    }

    articleService.deleteArticle(id);

    return ResultData.from("S-1", Ut.f("%d번 게시물을 삭제하였습니다.", id), "id", id);
  }

  @RequestMapping("/usr/article/doModify")
  @ResponseBody
  public ResultData<Article> doModify(HttpSession httpSession, int id, String title, String body) {
    boolean isLogined = false;
    int loginedMemberId = 0;

    if (httpSession.getAttribute("loginedMemberId") != null) {
      isLogined = true;
      loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
    }

    if(isLogined == false) {
      return ResultData.from("F-A", "로그인 후 이용해주세요.");
    }

    Article article = articleService.getForPrintArticle(id);

    if ( article == null ) {
      return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
    }

    ResultData actorCanModifyRd = articleService.actorCanModify(loginedMemberId, article);

    if(actorCanModifyRd.isFail()) {
      return actorCanModifyRd;
    }

    return articleService.modifyArticle(id, title, body);
  }
}
