package com.spring.mvc.chap05.dto.response;

import com.spring.mvc.chap05.entity.Board;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//화면에 보여지는 정보 (클라이언트에게 게시글 목록을 줄 때 스펙) - db저장 데이터를 클라이언트에게 넘겨줄때 스펙
@Getter @ToString
@EqualsAndHashCode
public class BoardListResponseDTO {

    private final int boardNo;
    private final String shortTitle; // 5자 이상 줄임
    private final String shortContent; // 30자 이상 줄임
    private final String date; // 날짜패턴 yyyy-MM-dd HH:mm
    private final int viewCount;

    public BoardListResponseDTO(Board board) {
        this.boardNo = board.getBoardNo(); //원본 글번호 그대로 세팅
        this.shortTitle = makeShortTitle(board.getTitle()); //원본 타이틀에서 shortTitle로 변환 (하단에 함수)
        this.shortContent = makeShortContent(board.getContent()); //이하 동일
        this.date = makePrettierDateString(board.getRegDateTime()); //하단에 함수 작성
        this.viewCount = board.getViewCount();
    }

    static String makePrettierDateString(LocalDateTime regDateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); //날짜 데이터를 포맷으로 바꿈
                                                                                    // (java day11 -api - time - DateTime)
        return pattern.format(regDateTime);
    }

    private String makeShortContent(String originContent) {
        return sliceString(originContent, 30);
    }


    private String makeShortTitle(String originTitle) {
        return sliceString(originTitle, 7);

    }

    /**
     *
     * @param targetString : 줄이고 싶은 원본 문자열
     * @param wishLength : 짜르고 싶은 글자 수
     * @return : wishLength보다 targetString이 길면
     *              wishLength만큼 짤라서 뒤에 ... 붙여서 리턴
     */
    private static String sliceString(String targetString, int wishLength) { //String의 값을 가져와! wishLength는 화면에서 입력되는 값
                                    //자르고 싶은 문자열 주고, 자르고 싶은 길이 입력해라
        return (targetString.length() > wishLength) //원본 타겟 글자의 길이가 wishlength의 길이보다 크면 잘라주겠다, 안크면 그대로 입력
                ? targetString.substring(0, wishLength) + "..."
                : targetString;
    }

}