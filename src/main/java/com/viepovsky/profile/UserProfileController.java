package com.viepovsky.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user-profile")
@CrossOrigin("*")
class UserProfileController {
    private final UserProfileService service;

    @Autowired
    UserProfileController(UserProfileService service) {
        this.service = service;
    }

    @GetMapping
    List<UserProfile> getUserProfiles() {
        return service.getUserProfiles();
    }

    @PostMapping(
            path = "{userId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    void uploadUserProfileImage(@PathVariable UUID userId, @RequestParam MultipartFile file) {
        service.uploadUserProfileImage(userId, file);
    }
}
