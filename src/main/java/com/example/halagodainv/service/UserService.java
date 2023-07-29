package com.example.halagodainv.service;

import com.example.halagodainv.model.UserEntity;
import com.example.halagodainv.request.UserAddRequest;
import com.example.halagodainv.request.UserEditRequest;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface UserService {
    Object getAll(int pageNo, int pageSize, String userName);

    Object getDetail(int userId);

    Object addUser(UserAddRequest userAddRequest);

    Object updateUser(UserEditRequest userEditRequest);

    void deleteUser(int userId);
    Object getRole();
    void updateResetPasswordToken(String token, String email);

    void sendEmail(String recipientEmail, String code)
            throws MessagingException, UnsupportedEncodingException ;
    void updatePassword(UserEntity customer, String newPassword);
}
