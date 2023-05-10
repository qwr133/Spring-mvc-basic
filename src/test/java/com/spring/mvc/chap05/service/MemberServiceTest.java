package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.LoginRequestDTO;
import com.spring.mvc.chap05.dto.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("signUpDTO를 전달하면 회원가입에 성공해야한다")
    void joinTest() {
        SignUpRequestDTO dto = new SignUpRequestDTO();
        dto.setAccount("KilookKillook22");
        dto.setPassword("abc1234");
        dto.setName("끼루룩22");
        dto.setEmail("abc@abc22.com");

        memberService.join(dto);
    }

    @Test
    @DisplayName("계정명이 abc123인 회원의 로그인시도 결과 검ㅈ으을 상황별로 수행해야한다")
    void loginTest(){
        //given
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setAccount("abc123");
        dto.setPassword("abc123@");

        //when
        LoginResult result = memberService.authenticate(dto);

        //then
        assertEquals(LoginResult.SUCCESS, result);
    }

}