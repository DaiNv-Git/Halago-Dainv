package com.example.halagodainv.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Business error
    EMAIL_EXISTED(1001, "Email existed!", "Email đã tồn tại!"),
    FILE_NOT_FOUND(1002, "File not found!", "File không tồn tại!"),
    ORGANIZATION_NOT_FOUND(1003, "Organization not found!", "Đơn vị không tồn tại!"),
    ACCOUNT_NOT_FOUND(1004, "Account not found!", "Tài khoản không tồn tại!"),
    EMPLOYEE_NOT_FOUND(1005, "Employee not found!", "Nhân viên không tồn tại!"),
    // Server error
    VIOLATE_VALIDATION_ARGUMENT(2000, "Violate validation constraint", "Dữ liệu không hợp lệ!"),
    METHOD_ARGUMENT_TYPE_MISMATCH(2001, "Method argument type mismatch", "Dữ liệu không hợp lệ!"),
    METHOD_ARGUMENT_NOT_VALID(2002, "Method argument not valid", "Dữ liệu không hợp lệ!"),
    MISSING_SERVLET_REQUEST_PARAMETER(2003, "Missing servlet request parameter", "Thiếu tham số!"),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED(2004, "Http media type not supported", "MIME Type không được hỗ trợ!"),
    NO_HANDLER_FOUND(2005, "No handler found for this URL", "Endpoint không tồn tại!"),
    UNKNOWN_SERVER_ERROR(2006, "Unknown server error occurred", "Lỗi máy chủ!");
    private final Integer responseStatus;
    private final String causeMsg;
    private final String detailsMessage;
    ErrorCode(int responseStatus, String causeMsg, String detailsMessage) {
        this.responseStatus = responseStatus;
        this.causeMsg = causeMsg;
        this.detailsMessage = detailsMessage;
    }
}
