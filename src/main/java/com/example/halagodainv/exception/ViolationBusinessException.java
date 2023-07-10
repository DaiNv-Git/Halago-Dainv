package com.example.halagodainv.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class ViolationBusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final Integer errorCode;
    private final String causeMsg;
    private final String detailsMessage;

    public ViolationBusinessException(ErrorCode error) {
        super(error.getDetailsMessage());
        this.errorCode = error.getResponseStatus();
        this.causeMsg = error.getCauseMsg();
        this.detailsMessage = error.getDetailsMessage();
    }

    public ViolationBusinessException(Integer errorCode, String causeMsg, String detailsMessage) {
        super(detailsMessage);
        this.errorCode = errorCode;
        this.causeMsg = causeMsg;
        this.detailsMessage = detailsMessage;
    }
}