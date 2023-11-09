package com.viepovsky.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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

    }
}
