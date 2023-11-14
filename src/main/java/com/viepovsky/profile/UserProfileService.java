package com.viepovsky.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.springframework.http.MediaType.IMAGE_JPEG;
import static org.springframework.http.MediaType.IMAGE_PNG;

@Service
class UserProfileService {
    private final UserProfileDataAccessService dataAccessService;

    @Autowired
    UserProfileService(UserProfileDataAccessService dataAccessService) {
        this.dataAccessService = dataAccessService;
    }

    List<UserProfile> getUserProfiles() {
        return dataAccessService.getUserProfiles();
    }

    void uploadUserProfileImage(UUID userId, MultipartFile file) {
        isFileEmpty(file);

        isImage(file);

        UserProfile user = getUserProfileOrThrow(userId);
        Map<String, String> metadata;
        try {
            metadata = extractMetadataFrom(file);
        } catch (IOException e) {
            throw new IllegalStateException("Failed", e);
        }
        //String path = String.format("%s/%s", BucketName.S3_BUCKET.getBucketName(), userId);
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        //            fileStore.save(path, fileName, file.getInputStream(), Optional.of(metadata));

        //user.setUserProfileImageLink(path);

    }

    private static Map<String, String> extractMetadataFrom(MultipartFile file) throws IOException {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile getUserProfileOrThrow(UUID userId) {
        return dataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User profile " + userId + " not found"));
    }

    private static void isImage(MultipartFile file) {
        if (!Arrays.asList(IMAGE_JPEG, IMAGE_PNG).contains(file.getContentType())) {
            throw new IllegalStateException("This is not an image:" + file.getContentType());
        }
    }

    private static void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("File is empty:");
        }
    }
}
