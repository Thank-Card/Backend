package com.thankscard.hello.controller;

import com.thankscard.global.api.GlobalApiResponse;
import com.thankscard.hello.dto.HelloResponseDTO;
import com.thankscard.hello.service.HelloService;
import com.thankscard.global.s3.service.S3UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final HelloService helloService;
    private final S3UploadService s3UploadService;
    @Value("${cloud.aws.s3.path.card}")
    private String cardPath;

    @GetMapping("/hello")
    public GlobalApiResponse<HelloResponseDTO> hello() {
        return GlobalApiResponse.onSuccess(
                HelloResponseDTO.builder()
                .message("Hello, Spring!")
                .build());
    }

    @GetMapping("/hello/{flag}")
    public GlobalApiResponse<HelloResponseDTO> hello(@PathVariable Integer flag) {

        helloService.CheckFlag(flag);

        return GlobalApiResponse.onSuccess(
                HelloResponseDTO.builder()
                        .message("Hello, Spring! flag: " + flag)
                        .build());
    }

    @PostMapping(value = "/hello/upload",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "이미지 업로드 테스트", description = "이미지 업로드를 테스트하는 API")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "이미지 업로드 성공"),
    })
    public GlobalApiResponse<HelloResponseDTO> uploadFileTest(@RequestParam("file") MultipartFile file) throws IOException {

        String url = s3UploadService.uploadImage(file, cardPath);

        return GlobalApiResponse.onSuccess(
                HelloResponseDTO.builder()
                        .message(url)
                        .build());
    }

}
