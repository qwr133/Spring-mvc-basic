package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//public static 붙이면 안됨!!!!!
class ScoreRepositoryImplTest {

    ScoreRepository repository = new ScoreRepositoryImpl();

    //단위 테스트 (Unit Test)
    //테스트 시나리오
    // - 단언(Assertion) 기법 (~있어야한다, like 화법X)
    @Test
    @DisplayName("저장소에서 findAll 호출하면 그 반환된 리스트에는 성적정보가 3개 있어야 한다")
    void findAllTest(){
        //given-when-then 패턴
        // given: 테스트를 위해 주어질 데이터(ex, parameter)

        //when: 테스트 실제 상황
        List<Score> scoreList = repository.findAll();

        //then: 테스트 결과 확인
        System.out.println(scoreList.size()==3);

        //나는 스코어리스트의 사이즈가 3인 것이 참이라고 단언한다 -- 값이 실패시 테스트 실패 안내 뜸, 성공 시 테스트통과
//        Assertions.assertTrue(scoreList.size()==3); t ,f 값만 나옴
        assertEquals(3,scoreList.size()); //내가 작성한 값, 실제 사이즈 값안내를 해줌

        //나는 리스트의 첫번째 객체의이름이 뽀로로라고 단언한다
        assertEquals("뽀로로", scoreList.get(0).getName());
    }


    @Test
    @DisplayName("나는 저장소에서 findByStuNum을 호출하여 학번이 2인 학생을 조회하면 그 학생의 국어 점수가 80점이고 이름이 에디여야 한다")
    void findOneTest(){
        //given 학번
        int stuNum=2;
        //when
        Score score = repository.findByStuNum(stuNum);
        //then
        assertEquals(80, score.getKor());
        assertEquals("에디", score.getName());
    }


    @Test
    @DisplayName("나는 저장소에서 findByStuNum을 호출하여 학번이 99인 학생을 조회하면 null로 리턴될 것이다")
    void findOneTest2(){
        //given 학번
        int stuNum=2;
        //when
        Score score = repository.findByStuNum(stuNum);
        //then
        assertNull(score);
    }

@Test
@DisplayName("저장소에서 학번이 2인 학생을 삭제한 후에 리스트를 전체 조회하면 성적의 개수가 2개일 것이고 " +
        "다시 2번학생을 조회했을 때 null이 반환되어야 한다")
    void deleteTest(){
        //given
        int stuNum=2;
        //when
        repository.deleteByStuNum(stuNum);
    List<Score> scoreList = repository.findAll();
    Score score = repository.findByStuNum(stuNum);

    //then
    assertEquals(2, scoreList.size());
    assertNull(score);

    scoreList.forEach(s-> System.out.println(s));
}

@Test
@DisplayName("새로운 성적정보를 save를 통해 추가하면 목록의 개수가 4개야아한다")
void saveTest() {
    // given
    Score score = new Score();
    score.setName("춘식이");
    score.setKor(100);
    score.setMath(50);
    score.setEng(0);
    // when
    boolean flag = repository.save(score);
    List<Score> scoreList = repository.findAll();

    // then
    assertEquals(4, scoreList.size());
    assertTrue(flag);
    assertEquals(4, scoreList.get(scoreList.size()-1).getStuNum());
}
}