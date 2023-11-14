package com.viepovsky.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/car-profile")
@CrossOrigin("*")
class CarProfileController {
    private final CarProfileService service;

    @Autowired
    CarProfileController(CarProfileService service) {
        this.service = service;
    }

    @GetMapping
    List<CarProfileDTO> getCarProfiles() {
        return service.getCarProfiles();
    }

    @GetMapping("{carId}")
    CarProfileDTO getCarProfile(@PathVariable UUID carId) {
        return service.getCarProfile(carId);
    }

    @PostMapping(
            value = "{carId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    void uploadCarProfileImage(@PathVariable UUID carId, @RequestParam MultipartFile file) {
        service.uploadCarProfileImage(carId, file);
    }

    @GetMapping("{carId}/image/download")
    byte[] getCarProfileImage(@PathVariable UUID carId) {
        return service.downloadCarProfileImage(carId);
    }
}
