package com.thankscard.global.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"success", "errCode", "message", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final boolean isSuccess;
    private final String errCode;
    private final String message;
    private final T data;

    public static <T> ApiResponse<T> onSuccess(T data) {
        return new ApiResponse<>(true, null, "OK", data);
    }

//    public static <T> ApiResponse<T> onSuccess(String code, String message, T data) {
//        return new ApiResponse<>(true, code, message, data);
//    }

    public static <T> ApiResponse<T> onFailure(String errCode, String message, T data) {
        return new ApiResponse<>(false, errCode, message, data);
    }

}
