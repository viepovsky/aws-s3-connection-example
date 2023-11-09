package com.viepovsky.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
class AmazonConfig {

    @Bean
    AmazonS3 s3() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("credentials.txt")));
            String access = content.substring(0, content.indexOf(','));
            String secret = content.substring(content.indexOf(',') + 1);
            AWSCredentials awsCredentials = new BasicAWSCredentials(
                    access,
                    secret
            );
            return AmazonS3ClientBuilder
                    .standard()
                    .withRegion(Regions.EU_CENTRAL_1)
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Credentials.txt not found", e);
        }
    }
}
