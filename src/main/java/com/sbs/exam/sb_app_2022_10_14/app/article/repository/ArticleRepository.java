package com.sbs.exam.sb_app_2022_10_14.app.article.repository;

import com.sbs.exam.sb_app_2022_10_14.app.article.vo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleRepository {
  @Select("""
            <script>
            SELECT *
            FROM article
            </script>
            """)
  List<Article> getArticles();
}
