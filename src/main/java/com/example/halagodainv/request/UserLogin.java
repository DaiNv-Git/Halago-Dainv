package com.example.halagodainv.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLogin {
    @NotBlank
    private String loginAccount;

    @NotBlank
    private String password;
}
