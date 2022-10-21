package com.sbs.exam.sb_app_2022_10_14.app.article.repository;

import com.sbs.exam.sb_app_2022_10_14.app.article.vo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleRepository {

  @Insert("INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = #{title}, `body` = #{body}")
  public void writeArticle(String title, String body);

  @Select("SELECT * FROM article WHERE id = #{id}")
  public Article getArticle(int id);

  @Delete("DELETE FROM article WHERE id = #{id}")
  public void deleteArticle(int id);

  @Update("UPDATE article SET title = #{title}, `body` = #{body}, updateDate = NOW() WHERE id = #{id}")
  public void modifyArticle(int id, String title, String body);

  @Select("SELECT * FROM article ORDER BY id DESC")
  public List<Article> getArticles();

  @Select("SELECT LAST_INSERT_ID()")
  int getLastInsertId();
}
