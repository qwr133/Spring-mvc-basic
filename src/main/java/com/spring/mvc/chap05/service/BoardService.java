package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor //서비스 주입을 위해서 롬복으로 생성자 주입
public class BoardService {

    private final BoardRepository boardRepository;
    //레파짓토리에 의존하고 레파짓토리의 역할에 의존하기


    // 중간처리 기능 자유롭게 사용
    // 목록 중간처리
    public List<BoardListResponseDTO> getList() {
    //람다식을 기본 문법 코드로 보고싶을 때 alt+enter - 스트림 API체인을 루프로 바꾸기 엔터
        return boardRepository.findAll()
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(toList())
                ;
    }

    // 글 등록 중간처리
    public boolean register(BoardWriteRequestDTO dto) {
        return boardRepository.save(new Board());
    }

    public boolean delete(int bno) {
        return boardRepository.deleteByNo(bno);
    }

    public BoardDetailResponseDTO getDetail(int bno) {

        Board board = boardRepository.findOne(bno);
        // 조회수 상승 처리
        board.setViewCount(board.getViewCount() + 1);

        return new BoardDetailResponseDTO(board);
    }
}