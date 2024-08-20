package com.example.halagodainv.service;

import com.example.halagodainv.model.UserEntity;
import com.example.halagodainv.request.UserAddRequest;
import com.example.halagodainv.request.UserEditRequest;
import com.example.halagodainv.request.UserLogin;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface UserService {
    Object getAll(int pageNo, int pageSize, String userName);

    Object getDetail(int userId);

    Object addUser(UserAddRequest userAddRequest);

    ResponseEntity<?> login(UserLogin userLogin);

    Object updateUser(UserEditRequest userEditRequest);

    void deleteUser(int userId);

    Object getRole();

    void updateResetPasswordToken(String token, String email);

    void sendEmail(String recipientEmail, String code)
            throws MessagingException, UnsupportedEncodingException;

    void updatePassword(UserEntity customer, String newPassword);

    Object sendByCode(String recipientEmail, String code) throws MessagingException, UnsupportedEncodingException;

    boolean isCheckCode( String code, String email);
}
