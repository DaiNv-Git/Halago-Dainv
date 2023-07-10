package com.example.halagodainv.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Lazy;

@Getter
@AllArgsConstructor
@Lazy
public class BaseResponse<T> {
    private Integer code;

    private String message;

    private T data;


}
