package com.example.halagodainv.controller;

import com.example.halagodainv.config.filter.JwtToken;
import com.example.halagodainv.dto.user.UserDto;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.UserEntity;
import com.example.halagodainv.repository.UserRepository;
import com.example.halagodainv.request.UserAddRequest;
import com.example.halagodainv.request.UserEditRequest;
import com.example.halagodainv.request.UserLogin;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.UserResponse;
import com.example.halagodainv.service.UserService;
import com.example.halagodainv.service.auth.UserServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServiceConfig authConfig;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private UserService userService;

    @PostMapping("/insert")
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserAddRequest userAddRequest) {
        return ResponseEntity.ok(userService.addUser(userAddRequest));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> editUser(@Valid @RequestBody UserEditRequest userEditRequest) {
        return ResponseEntity.ok(userService.updateUser(userEditRequest));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> editUser(@RequestParam("userId") int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "Xóa thành công", null));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> user(@Valid @RequestBody UserLogin userLogin) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getLoginAccount(), userLogin.getPassword()));
            UserDetails userDetails = authConfig.loadUserByUsername(userLogin.getLoginAccount());
            Optional<UserEntity> userEntity = userRepository.findByEmailOrUserName(userDetails.getUsername(), userDetails.getUsername());
            UserDto user = userRepository.getUser(userEntity.get().getId());
            String token = jwtToken.generateToken(userDetails);
            String refreshToken = jwtToken.generateRefreshToken(userDetails);
            return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "Đăng nhập thành công", new UserResponse(user.getName(), user.getEmail(), user.getRole(), token, refreshToken)));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Đăng nhập không thành công", null));
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<BaseResponse<Object>> user(@RequestParam("refreshToken") String refreshToken) {
        try {
            String accessToken = jwtToken.getUserNameFromJWT(refreshToken);
            UserDetails userDetails = authConfig.loadUserByUsername(accessToken);
            Optional<UserEntity> userEntity = userRepository.findByEmail(userDetails.getUsername());
            UserDto user = userRepository.getUser(userEntity.get().getId());
            String newToken = jwtToken.generateToken(userDetails);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(HttpStatus.OK.value(), "login success", new UserResponse(user.getName(), user.getEmail(), user.getRole(), newToken, refreshToken)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}