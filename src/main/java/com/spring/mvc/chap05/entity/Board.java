package com.spring.mvc.chap05.entity;

import com.spring.mvc.chap05.dto.request.BoardWriteRequestDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

//    create table tbl_board (
//            board_no int(10) auto_increment primary key,
//    title VARCHAR(80) not null,
//            -- --varchar(4000) -> 약 1200자 정도, 논문 그 이상은 clob으로 (용량차지up)
//    content VARCHAR(2000),
//    view_count int(10) default 0,
//    reg_date_time DATETIME default current_timestamp
//);


    private int boardNo; // 게시글 번호
    private String title; // 제목
    private String content; // 내용
    private int viewCount; // 조회수
    private LocalDateTime regDateTime; // 작성일자시간

    //5/12 오후 인증인가
    private String account; //작성자 계정명
    private String writer; //작성자 이름

    //기본적으로 화면에 보여질 변수만 따로 생성자를 만듬
    public Board(int boardNo, String title, String content) {
        this.boardNo = boardNo;
        this.title = title;
        this.content = content;
        this.regDateTime = LocalDateTime.now();
    }

    public Board(BoardWriteRequestDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.regDateTime = LocalDateTime.now();
    }

    public static void findOne(int boardNo) {
    }
}



