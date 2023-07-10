package com.example.halagodainv.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractResponse { private int status;
    private String errorCode;
    private String message;
    private String id;

    public AbstractResponse(int status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }
    public AbstractResponse(int status, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }
}
