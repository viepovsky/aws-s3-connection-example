package com.viepovsky.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
