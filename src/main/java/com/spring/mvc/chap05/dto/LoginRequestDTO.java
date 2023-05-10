package com.spring.mvc.chap05.dto;

import lombok.*;

@Setter @NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class LoginRequestDTO {

    private String account;
    private String password;
    private boolean autoLogin;

}
