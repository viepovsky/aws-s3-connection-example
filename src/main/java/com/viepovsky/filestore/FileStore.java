package com.viepovsky.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
class FileStore {
    private final AmazonS3 s3;

    @Autowired
    FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    void save(String path, String fileName, Optional<Map<String, String>> optionalMetadata, InputStream inputStream) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setUserMetadata(optionalMetadata.orElse(new HashMap<>()));
        try {
            s3.putObject(path, fileName, inputStream, metadata);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to store file to s3.", e);
        }
    }
}