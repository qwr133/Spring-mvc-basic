package com.spring.mvc.chap04.entity;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class Score {
    //클라이언트가 주는 데이터
    private String name;
    private int kor, eng, math; //국영수 점수


    //서버가 주는 데이터
    private int stuNum; //학번
    private int total; //총점
    private double average;
    private Grade grade; //학점


    public Score(ScoreRequestDTO dto) {
        this.name = dto.getName();
        this.kor = dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();

        calcTotalAndAvg(); //총점, 평균 계산 ctrl+alt+m
        calcGrade(); //학점계산
    }

    //리펙토링 changeScore부분 추가하기


    private void calcGrade() {
        if (average >= 90) {
            this.grade = Grade.A;
        } else if (average >= 80) {
            this.grade = Grade.B;
        } else if (average >= 70) {
            this.grade = Grade.C;
        } else if (average >= 60) {
            this.grade = Grade.D;
        } else {
            this.grade = Grade.F;
        }


    }
    private void calcTotalAndAvg() {
        this.total = kor + eng + math;
        this.average = total / 3.0;
    }


}
