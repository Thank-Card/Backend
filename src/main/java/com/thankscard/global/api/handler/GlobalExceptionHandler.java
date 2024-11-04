package com.thankscard.global.api.handler;

import com.thankscard.global.api.ApiResponse;
import com.thankscard.global.api.code.ResponseDTO;
import com.thankscard.global.api.exception.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity onThrowException(BaseException baseException, HttpServletRequest request) {

        ResponseDTO response = baseException.getResponse();
        return handleExceptionInternal(baseException, response,null,request);
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception e, ResponseDTO response,
                                                           HttpHeaders headers, HttpServletRequest request) {

        ApiResponse<Object> body = ApiResponse.onFailure(response.getErrCode(),response.getMessage(),null);
        WebRequest webRequest = new ServletWebRequest(request);

        return super.handleExceptionInternal(
                e,
                body,
                headers,
                response.getStatus(),
                webRequest
        );
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception e, ResponseDTO responseDTO, HttpHeaders headers, WebRequest request) {

        ApiResponse<Object> body = ApiResponse.onFailure(responseDTO.getErrCode(), responseDTO.getMessage(), null);

        return super.handleExceptionInternal(
                e,
                body,
                headers,
                responseDTO.getStatus(),
                request
        );

    }
}
