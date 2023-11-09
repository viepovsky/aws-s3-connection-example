package com.viepovsky.profile;

import com.viepovsky.datastore.FakeUserProfileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class UserProfileDataAccessService {

    private final FakeUserProfileDataStore dataStore;

    @Autowired
    UserProfileDataAccessService(FakeUserProfileDataStore dataStore) {
        this.dataStore = dataStore;
    }

    List<UserProfile> getUserProfiles() {
        return dataStore.getUserProfiles();
    }
}
