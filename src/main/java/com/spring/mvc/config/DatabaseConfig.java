package com.spring.mvc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration  //설정파일 선언
public class DatabaseConfig {


    //데이터 소스 설정: 데이터베이스 연결 정보
    // url: dbms가 설치된 경로
    //username : db 계정명
    //password : db password
    //driver clasee : DBMS마다 설치한 커넥터 드라이버

    //커넥션 풀 설정
    // db 접속시 사용하는 리소스를 관리하는 프로그램

    //spring boot 사용하지 않으면 이 부분 설정해야함
//    @Bean
//    public DataSource dataSource(){
//        //히카리 설정
//        HikariConfig config = new HikariConfig();
//        config.setUsername("root");
//        config.setPassword("1234");
//        config.setJdbcUrl("jdbc:mariadb://localhost:3306/spring");
//        config.setDriverClassName("org.mariadb.jdbc.Driver");
//
//        return new HikariDataSource(config);
//
//    }
}
