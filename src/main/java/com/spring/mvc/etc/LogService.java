package com.spring.mvc.etc;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j //로그 라이브러리
public class LogService {

    /*
        -로그: 발생시간, 로그 레벨, 파일저장
        -로그 라이브러리: logback, log4j, slf4j

        - 로그 레벨
        1. trace  : 애플리케이션 실행 흐름 세부정보, 디버깅목적 ( ㅇㅇ함수가 잡혔구나)
        2. debug  : 변수값, 파라미터값 내부 정보출력, 디버깅목적
       ==============운영 서버 ▼ ==========
        3. info   :  운영환경에서 일반적인 작동정보들(시스템 상태나 진행적인 작업 정보) -- 일반적으로 애매하면 info
        ===========개발 서버  ▲ =============

        4. warn   :  문재적인 문제상항을 겪고, 구성값이 예상 범위를 벗어났거나 시스템 리소스 부족
        5. error  :  예외가 발생하거나 기능이 실패했을 때 심각한 문제 상황
        6. fatal  : 치명적인 오류 시스템이 지속될 수 없는 상황, 즉각 조치가 필요한 경우

     */
        public void showLog(){
            System.out.println("hello log");

            log.trace("hello trace!");
            log.debug("hello debug!");
            log.info("hello info!");
            log.warn("hello warn!");
            log.error("hello error!");
        }



}
