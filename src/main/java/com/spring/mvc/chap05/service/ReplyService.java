package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.response.ReplyDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.ReplyListResponseDTO;
import com.spring.mvc.chap05.dto.request.ReplyModifyRequestDTO;
import com.spring.mvc.chap05.dto.request.ReplyPostRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.PageMaker;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.repository.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/*
화면에 보여지는 값들을 클라이언트 개발자가 변경요청을 하게되면 service에서 변경해야한다
예, replyNo를 RN으로 변경해주세요, 날짜 데이터를 년월일로 변경해주세요 등
 */

@Service
@RequiredArgsConstructor //주입을 위한 생성자
@Slf4j
public class ReplyService {

    private final ReplyMapper replyMapper; //repository에 의존

    //댓글 목록 조회 서비스
    public ReplyListResponseDTO getList(long boardNo, Page page) {

        replyMapper.findAll(boardNo, page)
                .stream() .map(reply -> new ReplyDetailResponseDTO(reply))
                .collect(Collectors.toList());


        List<ReplyDetailResponseDTO> replies = replyMapper.findAll(boardNo, page)
                .stream()
                .map(reply -> new ReplyDetailResponseDTO(reply))
                .collect(Collectors.toList());

        int count = replyMapper.count(boardNo);

        return ReplyListResponseDTO.builder()
                .count(count)
                .pageInfo(new PageMaker(page, count))
                .replies(replies)
                .build();
    }

    //댓글등록 서비스
    //로그 화면에 갱신목록을 바로 보여주면되니까 ReplyListREsponseDTO를 void 대신에 넣음
    public ReplyListResponseDTO register(final ReplyPostRequestDTO dto) //final - 매개변수 조작못하게 보호하기
            throws SQLException {
        log.debug("register service execute!!");

        //dto를 entity로 변환 - 메서드만들기(위 dto에)
        Reply reply = dto.toEntity();
        boolean flag = replyMapper.save(reply);

        //예외처리
        if (!flag) {
            log.warn("reply registered fail!");
            throw new SQLException("댓글저장 실패");
        }
        return getList(dto.getBno(), new Page(1, 10));
    }


    //댓글 삭제 서비스 - 삭제에서 갱신된 목록 가져오기
    @Transactional //트랜잭션처리 -- 하나라도 처리가 안되면 롤백, 모두 성공시 커밋
    public ReplyListResponseDTO delete(final long replyNo)
    throws Exception{

        long boardNo = replyMapper.findOne(replyNo).getBoardNo();
        replyMapper.deleteOne(replyNo);

        return getList(
                boardNo
                , new Page(1, 10)
        );
    }

    public ReplyListResponseDTO modify(final ReplyModifyRequestDTO dto)
        throws Exception{

        replyMapper.modify(dto.toEntity());
        return getList(dto.getBno(), new Page(1, 10)

        );
    }



}
