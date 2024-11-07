package com.thankscard.hello.exception;

import com.thankscard.global.api.code.BaseResponse;
import com.thankscard.global.api.code.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HelloStatus implements BaseResponse<ResponseDTO> {

    HELLO_TEST_ERROR(HttpStatus.BAD_REQUEST, "HELLO4001", "invalid flag"),
    HELLO_FLAG_NULL_ERROR(HttpStatus.BAD_REQUEST, "HELLO4002", "invalid flag: null")
    ,;

    private final HttpStatus httpStatus;
    private final String errCode;
    private final String message;

    @Override
    public ResponseDTO getResponse() {

        return ResponseDTO.builder()
                .status(httpStatus)
                .isSuccess(false)
                .errCode(errCode)
                .message(message)
                .build();
    }
}
