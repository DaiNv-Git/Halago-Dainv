package com.example.halagodainv.service.auth;

import com.example.halagodainv.dto.user.UserDto;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.model.InfluencerEntity;
import com.example.halagodainv.model.UserEntity;
import com.example.halagodainv.model.authen.AuthenPassword;
import com.example.halagodainv.repository.InfluencerDetailRepository;
import com.example.halagodainv.repository.InfluencerEntityRepository;
import com.example.halagodainv.repository.RoleRepository;
import com.example.halagodainv.repository.UserRepository;
import com.example.halagodainv.repository.authen.AuthenPasswordRepository;
import com.example.halagodainv.request.UserAddRequest;
import com.example.halagodainv.request.UserEditRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.UserService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserServiceConfig userServiceConfig;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final InfluencerEntityRepository influencerEntityRepository;
    private final InfluencerDetailRepository influencerDetailRepository;
    private final AuthenPasswordRepository authenPasswordRepository;

    private final JavaMailSender javaMailSender;

    @PersistenceContext
    private final EntityManager entityManager;

    public Object getAll(int pageNo, int pageSize, String userName) {
        try {
            int offset = 0;
            if (pageNo > 0) {
                offset = pageNo - 1;
            }
            Pageable pageable = PageRequest.of(offset, pageSize);
            List<UserDto> userDtos = new ArrayList<>();
            userRepository.getAll(userName, pageable).forEach(userEntity -> userDtos.add(new UserDto(userEntity)));
            if (CollectionUtils.isEmpty(userDtos)) {
                PageResponse<UserDto> pageResponse = new PageResponse<>(new PageImpl<>(userDtos, pageable, 0));
                return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu thành công", pageResponse);
            }
            PageResponse<UserDto> pageResponse = new PageResponse<>(new PageImpl<>(userDtos, pageable, userRepository.totalElementAll(userName)));
            return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu thành công", pageResponse);
        } catch (Exception exception) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lấy dữ liệu không thành công", null);
        }
    }


    public Object getDetail(int userId) {
        try {
            return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu thành công", new UserDto(userRepository.getUser(userId)));
        } catch (Exception exception) {
            throw new RuntimeException("Lấy dữ liệu thất bại");
        }
    }

    public Object addUser(UserAddRequest userAddRequest) {
        try {
            if (!userAddRequest.getPassword().equals(userAddRequest.getPasswordConfirm())) {
                return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Mật khẩu xác nhận đang không giống nhau", null);
            }

            Optional<UserEntity> isCheckEmail = userRepository.findByEmail(userAddRequest.getEmail());
            if (isCheckEmail.isPresent()) {
                return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Email [" + userAddRequest.getEmail() + "] này đã tồn tại", null);
            }

            Optional<UserEntity> isCheckUserName = userRepository.findByUserName(userAddRequest.getUserName());
            if (isCheckUserName.isPresent()) {
                return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tên tài khoản [" + userAddRequest.getUserName() + "] này đã tồn tại", null);
            }
            UserEntity user = new UserEntity();
            user.setUserName(userAddRequest.getUserName());
            user.setEmail(userAddRequest.getEmail());
            user.setPhone(userAddRequest.getPhone());
            user.setRoleId(userAddRequest.getRoleId());
            user.setPassword(passwordEncoder.encode(userAddRequest.getPassword()));
            user.setCreated(new Date());
            user = userRepository.save(user);
            return new BaseResponse<>(HttpStatus.OK.value(), "Thêm tài khoản thành công", userRepository.getUser(user.getId()));
        } catch (Exception exception) {
            return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Thêm tài khoản thất bại ", null);
        }
    }

    public Object updateUser(UserEditRequest userEditRequest) {
        try {
            Optional<UserEntity> user = userRepository.findById(userEditRequest.getId());
            if (user.isPresent()) {
                Optional<UserEntity> isCheckEmail = userRepository.findByEmailAndIdIsNot(userEditRequest.getEmail(), user.get().getId());
                if (isCheckEmail.isPresent()) {
                    return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Email [" + userEditRequest.getEmail() + "] này đã tồn tại", null);
                }
                Optional<UserEntity> isCheckUserName = userRepository.findByUserNameAndIdIsNot(userEditRequest.getUserName(), user.get().getId());
                if (isCheckUserName.isPresent()) {
                    return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tên tài khoản [" + userEditRequest.getUserName() + "] này đã tồn tại", null);
                }
                user.get().setEmail(userEditRequest.getEmail());
                if (!Strings.isNullOrEmpty(userEditRequest.getPassword())) {
                    user.get().setPassword(passwordEncoder.encode(userEditRequest.getPassword()));
                }
                user.get().setUserName(userEditRequest.getUserName());
                user.get().setPhone(userEditRequest.getPhone());
                user.get().setRoleId(userEditRequest.getRoleId());
                userRepository.save(user.get());
            }
            return new BaseResponse<>(HttpStatus.OK.value(), "Sửa dữ liệu thành công", userRepository.getUser(user.get().getId()));
        } catch (Exception exception) {
            return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Sửa dữ liệu không thành công", null);
        }
    }

    public void deleteUser(int userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            Optional<InfluencerEntity> influencerEntity = influencerEntityRepository.findByUserId(userId);
            influencerEntity.ifPresent(entity -> influencerDetailRepository.deleteByInfluId(entity.getId()));
            influencerEntityRepository.deleteByUserId(userId);
        }
        userRepository.deleteById(userId);
    }

    public Object getRole() {
        return new BaseResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu thành công", roleRepository.findAll());
    }


    public void updateResetPasswordToken(String codeResetPassWord, String email) {
        UserDetails customer = userServiceConfig.loadUserByUsername(email);
        Optional<UserEntity> userEntity = userRepository.findByEmail(customer.getUsername());
        if (userEntity.isPresent()) {
            userEntity.get().setPassword(passwordEncoder.encode(codeResetPassWord));
            userRepository.save(userEntity.get());
        } else {
            throw new RuntimeException("Could not find any customer with the email " + email);
        }
    }

    public void updatePassword(UserEntity customer, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(encodedPassword);
        userRepository.save(customer);
    }

    public void sendEmail(String recipientEmail, String code)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(message, true);
        mailMessage.setFrom("halogo939@gmail.com", "halago.contact");
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("This is the password code");
        String content = "<div><h3>New password: </h3>" +
                "<span>" + code + "</span></div>";
        mailMessage.setText(content, true);
        javaMailSender.send(message);
    }

    public boolean isCheckCode(String code, String email) {
        Optional<AuthenPassword> isCheckCode = authenPasswordRepository.findByCodeAndEmail(code, email);
        return isCheckCode.isPresent();
    }

    public Object sendByCode(String recipientEmail, String code) throws MessagingException, UnsupportedEncodingException {
        Optional<UserEntity> authenEmail = userRepository.findByEmailAndRoleId(recipientEmail, 4);
        if (authenEmail.isEmpty()) {
            return new ErrorResponse<>(500, "Email không tồn tại hoặc không phải là tài khoản Influencer", null);
        }
        Optional<AuthenPassword> isCheckEmail = authenPasswordRepository.findByEmail(recipientEmail);
        if (isCheckEmail.isPresent()) {
            isCheckEmail.get().setEmail(recipientEmail);
            isCheckEmail.get().setCode(code);
            authenPasswordRepository.save(isCheckEmail.get());
        } else {
            AuthenPassword authenPassword = new AuthenPassword();
            authenPassword.setEmail(recipientEmail);
            authenPassword.setCode(code);
            authenPasswordRepository.save(authenPassword);
        }
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(message, true);
        mailMessage.setFrom("halogo939@gmail.com", "halago.contact");
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Mã otp đã của bạn");
        String content = "<div><h3>Mã otp: " + code + " </h3> </div>"
                + "<span><h4> Có hiệu lực trong vòng 30 giây </h4></span>";
        mailMessage.setText(content, true);
        javaMailSender.send(message);
        timeOutAuthenCode(recipientEmail);
        return new BaseResponse<>(200, "Mã xác thực đã được gửi tới email của bạn", null);
    }

    private void timeOutAuthenCode(String email) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> {
            authenPasswordRepository.deleteByEmail(email);
        }, 31, TimeUnit.SECONDS);

        executor.shutdown();
    }
}
