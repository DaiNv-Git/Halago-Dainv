package com.example.halagodainv.service.auth;

import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.UserEntity;
import com.example.halagodainv.repository.RoleRepository;
import com.example.halagodainv.repository.UserRepository;
import com.example.halagodainv.request.UserAddRequest;
import com.example.halagodainv.request.UserEditRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Object getAll(int pageNo, int pageSize) {
        try {
            int offset = 0;
            if (pageNo > 0) {
                offset = pageNo - 1;
            }
            Pageable pageable = PageRequest.of(offset, pageSize, Sort.Direction.DESC, "id");
            if (CollectionUtils.isEmpty(userRepository.getAll(pageable))){
                PageResponse pageResponse = new PageResponse<>(new PageImpl<>(userRepository.getAll(pageable), pageable, 0));
                return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu thành công", pageResponse);
            }
            PageResponse pageResponse = new PageResponse<>(new PageImpl<>(userRepository.getAll(pageable), pageable, userRepository.totalElementAll()));
            return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu thành công", pageResponse);
        } catch (Exception exception) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lấy dữ liệu thất bại", null);
        }
    }


    public Object addUser(UserAddRequest userAddRequest) {
        try {
            Optional<UserEntity> userDetails = userRepository.findByEmail(userAddRequest.getEmail());
            if (userDetails.isPresent()) {
                return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Email này đã tồn tại", null);
            }
            UserEntity user = new UserEntity();
            user.setUserName(userAddRequest.getUserName());
            user.setEmail(userAddRequest.getEmail());
            user.setPassword(passwordEncoder.encode(userAddRequest.getPassword()));
            user.setPasswordHide(userAddRequest.getPassword());
            user.setRole(userAddRequest.getRole());
            user.setCreated(new Date());
            user = userRepository.save(user);
            return new BaseResponse<>(HttpStatus.OK.value(), "Sửa dữ liệu thành công", userRepository.getUser(user.getId()));
        } catch (Exception exception) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Sửa dữ liệu không thành công", null);
        }
    }

    public Object updateUser(UserEditRequest userEditRequest) {
        try {
            Optional<UserEntity> userDetails = userRepository.findById(userEditRequest.getId());
            if (!userDetails.isPresent()) {
                return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Email này không tồn tại", null);
            }
            Optional<UserEntity> user = userRepository.findById(userEditRequest.getId());
            user.get().setEmail(userEditRequest.getEmail());
            user.get().setPassword(passwordEncoder.encode(userEditRequest.getPassword()));
            user.get().setPasswordHide(userEditRequest.getPassword());
            user.get().setRole(userEditRequest.getRole());
            userRepository.save(user.get());
            return new BaseResponse<>(HttpStatus.OK.value(), "Thêm dữ liệu thành công", userRepository.getUser(user.get().getId()));
        } catch (Exception exception) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Thêm dữ liệu thất bại", null);
        }
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}
