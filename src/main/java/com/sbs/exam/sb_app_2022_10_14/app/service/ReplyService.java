package com.sbs.exam.sb_app_2022_10_14.app.service;

import com.sbs.exam.sb_app_2022_10_14.app.repository.ReplyRepository;
import com.sbs.exam.sb_app_2022_10_14.app.util.Ut;
import com.sbs.exam.sb_app_2022_10_14.app.vo.Article;
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
    List<Reply> replies = replyRepository.getForPrintReplies(relTypeCode, relId);

    for(Reply reply : replies) {
      updateForPrintData(actor, reply);
    }

    return replies;
  }

  private void updateForPrintData(Member actor, Reply reply) {
    if( reply == null ) {
      return;
    }

    ResultData actorCanDeleteRd = actorCanDelete(actor, reply);
    reply.setExtra__actorCanDelete(actorCanDeleteRd.isSuccess());

    ResultData actorCanModifyRd = actorCanModify(actor, reply);
    reply.setExtra__actorCanModify(actorCanModifyRd.isSuccess());
  }

  private ResultData actorCanModify(Member actor, Reply reply) {
    if ( reply == null ) {
      return ResultData.from("F-1", "댓글이 존재하지 않습니다.");
    }

    if ( reply.getMemberId() != actor.getId() ) {
      return ResultData.from("F-2", "권한이 없습니다.");
    }

    return ResultData.from("S-1", "댓글 수정이 가능합니다.");
  }

  private ResultData actorCanDelete(Member actor, Reply reply) {
    if ( reply == null ) {
      return ResultData.from("F-1", "댓글이 존재하지 않습니다.");
    }

    if ( reply.getMemberId() != actor.getId() ) {
      return ResultData.from("F-2", "권한이 없습니다.");
    }

    return ResultData.from("S-1", "댓글 삭제가 가능합니다.");
  }

  public Reply getForPrintReply(Member actor, int id) {
    Reply reply = replyRepository.getForPrintReply(id);

    updateForPrintData(actor, reply);

    return  reply;
  }

  public ResultData deleteReply(int id) {
    replyRepository.deleteReply(id);

    return ResultData.from("S-1", Ut.f("%d번 댓글을 삭제하였습니다.", id));
  }

  public Reply getReply(Member actor, int id) {
    return replyRepository.getReply(id);
  }
}
