package com.spring.mvc.chap05SelfStudy.entity;

import lombok.*;

import java.time.LocalDateTime;

//보드는 모든 데이터이고 dto는 내가 필요한 정보만 보여지는 데이터
//그러면 보드는 무조건 있어야하고, dto는 내가 커스트마이즈 해야한다
//함수를 불러와서 사용할 시에 allArgsConstructor로 모든 생성자 초기화 했으니
// 내가 필요한 생성자만 꺼내서 커스텀 해둘 수 있음


@Setter @Getter @ToString @EqualsAndHashCode
@NoArgsConstructor //파라미터가 없는 기본생성자를 생성
@AllArgsConstructor //모든 필드 값을 받는 기본생성자 생성
public class Board {

    private int boardNo; // 게시글 번호
    private String title; // 제목
    private String content; // 내용
    private int viewCount; // 조회수
    private LocalDateTime regDateTime; // 작성일자시간

    public Board(int boardNo, String title, String content, LocalDateTime regDateTime) {
        this.boardNo = boardNo;
        this.title = title;
        this.content = content;
        this.regDateTime = regDateTime;
    }
}
