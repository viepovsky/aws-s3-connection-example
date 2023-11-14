package com.viepovsky.s3;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws.s3.bucket-name")
public class S3Buckets {

    private String localTesting;

    String getLocalTesting() {
        return localTesting;
    }

    void setLocalTesting(String localTesting) {
        this.localTesting = localTesting;
    }
}
