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
          LEFT JOIN `member` AS M
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
        LEFT JOIN `member` AS M
        ON A.memberId = M.id
        WHERE 1
        <if test="boardId != 0">
          AND A.boardId = #{boardId}
        </if>
        <if test="searchKeyword != ''">
          <choose>
            <when test="searchKeywordTypeCode == 'title'">
              AND A.title LIKE CONCAT('%', #{searchKeyword}, '%')
            </when>
            <when test="searchKeywordTypeCode == 'body'">
              AND A.body LIKE CONCAT('%', #{searchKeyword}, '%')
            </when>
            <otherwise>
              AND (
                A.title LIKE CONCAT('%', #{searchKeyword}, '%')
                OR
                A.body LIKE CONCAT('%', #{searchKeyword}, '%')
              )
            </otherwise>
          </choose>
        </if>
        ORDER BY A.id DESC                    
        <if test="limitTake != -1">
          LIMIT #{limitStart}, #{limitTake}
        </if>        
        </script>
        """)
  public List<Article> getForPrintArticles(@Param("boardId") int boardId, @Param("searchKeywordTypeCode") String searchKeywordTypeCode, @Param("searchKeyword") String searchKeyword, @Param("limitStart") int limitStart, @Param("limitTake") int limitTake);

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
          <if test="searchKeyword != ''">
            <choose>
              <when test="searchKeywordTypeCode == 'title'">
                AND A.title LIKE CONCAT('%', #{searchKeyword}, '%')
              </when>
              <when test="searchKeywordTypeCode == 'body'">
                AND A.body LIKE CONCAT('%', #{searchKeyword}, '%')
              </when>
              <otherwise>
                AND (
                  A.title LIKE CONCAT('%', #{searchKeyword}, '%')
                  OR
                  A.body LIKE CONCAT('%', #{searchKeyword}, '%')
                )
              </otherwise>
            </choose>
          </if>          
          </script>
          """)
  public int getArticleCount(@Param("boardId") int boardId, @Param("searchKeywordTypeCode") String searchKeywordTypeCode, @Param("searchKeyword")  String searchKeyword);

  @Update("""
          <script>
          UPDATE article
          SET hitCount = hitCount + 1
          WHERE id = #{id}               
          </script>
          """)
  public int increaseHitCount(@Param("id") int id);

  @Select("""
          <script>
          SELECT hitCount
          FROM article
          WHERE id = #{id}             
          </script>
          """)
  public int getArticleHitCount(@Param("id") int id);

  @Update("""
			<script>
			UPDATE article
			SET goodReactionPoint = goodReactionPoint + 1
			WHERE id = #{id}
			</script>
			""")
  public int increaseGoodReactionPoint(@Param("id") int id);

  @Update("""
          <script>
          UPDATE article
          SET badReactionPoint = badReactionPoint + 1
          WHERE id = #{id}               
          </script>
          """)
  public int increaseBadReactionPoint(@Param("id") int id);

  @Update("""
          <script>
          UPDATE article
          SET goodReactionPoint = goodReactionPoint - 1
          WHERE id = #{id}               
          </script>
          """)
  public int decreaseGoodReactionPoint(@Param("id") int id);
  @Update("""
          <script>
          UPDATE article
          SET badReactionPoint = badReactionPoint - 1
          WHERE id = #{id}               
          </script>
          """)
  public int decreaseBadReactionPoint(@Param("id") int id);

  @Select("""
          <script>
          SELECT *
          FROM article
          WHERE id = #{id}             
          </script>
          """)
  Article getArticle(@Param("id") int id);
}
