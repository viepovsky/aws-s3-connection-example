package com.viepovsky.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-profile")
class UserProfileController {
    private final UserProfileService service;

    @Autowired
    UserProfileController(UserProfileService service) {
        this.service = service;
    }

    @GetMapping
    List<UserProfile> getUserProfiles(){
        return service.getUserProfiles();
    }
}
