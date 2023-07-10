package com.example.halagodainv.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SuccessResponse<T> extends AbstractResponse {
    private T data;


    public SuccessResponse(int status, String message, T data) {
        super(status, message);
        this.data = data;
    }

    public SuccessResponse(int status, String message, HttpStatus data) {
        super(status, message);
    }

    public SuccessResponse(int status, String message, String errorCode,HttpStatus data) {
        super(status,errorCode, message);
    }

    public SuccessResponse(T data) {
        super(HttpStatus.OK.value(), null);
        this.data = data;
    }
    public SuccessResponse(int status,String message) {
        super(HttpStatus.OK.value(), null);

    }
    public SuccessResponse(int status,String message,Long id,String errorCode,HttpStatus serverStatus) {
        super(HttpStatus.OK.value(),errorCode, null);

    }

    public SuccessResponse(String message, T data) {
        super(HttpStatus.OK.value(), message);
        this.data = data;
    }

}
