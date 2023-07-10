package com.example.halagodainv.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final Integer errorCode;
    private final String causeMsg;
    private final String detailsMessage;

    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode.getDetailsMessage());
        this.errorCode = errorCode.getResponseStatus();
        this.causeMsg = errorCode.getCauseMsg();
        this.detailsMessage = errorCode.getDetailsMessage();
    }

    public ResourceNotFoundException(Integer errorCode, String causeMsg, String detailsMessage) {
        super(detailsMessage);
        this.errorCode = errorCode;
        this.causeMsg = causeMsg;
        this.detailsMessage = detailsMessage;
    }
}
