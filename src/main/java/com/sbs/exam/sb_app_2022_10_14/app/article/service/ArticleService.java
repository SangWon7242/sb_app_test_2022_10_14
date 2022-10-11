package com.sbs.exam.sb_app_2022_10_14.app.article.service;

import com.sbs.exam.sb_app_2022_10_14.app.article.dto.Article;
import com.sbs.exam.sb_app_2022_10_14.app.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final ArticleRepository articleRepository;
  public List<Article> getArticles() {
    return articleRepository.getArticles();
  }
}
