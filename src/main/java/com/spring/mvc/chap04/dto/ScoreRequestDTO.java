package com.spring.mvc.chap04.dto;

import lombok.*;

@Setter@Getter
@NoArgsConstructor@AllArgsConstructor
@EqualsAndHashCode@ToString

//spring은 allarg로 안쓰고 noarg로 한 후 setter를 사용함
//그러므로 noArgs,AllArgs랑 같아야함
public class ScoreRequestDTO {
    private String name;

    private int kor, eng, math;

}
