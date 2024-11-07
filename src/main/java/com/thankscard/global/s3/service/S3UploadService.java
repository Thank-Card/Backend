package com.thankscard.global.s3.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3UploadService {

    String uploadImage(MultipartFile multipartFile, String dirPath) throws IOException;
    void deleteImage(String originalFilename);
}
