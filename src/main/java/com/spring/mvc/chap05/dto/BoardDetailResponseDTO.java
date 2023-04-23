package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Board;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString @EqualsAndHashCode
//상세보기 시 조회는 제외하고 세부정보를 보고싶을 때 DTO를 다시 만들어서 작성 (BOARDSERVICE에 있음)
public class BoardDetailResponseDTO {

    private final int boardNo;
    private final String title;
    private final String content;
    private final String date;

    public BoardDetailResponseDTO(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.date = BoardListResponseDTO.makePrettierDateString(board.getRegDateTime()); // LISTDTO에서 private 대신 public 으로 하여 꺼내옴
                                                                        //현업에서는 클래스 하나 더 만드는것이 맞음
    }
}