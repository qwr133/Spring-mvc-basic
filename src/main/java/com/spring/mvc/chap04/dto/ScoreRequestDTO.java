package com.spring.mvc.chap04.dto;

import lombok.*;

@Setter@Getter
@NoArgsConstructor@AllArgsConstructor
@EqualsAndHashCode@ToString

//dto는 클라이언트가 주는 데이터만 가지고 있음 그래서 실무입장에서는 dto를 따로 만듬 (보안강화효과)

//spring은 allarg로 안쓰고 noarg로 한 후 setter를 사용함
//그러므로 noArgs,AllArgs랑 같아야함
public class ScoreRequestDTO {
    private String name;

    private int kor, eng, math;

}
