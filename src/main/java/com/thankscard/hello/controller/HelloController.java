package com.thankscard.hello.controller;

import com.thankscard.global.api.ApiResponse;
import com.thankscard.hello.dto.HelloResponseDTO;
import com.thankscard.hello.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final HelloService helloService;

    @GetMapping("/hello")
    public ApiResponse<HelloResponseDTO> hello() {
        return ApiResponse.onSuccess(
                HelloResponseDTO.builder()
                .message("Hello, Spring!")
                .build());
    }

    @GetMapping("/hello/{flag}")
    public ApiResponse<HelloResponseDTO> hello(@PathVariable Integer flag) {

        helloService.CheckFlag(flag);

        return ApiResponse.onSuccess(
                HelloResponseDTO.builder()
                        .message("Hello, Spring! flag: " + flag)
                        .build());
    }
}
