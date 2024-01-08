package com.example.halagodainv.request;

import lombok.Data;

@Data
public class UserAddRequest {
    private String userName;
    private String password;
    private String passwordConfirm;
    private String email;
    private String phone;
    private int roleId;
}
