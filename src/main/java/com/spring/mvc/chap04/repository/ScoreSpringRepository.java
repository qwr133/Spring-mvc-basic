package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("spring")
@RequiredArgsConstructor
public class ScoreSpringRepository implements ScoreRepository {
    //작성x
    @Override
    public List<Score> findAll() {
        return null;
    }

    @Override
    public List<Score> findAll(String sort) {
        return ScoreRepository.super.findAll(sort);
    }

    @Override
    public boolean save(Score score) {
        return false;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        return false;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        return null;
    }
}
