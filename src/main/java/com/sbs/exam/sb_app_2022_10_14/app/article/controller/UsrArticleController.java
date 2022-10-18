package com.sbs.exam.sb_app_2022_10_14.app.article.controller;

import com.sbs.exam.sb_app_2022_10_14.app.article.vo.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsrArticleController {

  private int articlesLastId;
  private List<Article> articles;

  public UsrArticleController() {
    articles = new ArrayList<>();
    articlesLastId = 0;

    makeTestData();
  }

  private void makeTestData() {
    for (int i = 1; i <= 10; i++) {
      String title = "제목 " + i;
      String body = "내용 " + i;

      writeArticle(title, body);
    }
  }

  public Article writeArticle(String title, String body) {
    int id = articlesLastId + 1;
    Article article = new Article(id, title, body);

    articles.add(article);
    articlesLastId = id;

    return article;
  }

  @RequestMapping("/usr/article/doAdd")
  @ResponseBody
  public Article doAdd(String title, String body) {
    Article article = writeArticle(title, body);

    return article;
  }

  @RequestMapping("/usr/article/getArticles")
  @ResponseBody
  public List<Article> getArticles() {
    return articles;
  }
}
