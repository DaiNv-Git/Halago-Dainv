package com.example.halagodainv.exception;

import com.example.itspower.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build(ErrorResponse errorResponse, HttpStatus httpStatus) {
        return ResponseHandler.generateResponse(httpStatus, errorResponse.getMessage(), errorResponse);
    }
}