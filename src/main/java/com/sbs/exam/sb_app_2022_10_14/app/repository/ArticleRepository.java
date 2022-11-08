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
          memberId = #{memberId},
          title = #{title},
          `body` = #{body}
          </script>
          """)
  public void writeArticle(@Param("memberId") int memberId, @Param("title") String title, @Param("body") String body);

  @Select("""
          <script>
          SELECT A.*,
          M.nickname AS extra__writerName
          FROM article AS A
          LEFT JOIN member AS M
          ON A.memberId = M.id
          WHERE 1
          AND A.id DESC
          </script>
          """)
  public Article getForPrintArticle(@Param("id") int id);

  @Delete("""
          <script>
          DELETE
          FROM article
          WHERE id = #{id}
          </script>
          """)
  public void deleteArticle(@Param("id") int id);

  @Select("""
          SELECT A.*,
          M.nickname AS extra__writerName
          FROM article AS A
          LEFT JOIN member AS M
          ON A.memberId = M.id
          ORDER BY A.id DESC
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
