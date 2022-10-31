package com.sbs.exam.sb_app_2022_10_14.app.repository;

import com.sbs.exam.sb_app_2022_10_14.app.vo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleRepository {

  @Insert("""
         <script>
         INSERT INTO article
          SET regDate = NOW(),
          updateDate = NOW(),
          title = #{title},
          `body` = #{body}
          </script>
          """)
  public void writeArticle(@Param("title") String title, @Param("body") String body);

  @Select("""
          <script>
          SELECT *
          FROM article
          WHERE id = #{id}
          </script>
          """)
  public Article getArticle(@Param("id") int id);

  @Delete("""
          <script>
          DELETE
          FROM article
          WHERE id = #{id}
          </script>
          """)
  public void deleteArticle(@Param("id") int id);

  @Select("""
          <script>
          SELECT *
          FROM article
          ORDER BY id DESC
          </script>
          """)
  public List<Article> getArticles();

  @Update("""
          <script>
          UPDATE article    
          <set>
            <if test="title != null and title != ''">
              title = #{title},
            </if>
            <if test="body != null and body != ''">
              `body` = #{body},
            </if>          
          updateDate = NOW()
          </set>
          WHERE id = #{id}
          </script>
          """)
  public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

  @Select("""
          SELECT LAST_INSERT_ID()
          """)
  public int getLastInsertId();
}
