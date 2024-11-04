package com.thankscard.global.api.exception;

import com.thankscard.global.api.code.BaseResponse;
import com.thankscard.global.api.code.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.ErrorResponse;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private final BaseResponse<ResponseDTO> code;

    public ResponseDTO getResponse() {
        return (ResponseDTO) this.code.getResponse();
    }

    public ErrorResponse getErrorResponse() {
        return (ErrorResponse) this.code.getResponse();
    }


}
