package com.spring.mvc.chap04.repository;


import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.ui.Model;

import java.util.List;

//역할 명세 (추상화)
// 성적 정보를 잘 저장하고 추가하고 삭제하고 수정해야한다
//그래서 어디에 저장하는건데?
//어디에서 조회하고 삭제하니?
//A. 너 알아서 해~~ 그냥 역할만 알아서 알려줘~~
public interface ScoreRepository {

    //성적 정보 전체 목록 조회
    List<Score> findAll(); //일반목록조회
    //알아서 정보알려주고 list로 담아오기만해~~

   default List<Score> findAll(String sort){
       return null;
   }; //정렬조회 (선택적으로 오버라이딩 가능) --오버라이딩 강제를 방지하기 위해 defaul 사용

    //성적정보등록
    boolean save(Score score);

    //성적 정보 한개 삭제
    boolean deleteByStuNum(int stuNum);

    //성적 정보 개별 조회
    Score findByStuNum(int stuNum);

//    Boolean update(int stuNum, ScoreRequestDTO dto);

}



