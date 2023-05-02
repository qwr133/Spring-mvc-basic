package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {


    //댓글 등록
    boolean save(Reply reply);

    //댓글 수정
    boolean modify(Reply reply);

    //댓글 삭제
    boolean deleteOne(long replyNo);

    //댓글 개별 조회
    Reply findOne(long replyNo);
    //댓글 목록 조회

//    @Param() 사용이유 - reply와 board 매퍼에 각각 중복되는 변수가 있으므로 선언할 때 어느부분인지 잘 모름
    //그러므로 2개 이상일 때는 @Param(values)를 작성하여 구분짓게 함 - replyMapper.xml 에도 코드 수정되었음
    List<Reply> findAll(

          @Param("bn") long boardNo,
           @Param("p") Page page
    );

    //댓글 수 조회
    int count(long boardNo);
}
