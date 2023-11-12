package com.huangbusiness.common;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserEntryDto {

    public static interface UserRegisterAction {
    }

    @Email
    @NotBlank
    private String email;

    @NotBlank(groups = UserRegisterAction.class)
    private String name;

    @NotBlank
    private String password;
}