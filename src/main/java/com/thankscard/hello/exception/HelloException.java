package com.thankscard.hello.exception;

import com.thankscard.global.api.code.BaseResponse;
import com.thankscard.global.api.code.ResponseDTO;
import com.thankscard.global.api.exception.BaseException;

public class HelloException extends BaseException {

    public HelloException(BaseResponse<ResponseDTO> code) {
        super(code);
    }
}
