package com.viepovsky.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class S3Service {

    @Value("${aws.s3.bucket-name.localTesting}")
    private String bucketName;

    private final S3Client s3Client;

    S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadObject(String key, byte[] file) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(objectRequest, RequestBody.fromBytes(file));
    }

    public byte[] downloadObject(String key) {
        GetObjectRequest objectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(objectRequest);

        try {
            return response.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Failed to download object", e);
        }
    }

}
