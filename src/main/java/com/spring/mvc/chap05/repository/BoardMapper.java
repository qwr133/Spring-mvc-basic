package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
//추가적으로 수정, 정렬 , 검색, 상승 기능 추가하기

@Mapper
public interface BoardMapper {

    // 게시물 목록 조회 -- 변수 작성 후 게시판의 목록을 배열로 받는다
//    List<Board> findAll();

    //BoardMapper.xml 쿼리문에 page index, amount 작성했기 때문에 파라미터 가져와야함
    List<Board> findAll(Page page);

    // 게시물 상세 조회 -- 게시판의 글번호를 받아서 게시물을 준다
    Board findOne(int boardNo);

    // 게시물 등록 -- 게시물 정보가 통채로 필요함
    boolean save(Board board);

    // 게시물 삭제 -- 글번호로 게시물 삭제
    boolean deleteByNo(int boardNo);

    //조회수 상승
    void upViewCount(int boardNo);


    //수정
    void modify(Board board);

    //총 게시물 수 조회하기
    int count();
}