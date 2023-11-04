package com.example.halagodainv.request.brand;

import com.example.halagodainv.exception.ErrorResponse;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class BrandEditRequest {
    private int id;
    private String registerName;
    private String brandName;
    private String email;
    private String website;
    private String phoneNumber;
    private String logo;
    private String description;
    private Integer partnerId;
    private boolean FilmingTVCCommercials;
    private boolean liveStream;
    private boolean review;
    private boolean orther;
    private boolean event;
    private boolean BrandAmbassador;

    public boolean validate(List<ErrorResponse> response) {
        boolean isCheck = true;
        if (StringUtils.isBlank(registerName)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "registerName is not null or empty", null));
            isCheck = false;
        }
        if (StringUtils.isBlank(brandName)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "brandName is not null or empty", null));
            isCheck = false;
        }
        if (StringUtils.isBlank(email)) {
            response.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "email is not null or empty", null));
            isCheck = false;
        }
        return isCheck;
    }
}
