package com.thankscard.global.api.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseDTO {

    private HttpStatus status;

    private final boolean isSuccess;
    private final String errCode;
    private final String message;

    public boolean getIsSuccess() {
        return isSuccess;
    }
}
