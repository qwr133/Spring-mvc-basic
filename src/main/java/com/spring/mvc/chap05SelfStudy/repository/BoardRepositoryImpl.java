package com.spring.mvc.chap05SelfStudy.repository;

import com.spring.mvc.chap05SelfStudy.entity.Board;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BoardRepositoryImpl implements BoardRepository{

    private final   static Map<Integer, Board> boardMap;
    private static int sequence;
    static{
        boardMap = new HashMap<>();
        Board b1 = new Board(++sequence, "가가가", "가가가가가가", );
    }

}
