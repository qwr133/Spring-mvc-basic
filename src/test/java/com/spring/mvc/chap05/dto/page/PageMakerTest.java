package com.spring.mvc.chap05.dto.page;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PageMakerTest {

    @Test
    void pageTest(){
        Page page = new Page();
        page.setPageNo(45);
        page.setAmount(10);

        PageMaker maker = new PageMaker(page, 284);
        System.out.println("maker = "+ maker);
    }

}