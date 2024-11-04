package com.thankscard.global.api.code;

import org.springframework.http.HttpStatus;

public interface BaseResponse<T> {

    T getResponse();
    HttpStatus getHttpStatus();
}
