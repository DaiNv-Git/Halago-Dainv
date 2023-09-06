package com.example.halagodainv.request.brand;

import com.example.halagodainv.exception.ErrorResponse;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class BrandAddRequest {
    private String registerName;
    private String brandName;
    private String email;
    private String password;
    private String website;
    private String phoneNumber;
    private String logo;
    private String description;
    private Integer partnerId;

    public boolean validate(List<ErrorResponse> response) {
        boolean isCheck = true;
        if (Strings.isBlank(registerName)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Cần phải nhập dữ liệu", null));
            isCheck = false;
        }
        if (Strings.isBlank(brandName)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Cần phải nhập dữ liệu", null));
            isCheck = false;
        }
        if (Strings.isBlank(email)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Cần phải nhập dữ liệu", null));
            isCheck = false;
        }
        return isCheck;
    }
}
