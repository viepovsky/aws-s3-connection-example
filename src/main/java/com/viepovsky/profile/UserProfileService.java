package com.viepovsky.profile;

import com.amazonaws.services.gluedatabrew.model.Metadata;
import com.viepovsky.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.IMAGE_JPEG;
import static org.apache.http.entity.ContentType.IMAGE_PNG;

@Service
class UserProfileService {
    private final UserProfileDataAccessService dataAccessService;
    private final FileStore fileStore;

    @Autowired
    UserProfileService(UserProfileDataAccessService dataAccessService, FileStore fileStore) {
        this.dataAccessService = dataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles() {
        return dataAccessService.getUserProfiles();
    }

    void uploadUserProfileImage(UUID userId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("File is empty:");
        }
        if (!Arrays.asList(IMAGE_JPEG, IMAGE_PNG).contains(file.getContentType())) {
            throw new IllegalStateException("This is not an image:");
        }
        UserProfile user = dataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User profile " + userId + " not found"));

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        try {
            fileStore.save("profile-image", file.getName(), Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to store:");
        }

        user.setUserProfileImageLink("");

    }
}
