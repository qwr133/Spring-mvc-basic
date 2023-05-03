package com.spring.mvc.chap05.api;

import com.spring.mvc.chap05.dto.ReplyListResponseDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/replies")
@Slf4j

//restAPI는 api 패키지에 넣고
//일반 컨트롤러는 controller package에 넣음
public class ReplyController {

    private final ReplyService replyService;

    //댓글 목록 조회
    //url : /api/v1/replies/3/page/1 - 3번 게시물에 대한 1페이지 댓글 보여줘
    @GetMapping("/{boardNo}/page/{pageNo}")
    public ResponseEntity<?> list(
            @PathVariable long boardNo,
            @PathVariable int pageNo  //이렇게하면 ?표로 받게됨 근데 getMapping이니까 /로 받기 위해서 @PathVariable 추가
    ) {
        log.info("/api/v1/replies/{}/page/{} : GET !!", boardNo, pageNo);

        //서비스와 연결
        Page page = new Page();
        page.setPageNo(pageNo);
        //게시물은 6개로 띄우고 댓글은 10개씩 보여주기
        page.setAmount(10);
        ReplyListResponseDTO replyList = replyService.getList(boardNo, page);

        return ResponseEntity.ok().body(replyList);
    }

}
