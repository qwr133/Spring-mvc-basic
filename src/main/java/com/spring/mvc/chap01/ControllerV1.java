package com.spring.mvc.chap01;

//어떤 요청들을 처리할지 공통 URL을 설계

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/spring/*") // /spring으로 시작하는건 내가 다 받을게!
//이 클래스의 객체를 스프링이 관리하고도록 빈을 등록
@Controller //@Component와 같은 개념인데 핸들링하기에 더 편함
public class ControllerV1 {

    //세부요청들은 메서드를 통해 처리
    @RequestMapping("/hello") // http://localhost:8181/hello
    public String hello(){
        System.out.println("\n====헬로 요청이 들어옴1 ====\n");
       //어떤 jsp 열어줄 경로를 적습니다
        return "hello";
    }

    // /spring/food 요청이 오면 food.jsp를 열어보세요

    @RequestMapping("/food")
    public String food(){
        System.out.println("\n === food.jsp 요청 들어옴 ===\n");
        return "chap01/food";
    }


    //============== 요청 파라미터 읽기(Query String parameter)
    //==========1. HttpServletRequest 사용하기
    //========> ex) /spring/person?name=kim&age=30

    @RequestMapping("/person")
    public String person(HttpServletRequest request){
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        System.out.println("name =" + name);
        System.out.println("age =" + age);

        return "";
    }

    //=====2. @RequestParam 사용하기
    // --> ex) /spring/major?stu=kim&major=globalbussiness&grade=3
    @RequestMapping("/major")
    public String major(
//            @RequestParam String stu,
            String stu, //아무런 추가사항이 없으면 Request Param 생략가능
            @RequestParam ("major") String mj,
            @RequestParam (defaultValue = "1") int grade //기본값을 1로 설정하는 옵션임
    ) {
        System.out.println(" stu = " + stu);
        System.out.println(" major = " + mj);
        System.out.println(" grade = " + grade);

        return "";

    }

    //==3. 커맨드 객체 이용하기 (ex, 게시판 글쓰기)
    //== 쿼리 스트링의 양이 너무 많을 경우 또는 연관성이 있을 경우
    //==> ex) /spring/order?oNum=20230419007-P&goods=구두&amount=3&price=500000....

        @RequestMapping("/order")
        public String order (OrderRequestDTO dto){
            System.out.println("dto = " + dto);
            return "";
    }

    //==4. url에 경로로 붙어있는 데이터 읽기
    //==> /spring/member/hong/107
    //     hong이라는 유저의 107번 게시글을 읽고싶음
    @RequestMapping("/member/{userName}/{bNo}")
    public String member(
            @PathVariable String userName, //@PathVariable { } 괄호 뒤에 부분을 읽어줌
            @PathVariable long bNo
    ){
        System.out.println("userName = " + userName);
        System.out.println("bNo = " + bNo);
        return "";
    }



    //음식선택요청처리 - food.jsp
    @PostMapping("/food-select") //아래 리쿼스트맵핑이랑 같은 내용 = post 방식으로만 받고싶다!!!
//    @RequestMapping(value = "/food-select", method =  RequestMethod.POST)
    public String foodSelect(String foodName, String category){
        System.out.println("foodname = " + foodName);
        System.out.println("category = " + category);
        return "";
    }

}
