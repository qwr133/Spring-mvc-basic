package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.ReplyDetailResponseDTO;
import com.spring.mvc.chap05.dto.ReplyListResponseDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.PageMaker;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.repository.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

}
