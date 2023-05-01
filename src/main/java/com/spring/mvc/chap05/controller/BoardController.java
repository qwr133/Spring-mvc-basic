package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.PageMaker;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller //디스패처 서블릿 주입
@RequiredArgsConstructor //의존성 주입
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    //목록조회
    @GetMapping("/list")
    public String list(Page page, Model model) { //클라이언트한테 page정보 받아오기
        log.info("/board/list : GET");
        log.info("page : {}", page);
        List<BoardListResponseDTO> responseDTOS
                = boardService.getList(page);

        //페이징 알고리즘 작동
        PageMaker maker = new PageMaker(page, boardService.getCount());
        //301에 대한건 db에 관련된 정보이니 서비스한테 요청해야함

        model.addAttribute("bList", responseDTOS);
        //jsp에게 주려면 모델에 담아야함
        model.addAttribute("maker", maker);
        return "chap05/list";
    }

    // 글쓰기 화면 조회 요청
    @GetMapping("/write")
    public String write() {
        System.out.println("/board/write : GET");
        return "chap05/write";
    }

    // 글 등록 요청 처리
    @PostMapping("/write")
    public String write(BoardWriteRequestDTO dto) {
        System.out.println("/board/write : POST");
        boardService.register(dto);
        return "redirect:/board/list";
    }



    // 글 삭제 요청 처리
    @GetMapping("/delete")
    public String delete(int bno) {
        System.out.println("/board/delete : GET");
        boardService.delete(bno);
        return "redirect:/board/list";
    }




    //게시물 등록 - 게시물 정보 통채로 필요함
    @GetMapping("/detail")
    public String detail(int bno, Model model) { //글번호 내놔봐, model에 넣기
        System.out.println("/board/detail : GET");
        model.addAttribute("b", boardService.getDetail(bno));
        return "chap05/detail";
    }






    //게시글 수정
    @PostMapping("/modify")
    public String modify(BoardWriteRequestDTO dto){
        System.out.println("/board/modify : POST");
        boardService.modify(dto);
        return "redirect:/board/list";
    }

}


