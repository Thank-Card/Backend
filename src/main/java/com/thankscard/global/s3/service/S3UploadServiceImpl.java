package com.thankscard.global.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.thankscard.global.s3.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class S3UploadServiceImpl implements S3UploadService {

    private final AmazonS3 amazonS3;
    private final S3Util s3Util;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String uploadImage(MultipartFile multipartFile, String dirPath) throws IOException {
        String fileName = s3Util.generateFileName(multipartFile);
        String bucketDir = bucket + "/" + dirPath;
        ObjectMetadata objectMetadata = s3Util.getObjectMetadata(multipartFile);

        amazonS3.putObject(bucketDir, fileName, multipartFile.getInputStream(), objectMetadata);

        return amazonS3.getUrl(bucketDir, fileName).toString();
    }

    @Override
    public void deleteImage(String originalFilename) {
        amazonS3.deleteObject(bucket, originalFilename);
    }



}
