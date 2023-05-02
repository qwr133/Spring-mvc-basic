package com.spring.mvc.chap05.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter //검색으로 setter 입력하기 때문에
//상속받으면서 pageNo와 amount를 가져옴
public class Search extends Page {

    //검색타입, 검색키워드

    private String type;
    private String keyword;

    //검색을 안했을 때 기본값 사용
    public Search() {
        this.type="";
        this.keyword="";
    }



}
