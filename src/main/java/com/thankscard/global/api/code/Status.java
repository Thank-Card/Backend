package com.thankscard.global.api.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum Status implements BaseResponse<ResponseDTO> {

    // Code 작성 (COMMON: 성공 타입, 2: API Status Code 타입, 001: 사용자 응답 번호)
    // _OK("COMMON2000", "Success."),
    // HELLO_TEST_SUCCESS(HttpStatus.OK, "HELLO2000", "Success."),
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
