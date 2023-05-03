package com.spring.mvc.chap06;

import com.spring.mvc.jdbc.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
@RestController //controller랑 responseBody랑 같음
@RequestMapping("/rests")
public class RestApiController {

    @GetMapping("/hello")
//restAPI json형식으로 변환되는 스프링 프레임워크@ - 순수문자열을 보내줌
    public String hello() {
        return "안녕하세요";
    }

    @GetMapping("/foods")
    public List<String> foods() {
//    String[] foodList= {"탕수육", "짜장면", "마라탕"};
        List<String> foodList = List.of("탕수육", "짜장면", "마라탕");
        return foodList;
        /*
        1)
        public String[] foods () {
            String[] foodList = {"탕수육", "짜장면", "마라탕"};
            return foodList;

        2)
            public List<String> foods () {
                List<String> foodList = List.of("탕수육", "짜장면", "마라탕");
                return foodList;

                첫 번째 코드는 "foods"라는 이름의 메서드를 정의합니다.
                이 메서드는 String 배열을 반환합니다. 배열의 요소로는 "탕수육", "짜장면" 및 "마라탕" 세 가지 종류의 음식이 있습니다.
                배열은 "foodList"라는 변수로 초기화됩니다.

두 번째 코드는 또 다른 "foods" 메서드를 정의합니다.
이 메서드는 List<String>을 반환합니다. 이 리스트는 "탕수육", "짜장면", "마라탕" 세 가지 종류의 음식을 포함하는 불변 리스트입니다.
이 리스트는 "foodList" 변수에 List.of() 메서드를 사용하여 직접 초기화됩니다.

그러므로 두 번째 코드는 Java 9 이상에서 사용 가능한 List.of() 메서드를 사용하여 간결하게 리스트를 초기화합니다.
반면 첫 번째 코드는 Java 8 이하에서도 사용 가능한 일반적인 배열을 사용합니다.
또한, 배열은 고정된 크기를 가지며 요소를 추가, 삭제 또는 변경하는 데 제한이 있습니다.
하지만 List는 크기가 동적이며 요소를 추가, 삭제 또는 변경하는 데 더 유연합니다.
따라서 두 번째 코드는 더 많은 기능과 유연성을 제공합니다.

        배열로 넣으면 [ ] 이렇게 출력되고, 객체로 넣으면  { } 이렇게 출력되는건가?
*/
    }

    @GetMapping("/person")
    public Person person() {
        Person p = new Person(1L, "루피", 3);
        return p;
    }

    @GetMapping("/person-list")
    public ResponseEntity<?> personList() {
        Person p = new Person(1L, "루피", 3);
        Person p2 = new Person(2L, "뽀로로", 4);
        Person p3 = new Person(3L, "크롱", 5);

        return ResponseEntity.ok().body(personList());
    }

    @GetMapping("/bmi")
    public ResponseEntity<?> bmi(
//            reponseEntity를 사용하면 내가 에러 상태를 정할 수 있음 ==== 이걸로 통일하자
//            400번 에러면 badRequest 고, 200번 정상으로 되면 ok코드
//            명세서를 보면 클라이언트한테  성공을 응답해줄 때는  헤더에 ㅇㅇ정보를 추가해서 응답하세요 라는 경우가 있는데
//            그런 경우에 ResponseEntity를 사용하면 된다
            @RequestParam(required = false) Double cm,
            @RequestParam(required = false) Double kg) {
        if (cm == null || kg == null) {
            return ResponseEntity.badRequest().body("키랑 몸무게 정보가 없습니다");
        }

        double bmi = kg / (cm / 100) * (cm / 100);
        //ResponseEntity를 사용하면 내가 클라이언트한테 추가적인 정보를 줄 수 있음 - f12 - network - 200클릭
        //성공하면 과일이랑 취미를 붙여서 보내줄게
        HttpHeaders headers = new HttpHeaders();
        headers.add("fruits", "melon");
        headers.add("hobby", "soccer");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bmi);

        //return ResponseEntity.ok().body(bmi);
    }


}



