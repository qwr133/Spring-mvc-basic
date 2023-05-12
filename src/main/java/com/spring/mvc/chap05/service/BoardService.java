package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.response.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor //서비스 주입을 위해서 롬복으로 생성자 주입
public class BoardService {

//    private final BoardRepository boardRepository;
    //레파짓토리에 의존하고 레파짓토리의 역할에 의존하기
    private final BoardMapper boardRepository;


    // 중간처리 기능 자유롭게 사용
    // 목록 중간처리
    public List<BoardListResponseDTO> getList(Search page) { //클라이언트한테 받아야하기 때문에 page 파라미터삽입
                                            //군데 서비스가 컨트롤러에게 의존하기때문에 controller에서도 page 삽입필요
    //람다식을 기본 문법 코드로 보고싶을 때 alt+enter - 스트림 API체인을 루프로 바꾸기 엔터
        return boardRepository.findAll(page)
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(toList())
                ;
    }

    // 글 등록 중간처리
    public boolean register(BoardWriteRequestDTO dto, HttpSession session) {
        Board board = new Board(dto);
        board.setAccount(LoginUtil.getCurrentLoginMemberAccount(session));
        return boardRepository.save(board);
    }


    public boolean delete(int bno) {
        return boardRepository.deleteByNo(bno);
    }

    public BoardDetailResponseDTO getDetail(int bno) {

        Board board = boardRepository.findOne(bno);
        // 조회수 상승 처리
//        board.setViewCount(board.getViewCount() + 1);
        boardRepository.upViewCount(bno);

        return new BoardDetailResponseDTO(board);
    }


    public void modify(BoardWriteRequestDTO boardNo){
       boardRepository.modify(new Board(boardNo));
    }

    public int getCount(Search search) {
        return boardRepository.count(search);
    }

}