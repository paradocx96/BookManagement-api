package com.paradocx.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSignUpDto {
    private String name;
    private String email;
    private String username;
    private String password;
}
