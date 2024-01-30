package com.example.halagodainv.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse<T> {
    private Integer code;
    private String message;
    private T data;

}
