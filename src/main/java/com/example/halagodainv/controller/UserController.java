package com.example.halagodainv.controller;

import com.example.halagodainv.config.EmailConfig;
import com.example.halagodainv.config.filter.JwtToken;
import com.example.halagodainv.config.userconfig.UserAuthenConfig;
import com.example.halagodainv.dto.user.UserDto;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.UserEntity;
import com.example.halagodainv.repository.UserRepository;
import com.example.halagodainv.request.UserAddRequest;
import com.example.halagodainv.request.UserEditRequest;
import com.example.halagodainv.request.UserLogin;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.UserResponse;
import com.example.halagodainv.service.UserService;
import com.example.halagodainv.service.auth.UserServiceConfig;
import com.example.halagodainv.until.DateUtilFormat;
import com.example.halagodainv.until.FormatTimeSearch;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.bcel.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private UserAuthenConfig authenConfig;
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
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserAddRequest userAddRequest) {
        return ResponseEntity.ok(userService.addUser(userAddRequest));
    }

    @PostMapping("/insert-influencer")
    public ResponseEntity<Object> addInfluencer(@Valid @RequestBody UserAddRequest userAddRequest) {
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

    @GetMapping("/role")
    public ResponseEntity<Object> getRole() {
        return ResponseEntity.ok(userService.getRole());
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
            return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "Đăng nhập thành công", new UserResponse(user.getUserName(), user.getEmail(), user.getRole(), token, refreshToken)));
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
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(HttpStatus.OK.value(), "login success", new UserResponse(user.getUserName(), user.getEmail(), user.getRole(), newToken, refreshToken)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(@RequestParam("email") String email) throws GeneralException {
        String codeResetPassWord = RandomString.make(8);
        try {
            userService.updateResetPasswordToken(codeResetPassWord, email);
            userService.sendEmail(email, codeResetPassWord);
            return "We have sent a reset password link to your email. Please check";
        } catch (MessagingException ex) {
            throw new GeneralException("error: " + email);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/change_password")
    public ResponseEntity<BaseResponse<Object>> changePassword(@RequestParam("newPassword") String newPassword, @RequestParam("passwordAgain") String passwordAgain) throws GeneralException {
        try {
            UserDetails userDetails = authConfig.loadUserByUsername(authenConfig.getUser());
            Optional<UserEntity> userEntity = userRepository.findByEmail(userDetails.getUsername());
            if (userEntity.isPresent()) {
                if (newPassword.equalsIgnoreCase(passwordAgain)) {
                    userService.updatePassword(userEntity.get(), newPassword);
                    return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(HttpStatus.OK.value(), "Change password success", null));
                }
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse<>(HttpStatus.OK.value(), "Change password note success", null));
        } catch (Exception ex) {
            throw new GeneralException("error: " + ex.getMessage());
        }
    }

//    @PostMapping("/upload-file")
//    public Object uploadFile(@RequestParam MultipartFile file) throws IOException, ParseException {
//        Workbook workbook = new XSSFWorkbook(file.getInputStream());
//        Sheet sheet = workbook.getSheetAt(0);
//        List<UserEntity> userEntityList = new ArrayList<>();
//        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
//            UserEntity userEntity = new UserEntity();
//            Row row = sheet.getRow(i);
//            if (row != null) {
//                userEntity.setUserName(row.getCell(0).getStringCellValue());
//                userEntity.setPassword(passwordEncoder.encode(row.getCell(1).getStringCellValue()));
//                double a= Double.valueOf(row.getCell(2).getNumericCellValue());
//                userEntity.setRole((int) a);
//                userEntity.setCreated(DateUtilFormat.converStringToDate(row.getCell(3).getStringCellValue(),"yyyy-MM-dd"));
//                userEntity.setEmail(row.getCell(4).getStringCellValue());
//                userEntityList.add(userEntity);
//            }
//        }
//        userRepository.saveAll(userEntityList);
//        return null;
//    }
}
