package com.thankscard.global.api.exception;

import com.thankscard.global.api.code.BaseResponse;
import com.thankscard.global.api.code.ResponseDTO;

public class HelloException extends BaseException {

    public HelloException(BaseResponse<ResponseDTO> code) {
        super(code);
    }
}
