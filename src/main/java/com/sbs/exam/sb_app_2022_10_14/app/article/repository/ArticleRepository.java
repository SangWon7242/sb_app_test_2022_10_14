package com.sbs.exam.sb_app_2022_10_14.app.article.repository;

import com.sbs.exam.sb_app_2022_10_14.app.article.vo.Article;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleRepository {
  int articlesLastId;
  List<Article> articles;

  public ArticleRepository() {
    articlesLastId = 0;
    articles = new ArrayList<>();
  }

  public void makeTestData() {
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

  public Article getArticle(int id) {
    for (Article article : articles) {
      if (article.getId() == id) {
        return article;
      }
    }

    return null;
  }

  public void deleteArticle(int id) {
    Article article = getArticle(id);

    articles.remove(article);
  }

  public void modifyArticle(int id, String title, String body) {
    Article article = getArticle(id);

    article.setTitle(title);
    article.setBody(body);
  }

  public List<Article> getArticles() {
    return articles;
  }
}
