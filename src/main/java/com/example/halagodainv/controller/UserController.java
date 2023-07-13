package com.example.halagodainv.controller;

import com.example.halagodainv.config.filter.JwtToken;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.UserEntity;
import com.example.halagodainv.repository.UserRepository;
import com.example.halagodainv.request.UserLogin;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.UserResponse;
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

    @PostMapping("/login")
    public ResponseEntity<Object> user(@Valid @RequestBody UserLogin userLogin) {
        try {
            Pattern pattern = Pattern.compile("@");
            Matcher matcher = pattern.matcher(userLogin.getLoginAccount());
            int count = 0;
            while (matcher.find()) {
                count++;
            }
            UserDetails userDetails;
            if (count == 1) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getLoginAccount(), userLogin.getPassword()));
                userDetails = authConfig.loadUserByUsername(userLogin.getLoginAccount());
            } else {
                userDetails = authConfig.loadByUserName(userLogin.getLoginAccount());
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userLogin.getPassword()));
            }
            Optional<UserEntity> userEntity = userRepository.findByEmail(userDetails.getUsername());
            String token = jwtToken.generateToken(userDetails);
            String refreshToken = jwtToken.generateRefreshToken(userDetails);
            return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "Đăng nhập thành công", new UserResponse(userEntity.get().getUserName(), token, refreshToken)));
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
            String newToken = jwtToken.generateToken(userDetails);
            String refreshTokenNew = jwtToken.generateRefreshToken(userDetails);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(HttpStatus.OK.value(), "login success", new UserResponse(userEntity.get().getUserName(), newToken, refreshTokenNew)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());

        }
    }
}