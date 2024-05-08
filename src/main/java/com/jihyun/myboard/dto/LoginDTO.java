package com.jihyun.myboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    private String usernameIn;

    private String passwordIn;

    private String role;

    private String jwt;
}
