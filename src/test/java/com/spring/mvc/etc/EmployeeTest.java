package com.spring.mvc.etc;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void test(){


        Employee e = Employee.builder()
                .position("대리")
                .empName("홍길동")
                .hireDate(LocalDate.of(2022,3,16))
                .empId(300)
                .build();
        System.out.println("e = "+ e);


        Actor.builder()
                .actorName("현빈")
                .hasPhone(false)
                .actorAge(40)
                .build();
    }

}