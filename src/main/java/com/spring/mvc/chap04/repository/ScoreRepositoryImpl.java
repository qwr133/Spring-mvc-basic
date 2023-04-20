package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.spring.mvc.chap04.entity.Grade.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

@Repository //bean은 bean인데 저장소 bean이다  그럼 component 대신에 모든걸 써도되는지??
@Component //new 객체 안만들고 spring 너가 알아서 해줘
          // -- spring bean 등록: 객체 생성의 제어권을 스프링에게 위임
public class ScoreRepositoryImpl implements ScoreRepository {

    // key: 학번, value: 성적정보
    private static final Map<Integer, Score> scoreMap;

    // 학번에 사용할 일련번호
    private static int sequence;

    static {
        scoreMap = new HashMap<>();
        Score stu1 = new Score("뽀로로", 100, 50, 70, ++sequence, 0, 0, A);
        Score stu2 = new Score("춘식이", 33, 56, 12, ++sequence, 0, 0, A);
        Score stu3 = new Score("대길이", 88, 12, 0, ++sequence, 0, 0, A);

        scoreMap.put(stu1.getStuNum(), stu1);
        scoreMap.put(stu2.getStuNum(), stu2);
        scoreMap.put(stu3.getStuNum(), stu3);
    }

    @Override
    public List<Score> findAll() {
        return scoreMap.values()
                .stream()
                .sorted(comparing(Score::getStuNum))
                .collect(toList())
                ;
    }

    @Override
    public List<Score> findAll(String sort) {
        Comparator<Score> comparator = comparing(Score::getStuNum);
        switch (sort) {
            case "num":
                comparator = comparing(Score::getStuNum);
                break;
            case "name":
                comparator = comparing(Score::getName);
                break;
            case "avg":
                comparator = comparing(Score::getAverage).reversed();
                break;
        }
        return scoreMap.values()
                .stream()
                .sorted(comparator)
                .collect(toList())
                ;
    }

    @Override
    public boolean save(Score score) {
        if (scoreMap.containsKey(score.getStuNum())) {
            return false;
        }
        score.setStuNum(++sequence);
        scoreMap.put(score.getStuNum(), score);
//        System.out.println(findAll()); //확인 버튼 누르고 콘솔창에 데이터 넘어왔는지 확인하기
        return true;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        if (!scoreMap.containsKey(stuNum)) return false;
        scoreMap.remove(stuNum);
        return true;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        return scoreMap.get(stuNum);
    }

//    @Override
//    public boolean update (int stuNum, ScoreRequestDTO dto){
//
//    }


}