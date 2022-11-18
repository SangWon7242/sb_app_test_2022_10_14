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
          boardId = #{boardId},
          memberId = #{memberId},
          title = #{title},
          `body` = #{body}
          </script>
          """)
  public void writeArticle(@Param("memberId") int memberId, @Param("boardId") int boardId, @Param("title") String title, @Param("body") String body);

  @Select("""
          <script>
          SELECT A.*,
          M.nickname AS extra__writerName
          FROM article AS A
          LEFT JOIN member AS M
          ON A.memberId = M.id
          WHERE 1
          AND A.id = ${id}
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
          <script>
          SELECT A.*,
          M.nickname AS extra__writerName
          FROM article AS A
          LEFT JOIN member AS M
          ON A.memberId = M.id
          WHERE 1
          <if test="boardId != 0">
            AND A.boardId = #{boardId}
          </if>
          ORDER BY A.id DESC
          <if test="limitTake != -1">
            LIMIT #{limitStart}, #{limitTake}
          </if>
          </script>
          """)
  public List<Article> getArticles(@Param("boardId") int boardId, int limitStart, int limitTake);

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

  @Select("""
          <script>
          SELECT COUNT(*) AS cnt       
          FROM article AS A          
          WHERE 1
          <if test="boardId != 0">
            AND A.boardId = #{boardId}
          </if>          
          </script>
          """)
  public int getArticleCount(@Param("boardId") int boardId);
}
