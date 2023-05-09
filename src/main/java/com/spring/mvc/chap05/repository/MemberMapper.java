package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    //회원 가입
    boolean save(Member member);

    //회원 정보 조회
    Member findMember(String account); //pk로 찾기

    //중복체크(account, email) 기능
    int isDuplicate(
            @Param("type") String type,
            @Param("keyword") String keyword);
         //email, name, 등 , 중복값인지 체크하는 값!
        //중복검사시, 값이 두개이면 param을 꼭 작성해야한다

}
