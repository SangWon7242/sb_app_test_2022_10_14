package com.sbs.exam.sb_app_2022_10_14.app.repository;

import com.sbs.exam.sb_app_2022_10_14.app.vo.Article;
import com.sbs.exam.sb_app_2022_10_14.app.vo.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardRepository {

  @Select("""
          SELECT *
          FROM board AS B
          WHERE B.id = #{id}
          AND B.delStatus = 0  
          """)
  Board getBoardById(@Param("id") int id);
}
