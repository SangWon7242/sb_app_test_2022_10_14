package com.sbs.exam.sb_app_2022_10_14.app.service;

import com.sbs.exam.sb_app_2022_10_14.app.repository.ReplyRepository;
import com.sbs.exam.sb_app_2022_10_14.app.util.Ut;
import com.sbs.exam.sb_app_2022_10_14.app.vo.Member;
import com.sbs.exam.sb_app_2022_10_14.app.vo.Reply;
import com.sbs.exam.sb_app_2022_10_14.app.vo.ResultData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {
  private ReplyRepository replyRepository;

  public ReplyService(ReplyRepository replyRepository) {
    this.replyRepository = replyRepository;
  }

  public ResultData<Integer> writeReply(int actorId, String relTypeCode, int relId, String body) {
    replyRepository.writeReply(actorId, relTypeCode, relId, body);
    int id = replyRepository.getLastInsertId();

    return ResultData.from("S-1", Ut.f("%d번 댓글이 생성되었습니다.", id), "id", id);
  }

  public List<Reply> getForPrintReplies(Member actor, String relTypeCode, int relId) {
    return replyRepository.getForPrintReplies(relTypeCode, relId);
  }
}
