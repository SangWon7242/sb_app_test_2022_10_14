package com.sbs.exam.sb_app_2022_10_14.app.member.repository;

import com.sbs.exam.sb_app_2022_10_14.app.member.vo.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

@Mapper
public interface MemberRepository {

  @Insert("""
          INSERT INTO `member`
          SET regDate = NOW(),
          updateDate = NOW(),
          loginId = #{loginId},
          loginPw = #{loginPw},         
          `name` = #{name},
          nickname = #{nickname},
          cellphoneNo = #{cellphoneNo},
          email = #{email}
          """)
  public void join(@Param("loginId") String loginId, @Param("loginPw") String loginPw, @Param("name") String name,
                   @Param("nickname") String nickname, @Param("cellphoneNo") String cellphoneNo, @Param("email") String email);

  @Select("""
          SELECT LAST_INSERT_ID()
          """)
  int getLastInsertId();

  @Select("""
          SELECT *
          FROM `member` AS M
          WHERE M.id = #{id}
          """)
  Member getMemberById(@Param("id") int id);

  @Select("""
          SELECT *
          FROM `member` AS M
          WHERE M.loginId = #{loginId}
          """)
  Member getMemberByLoginId(@Param("loginId") String loginId);

  @Select("""
          SELECT *
          FROM `member` AS M
          WHERE M.name = #{name}
          AND M.email = #{email}
          """)
  Member getMemberByNameAndEmail(@Param("name") String name,@Param("email")  String email);
}
