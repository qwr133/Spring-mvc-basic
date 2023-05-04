package com.spring.mvc.chap05.api;

import com.spring.mvc.chap05.dto.ReplyListResponseDTO;
import com.spring.mvc.chap05.dto.ReplyModifyRequestDTO;
import com.spring.mvc.chap05.dto.ReplyPostRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.sql.SQLException;
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


    //댓글 등록 요청
    @PostMapping
    public ResponseEntity<?> create(
            //RequestBody: 요청메세지 바디에 JSON으로 보내주세요
            //클라이언트 개발자! 내가 검증할거야! 그러면 ReplyPostRequestDTO 에 필수값들 검증함
            @Validated @RequestBody ReplyPostRequestDTO dto
            , BindingResult result  //검증결과를 가진 객체
    ) {
        //입력값 검증에 걸리면 4xx 상태 코드 리턴
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(result.toString());
        }

        log.info("/api/v1/repilies/ : POST !!");
        log.info("param : {}", dto);

        //서비스에 비즈니스 로직 처리 위임
        try {
            ReplyListResponseDTO responseDTO = replyService.register(dto);
            //성공시 클라이언트에게 응답하기
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            // 문제발생 상황을 클라이언트에게 전달
            log.warn("500 STatus code repsponse!! caused by :{}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
            //e.getMessage() - ReplyService(line64) 댓글저장실패
        }


    }
    // 댓글 삭제 요청
    @DeleteMapping("/{replyNo}")
    public ResponseEntity<?> remove(
            //@PathVariable("relyNo") long rno (위 맵핑 파라미터와 변수가 다를시)
            @PathVariable(required = false) Long replyNo
    ) {
        if (replyNo == null) {
            return ResponseEntity
                    .badRequest()
                    .body("댓글 번호를 보내주세요!");
        }

        log.info("/api/v1/replies/{} DELETE!", replyNo);

        try {
            ReplyListResponseDTO responseDTO
                    = replyService.delete(replyNo);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }

    }

    //댓글 수정 요청
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}) //put,patch 둘다 받음
    //postman put,patch에 replies 주소까지 입력 후 수정 후 db 확인
    //@PutMapping("/{replyNo}")
    public ResponseEntity<?> modify(
            @Validated @RequestBody ReplyModifyRequestDTO dto
            , BindingResult result
    ) {

        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(result.toString());
        }

        log.info("/api/v1/replies PUT!");
        try {
            ReplyListResponseDTO responseDTO = replyService.modify(dto);
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            log.warn("500 status code! caused by: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
