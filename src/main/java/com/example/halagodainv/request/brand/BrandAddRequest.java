package com.example.halagodainv.request.brand;

import com.example.halagodainv.exception.ErrorResponse;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class BrandAddRequest {
    private String registerName;
    private String brandName;
    private String website;
    private int phoneNumber;
    private String logo;
    private String description;

    public boolean validate(List<ErrorResponse> response) {
        boolean isCheck = true;
        if (Strings.isBlank(registerName)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "registerName is not null or empty", null));
            isCheck = false;
        }
        if (Strings.isBlank(brandName)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "brandName is not null or empty", null));
            isCheck = false;
        }
        return isCheck;
    }
}