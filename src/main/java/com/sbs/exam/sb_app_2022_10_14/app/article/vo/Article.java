package com.sbs.exam.sb_app_2022_10_14.app.article.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
  private int id;
  private String title;
  private String body;
}