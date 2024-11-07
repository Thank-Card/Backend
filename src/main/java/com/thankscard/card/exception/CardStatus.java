package com.thankscard.card.exception;

import com.thankscard.global.api.code.BaseResponse;
import com.thankscard.global.api.code.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CardStatus implements BaseResponse<ResponseDTO> {

    // Error Code 작성 (UNKNOWN: 에러 타입, 5: API Status Code 타입, 001: 사용자 에러 번호)
    CARD_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "CARDIMG4001", "Card Image ID is not exist"),

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
}
