package com.spring.mvc.chap04.entity.controller;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/* 12:13분 수업 정리 리다이렉트 개념 정리
    #요청 URL
    1. 학생 성적정보 등록화면을 보여주고 및 성정정보 목록조회 처리
        /score/List : Get 방식 사용 (조회=GET)
    2. 성적 정보 등록 처리 요청
        /score/register: POST

    3. 성적정보 삭제 요청
        /score/remove: POST

    4. 성적정보 상세 조회 요청
        /score/detail : GET

 */

@Controller
@RequestMapping("/score")
//@AllArgsConstructor //클래스가 하나있기 때문에 autowired, 생성자 생략하고 바로 allArgs 사용이 가능함 : 모든 필드를 초기화하는 생성자
@RequiredArgsConstructor // : final 필드만 초기화하는 생성자

public class ScoreController {

    //저장소에 의존해 데이터를 받아서 클라이언트에게 응답할 수 있음
    private final ScoreRepository repository; //data가 변하지 않기 위해 final -- 객체 불변성 유지가능

    // @Autowired //spring이 저장소 객체를 주입  데이터 저장소 바뀌어도 컨트롤러 코드가 바뀔 일이 없음(OCP)
    //Autowired를 생략해도 돌아감 왜? -- 자동 autowired의 조건이 해당되기 때문
    // 자동 autowired란? 클래스의 생성자가 단 1개라면 자동으로 @autowired를 써줌
    // 여러개인 경우 해당 클래스에 하나씩 @autowired를 붙여줘야함
//    public ScoreController(ScoreRepository repository) {
//        this.repository = repository;
//    }

    //@RequestParam = num 부분 안넣으면 sort 부분이 null 값이 나옴 그래서 기본값을 입력하기 위함
    //성적등록화면 띄우기 + 정보목록조회
    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "num") String sort) {
        System.out.println("/score/list : GET!");
        System.out.println("정렬요구사항 =" + sort);

        List<Score> scoreList = repository.findAll(sort);
        model.addAttribute("sList", scoreList);

        return "chap04/score-list";
    }

    //2. 성적 정보 등록 처리 요청
    @PostMapping("/register")
    public String register(ScoreRequestDTO dto) {

        //입력데이터(쿼리스트링) 읽기 [httpServlet, @requestparam, @ ]
        System.out.println("/score/register: Post" + dto);

        //dto(ScoreDTO)를 entity(Score)로 변환해야함
        Score score = new Score(dto); //score에 생성자 만듬


        //save
        repository.save(score);

        /*
            등록요청에서 jsp 뷰 포워딩을 하면 갱신된 목록을 다시한번
            저장소에서 불러와 모델에 담는 추가적인 코드가 필요하지만

            리다이ㅔㄺ트를 통해서 위에서 만든 /score/List: GET
            을 자동으로 다시 보낼 수 있다면 번거로운 코드가 줄어들 수 있겠다.
         */

        return "redirect:/score/list "; //redirect: 요청url 작성 필요(jsp파일 작성X)
    }

    //3. 성적정보 삭제요청
    @GetMapping("/remove")
    public String remove(int stuNum) {
        System.out.println("/score/remove : GET");

        repository.deleteByStuNum(stuNum);
        return "redirect:/score/list";
    }


    //4, 성적정보 상세조회 요청
    @GetMapping("/detail")
    public String detail(int stuNum, Model model) {
        System.out.println("/score/detail : get");
        System.out.println(stuNum);

        Score byStuNum = repository.findByStuNum(stuNum);
        model.addAttribute("sName", byStuNum);

        return "chap04/score-detail";
    }

    //5. 수정
    @GetMapping("/modify")
    public String modify(int stuNum, Model model){
        System.out.println("/score/modify: get");
        System.out.println(stuNum);

        Score modifyNum = repository.findByStuNum(stuNum);
        model.addAttribute("s", modifyNum);


        return "chap04/score-modify";
    }


//
//    //5-2. 수정
//    @GetMapping("/modify")
//    public String modifyNextPage(int stuNum, ScoreRequestDTO dto){
//        System.out.println("/score/modify: post");
//        System.out.println(stuNum);
//
//        Score modifyNextPage = repository.findByStuNum(stuNum);
////        modifyNextPage.changeScore(dto);
//
//
//
//        return "chap04/score-modify";
//    }



}