package com.thankscard.global.api.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseResponse<ResponseDTO> {

    // Error Code 작성 (UNKNOWN: 에러 타입, 5: API Status Code 타입, 001: 사용자 에러 번호)
    // UNKNOWN_ERROR("UNKNOWN5001", "Unknown error"),
    HELLO_TEST_ERROR(HttpStatus.BAD_REQUEST, "HELLO4001", "invalid flag"),
    HELLO_FLAG_NULL_ERROR(HttpStatus.BAD_REQUEST, "HELLO4002", "invalid flag: null"),
    ;

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

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
