package com.example.halagodainv.request;

import lombok.Data;

@Data
public class UserEditRequest {
    private int id;
    private String password;
    private String email;
    private String userName;
    private int role;
}
