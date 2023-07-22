package com.example.halagodainv.service;

import com.example.halagodainv.model.UserEntity;
import com.example.halagodainv.request.UserAddRequest;
import com.example.halagodainv.request.UserEditRequest;

public interface UserService {
    Object getAll(int pageNo, int pageSize, String userName);

    Object getDetail(int userId);

    Object addUser(UserAddRequest userAddRequest);

    Object updateUser(UserEditRequest userEditRequest);

    void deleteUser(int userId);
    Object getRole();
    void updateResetPasswordToken(String token, String email);

    UserEntity getByResetPasswordToken(String token);

    void updatePassword(UserEntity customer, String newPassword);
}
