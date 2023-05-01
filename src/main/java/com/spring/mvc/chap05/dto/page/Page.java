package com.spring.mvc.chap05.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @ToString @Setter
public class Page {

    private int pageNo; //클라이언트가 보낸 페이지 번호 - 1
    private  int amount; //클라이언트가 보낸 목록 게시물 수 -6

    public Page(){ // 기본 디폴트 값
        this.pageNo = 1;
        this.amount=6;
    }

    public void setPageNo(int pageNo){
        if(pageNo<1 || pageNo> Integer.MAX_VALUE){
            this.pageNo=1;
            return;
        }
        this.pageNo = pageNo;
    }

    public void setAmount(int amount){
        if(amount<6 || amount>10){
            this.amount =6;
            return;
        }
        this.amount = amount;
    }

    public int getPageStart(){
        /*
            pageNo : 1-> return 0
            pageNo : 2-> return 6
            pageNo : 3-> return 12
            pageNo : n-> return (n-1)*6
         */
        return (pageNo - 1) * amount;
    }

}
