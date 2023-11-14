package com.viepovsky.profile;

import com.viepovsky.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@Service
class CarProfileService {
    private final FakeCarProfileRepository repository;

    private final S3Service s3Service;

    @Autowired
    CarProfileService(FakeCarProfileRepository repository, S3Service s3Service) {
        this.repository = repository;
        this.s3Service = s3Service;
    }

    List<CarProfile> getCarProfiles() {
        return repository.getAllCarProfiles();
    }

    CarProfile getCarProfile(UUID carId) {
        return repository.getCarProfileById(carId).orElseThrow(() -> new IllegalStateException("No Car of given id: " + carId));
    }

    byte[] downloadCarProfileImage(UUID carId) {
        CarProfile carProfile = repository.getCarProfileById(carId).orElseThrow(() -> new IllegalStateException("No Car of given id: " + carId));
        String imageKey = carProfile.getCarProfileImageKey();
        return s3Service.downloadObject(imageKey);
    }

    void uploadCarProfileImage(UUID carId, MultipartFile file) {
        isFileEmpty(file);
        isImage(file);

        CarProfile car = getCarProfileOrThrow(carId);
        String key = "images/%s/%s".formatted(carId.toString(), UUID.randomUUID().toString());
        try {
            s3Service.uploadObject(key, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed", e);
        }

        car.setCarProfileImageKey(key);
        repository.updateCarProfile(car);
    }

    private CarProfile getCarProfileOrThrow(UUID carId) {
        return getCarProfile(carId);
    }

    private static void isImage(MultipartFile file) {
        if (!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE).contains(file.getContentType())) {
            throw new IllegalStateException("This is not an image:" + file.getContentType());
        }
    }

    private static void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("File is empty:");
        }
    }
}
