package com.example.halagodainv.controller;

import com.example.halagodainv.config.filter.JwtToken;
import com.example.halagodainv.config.userconfig.UserAuthenLogin;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.RoleEntity;
import com.example.halagodainv.model.UserEntity;
import com.example.halagodainv.repository.RoleRepository;
import com.example.halagodainv.repository.UserRepository;
import com.example.halagodainv.request.UserAddRequest;
import com.example.halagodainv.request.UserEditRequest;
import com.example.halagodainv.request.UserLogin;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.UserResponse;
import com.example.halagodainv.service.UserService;
import com.example.halagodainv.service.auth.UserServiceConfig;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class UserController extends UserAuthenLogin {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServiceConfig authConfig;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/user")
    public ResponseEntity<Object> getAll(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "userName", defaultValue = "", required = false) String userName) {
        return ResponseEntity.ok(userService.getAll(pageNo, pageSize, userName));
    }

    @PostMapping("/detail")
    public ResponseEntity<Object> getDetail(@RequestParam("userId") int userId) {
        return ResponseEntity.ok(userService.getDetail(userId));
    }

    @PostMapping("/insert")
    public Object addUser(@Valid @RequestBody UserAddRequest userAddRequest) {
        return userService.addUser(userAddRequest);
    }

    @PostMapping("/insert-influencer")
    public Object addInfluencer(@Valid @RequestBody UserAddRequest userAddRequest) {
        return userService.addUser(userAddRequest);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> editUser(@Valid @RequestBody UserEditRequest userEditRequest) {
        return ResponseEntity.ok(userService.updateUser(userEditRequest));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> editUser(@RequestParam("userId") int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "Delete success", null));
    }

    @GetMapping("/role")
    public ResponseEntity<Object> getRole() {
        return ResponseEntity.ok(userService.getRole());
    }

    @PostMapping("/login")
    public ResponseEntity<?> user(@Valid @RequestBody UserLogin userLogin) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getLoginAccount(), userLogin.getPassword()));
            UserDetails userDetails = authConfig.loadUserByUsername(userLogin.getLoginAccount());
            Optional<UserEntity> userEntity = userRepository.findByEmailOrUserName(userDetails.getUsername(), userDetails.getUsername());
            if (userEntity.isPresent() && userEntity.get().getRoleId() == 4) {
                return ResponseEntity.ok(new ErrorResponse<>(HttpStatus.FORBIDDEN.value(), "Login not success", null));
            }
            String token = jwtToken.generateToken(userDetails);
            String refreshToken = jwtToken.generateRefreshToken(userDetails);
            return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "Login success", new UserResponse(userEntity.get().getId(), userEntity.get().getUserName(), userEntity.get().getEmail(), userEntity.get().getRoleId(), token, refreshToken)));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse<>(HttpStatus.FORBIDDEN.value(), "Login not success", null));
        }
    }

    @PostMapping("/login-campaign")
    public ResponseEntity<?> campaign(@Valid @RequestBody UserLogin userLogin) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getLoginAccount(), userLogin.getPassword()));
            UserDetails userDetails = authConfig.loadUserByUsername(userLogin.getLoginAccount());
            Optional<UserEntity> userEntity = userRepository.findByEmailOrUserName(userDetails.getUsername(), userDetails.getUsername());
            BaseResponse<Object> baseResponse = new BaseResponse<>();
            if (userEntity.isPresent()) {
                if (userEntity.get().getRoleId() == 2 || userEntity.get().getRoleId() == 3) {
                    return ResponseEntity.internalServerError().body(new ErrorResponse<>(HttpStatus.FORBIDDEN.value(), "Login not success", null));
                }
                String token = jwtToken.generateToken(userDetails);
                String refreshToken = jwtToken.generateRefreshToken(userDetails);
                baseResponse = new BaseResponse<>(HttpStatus.OK.value(), "Login success", new UserResponse(userEntity.get().getId(), userEntity.get().getUserName(), userEntity.get().getEmail(), userEntity.get().getRoleId(), token, refreshToken));
            }
            return ResponseEntity.ok(baseResponse);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorResponse<>(HttpStatus.FORBIDDEN.value(), "Login not success", null));
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<BaseResponse<Object>> user(@RequestParam("refreshToken") String refreshToken) {
        try {
            String accessToken = jwtToken.getUserNameFromJWT(refreshToken);
            UserDetails userDetails = authConfig.loadUserByUsername(accessToken);
            Optional<UserEntity> userEntity = userRepository.findByEmail(userDetails.getUsername());
            Optional<RoleEntity> roleEntity = roleRepository.findById(userEntity.get().getRoleId());
            String newToken = jwtToken.generateToken(userDetails);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(HttpStatus.OK.value(), "login success", new UserResponse(userEntity.get().getId(), userEntity.get().getUserName(), userEntity.get().getEmail(), userEntity.get().getRoleId(), newToken, refreshToken)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<?> processForgotPassword(@RequestParam("email") String email) throws MessagingException, UnsupportedEncodingException {
        Random random = new Random();
        String code = String.valueOf(random.nextInt(900000));
        return ResponseEntity.ok(userService.sendByCode(email, code));
    }

    @PostMapping("/change_password")
    public ResponseEntity<?> changePassword(@RequestParam("email") String email,
                                            @RequestParam("newPassword") String newPassword,
                                            @RequestParam("passwordAgain") String passwordAgain) {
        Optional<UserEntity> userEntity = userRepository.findByEmailAndRoleId(email, 4);
        if (userEntity.isPresent()) {
            if (newPassword.equals(passwordAgain)) {
                userService.updatePassword(userEntity.get(), newPassword);
                return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "Thay đổi thành công", null));
            }
            return ResponseEntity.ok(new BaseResponse<>(500, "Mật khẩu nhập lại đang không đúng", null));
        }
        return ResponseEntity.ok(new BaseResponse<>(500, "Thay đổi không thành công", null));
    }

    @GetMapping("/check_code")
    public ResponseEntity<Object> checkCode(@RequestParam("code") String code, @RequestParam("email") String email) {
        return ResponseEntity.ok(userService.isCheckCode(code, email));
    }
}
