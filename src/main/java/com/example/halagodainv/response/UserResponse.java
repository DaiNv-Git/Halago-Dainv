package com.example.halagodainv.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int id;
    private String userName;
    private String email;
    private int roleId;
    private String token;
    private String refreshToken;
}
