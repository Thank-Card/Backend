package com.thankscard.card.exception;

import com.thankscard.global.api.code.BaseResponse;
import com.thankscard.global.api.code.ResponseDTO;
import com.thankscard.global.api.exception.BaseException;

public class CardException extends BaseException {

    public CardException(BaseResponse<ResponseDTO> code) {
        super(code);
    }
}
