package com.hitss.AcademyPlatform.dto.login;

import lombok.Data;


@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}