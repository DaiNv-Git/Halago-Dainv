package com.example.halagodainv.service;

import com.example.halagodainv.request.UserAddRequest;
import com.example.halagodainv.request.UserEditRequest;

public interface UserService {
    Object getAll(int pageNo, int pageSize,String userName);
    Object addUser(UserAddRequest userAddRequest);
    Object updateUser(UserEditRequest userEditRequest);
    void deleteUser(int userId);
}
