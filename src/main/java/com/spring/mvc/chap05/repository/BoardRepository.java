package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;

import java.util.List;
//추가적으로 수정, 정렬 , 검색, 상승 기능 추가하기

public interface BoardRepository {

    // 게시물 목록 조회 -- 변수 작성 후 게시판의 목록을 배열로 받는다
    List<Board> findAll();

    // 게시물 상세 조회 -- 게시판의 글번호를 받아서 게시물을 준다
    Board findOne(int boardNo);

    // 게시물 등록 -- 게시물 정보가 통채로 필요함
    boolean save(Board board);

    // 게시물 삭제 -- 글번호로 게시물 삭제
    boolean deleteByNo(int boardNo);

}