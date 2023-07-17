package com.example.halagodainv.request;

import lombok.Data;

@Data
public class UserAddRequest {
    private String userName;
    private String password;
    private String email;
    private int role;
}
