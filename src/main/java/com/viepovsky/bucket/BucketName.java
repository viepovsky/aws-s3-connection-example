package com.viepovsky.bucket;

public enum BucketName {
    S3_BUCKET("viepovsky-test-bucket");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
