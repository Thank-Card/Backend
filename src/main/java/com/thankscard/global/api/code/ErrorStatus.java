package com.thankscard.global.api.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseResponse<ResponseDTO> {

    // Error Code 작성 (UNKNOWN: 에러 타입, 5: API Status Code 타입, 001: 사용자 에러 번호)
    // UNKNOWN_ERROR("UNKNOWN5001", "Unknown error"),
    TOKEN_USER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "TOKEN4001", "토큰이 유효하지 않거나 만료되었습니다. 새 토큰을 발급받아 다시 시도해주세요."),
    TOKEN_USER_FORBIDDEN(HttpStatus.FORBIDDEN, "TOKEN4002", "해당 리소스에 인가되지 않은 사용자의 접근입니다."),
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
