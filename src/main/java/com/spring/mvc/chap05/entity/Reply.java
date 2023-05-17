package com.spring.mvc.chap05.entity;
/*
create table tbl_reply(
        reply_no INT(10) auto_increment,
        reply_text VARCHAR(1000) not null,
        reply_writer VARCHAR(100) not null,
        reply_date DATETIME default current_timestamp,
        board_no int(10),
        -- 1대 다 관계로 게시글 번호가 필수적으로 필요함 (PK)
        constraint pk_reply primary key (reply_no),
        constraint fk_reply
        foreign key (board_no)
        references tbl_board(board_no)
        -- 	글 삭제하면 댓글도 다 삭제됨, 이거 없으면 댓글 있으면 게시물 다 삭제됨
        -- 	--실무적으론 백업테이블에 넣어두고, 삭제하는 방식으로 하게 됨 - 조심해야함
        on delete cascade
        );
*/

import lombok.*;

import java.time.LocalDateTime;
@Setter @Getter @ToString
@NoArgsConstructor @EqualsAndHashCode
@AllArgsConstructor @Builder
public class Reply {

private long replyNo;
private String replyText;
private String replyWriter;
private LocalDateTime replyDate;
private long boardNo;
private String profile;


}
