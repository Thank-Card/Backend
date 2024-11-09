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
    CARD_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "CARDIMG4001", "카드 이미지의 아이디가 존재하지 않습니다."),
    CARD_NOT_FOUND(HttpStatus.NOT_FOUND, "CARD4001", "존재하지 않은 카드입니다."),
    CARD_SEND_USER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "CARD4002", "로그인 한 유저만 카드 작성이 가능합니다."),
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
