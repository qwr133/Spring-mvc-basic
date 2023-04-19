package com.spring.mvc.chap01;

//어떤 요청들을 처리할지 공통 URL을 설계

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    ){
        System.out.println(" stu = "+ stu);
        System.out.println(" major = "+ mj);
        System.out.println(" grade = "+ grade);


        return "";


    }
}
