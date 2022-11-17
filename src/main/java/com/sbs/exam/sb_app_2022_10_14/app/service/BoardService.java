package com.sbs.exam.sb_app_2022_10_14.app.service;

import com.sbs.exam.sb_app_2022_10_14.app.repository.ArticleRepository;
import com.sbs.exam.sb_app_2022_10_14.app.repository.BoardRepository;
import com.sbs.exam.sb_app_2022_10_14.app.util.Ut;
import com.sbs.exam.sb_app_2022_10_14.app.vo.Article;
import com.sbs.exam.sb_app_2022_10_14.app.vo.Board;
import com.sbs.exam.sb_app_2022_10_14.app.vo.ResultData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
  private BoardRepository boardRepository;

  public BoardService(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  public Board getBoardById(int id) {
    return boardRepository.getBoardById(id);
  }
}

