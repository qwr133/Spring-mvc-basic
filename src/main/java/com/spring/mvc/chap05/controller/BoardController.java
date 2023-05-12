package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.response.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.page.PageMaker;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.service.BoardService;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller //디스패처 서블릿 주입
@RequiredArgsConstructor //의존성 주입
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    //목록조회
    @GetMapping("/list")
    public String list(Search page, Model model,
                       HttpServletRequest request
    ) throws IOException {//클라이언트한테 page정보 받아오기

//        boolean flag = false;

        //세션을 확인
//        Object login = request.getSession().getAttribute("login");
//
//        if(login!=null) flag=true;

        // 쿠키를 확인
//        Cookie[] cookies = request.getCookies();
//        for (Cookie c : cookies) {
//            if (c.getName().equals("login")) {
//                flag = true;
//                break;
//            }
//        }
//        if(!flag) return "redirect:/members/sign-in";


        log.info("/board/list : GET");
        log.info("page : {}", page);
        List<BoardListResponseDTO> responseDTOS
                = boardService.getList(page);

        // 페이징 알고리즘 작동
        PageMaker maker = new PageMaker(page, boardService.getCount(page));
        //301에 대한건 db에 관련된 정보이니 서비스한테 요청해야함

        model.addAttribute("bList", responseDTOS);
        //jsp에게 주려면 모델에 담아야함
        model.addAttribute("maker", maker);
        //기존에 검색한 내용 그대로 남아있게하기
        model.addAttribute("s", page);
        return "chap05/list";
    }

    // 글쓰기 화면 조회 요청
    @GetMapping("/write")
    public String write(HttpSession session) {
//        if (!LoginUtil.isLogin(session)) {
//            return "redirect:/members/sign-in";
//        }


        System.out.println("/board/write : GET");
        return "chap05/write";
    }

    // 글 등록 요청 처리
    @PostMapping("/write")
    public String write(BoardWriteRequestDTO dto, HttpSession session) {

        System.out.println("/board/write : POST");
        boardService.register(dto, session);
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
    public String detail(int bno, @ModelAttribute("s") Search search, Model model) { //글번호내놔봐, model에 넣기
        System.out.println("/board/detail : GET");
        BoardDetailResponseDTO detail = boardService.getDetail(bno);
        model.addAttribute("b", detail);
//        model.addAttribute("s", search);

        return "chap05/detail";
    }

}


