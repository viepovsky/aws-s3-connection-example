package com.viepovsky.profile;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
class FakeCarProfileRepository {
    private final List<CarProfile> CAR_PROFILES = new ArrayList<>(List.of(
            new CarProfile(UUID.randomUUID(), "Skoda", "Fabia", null),
            new CarProfile(UUID.randomUUID(), "Opel", "Corsa", null)
    ));

    List<CarProfile> getAllCarProfiles() {
        return new ArrayList<>(CAR_PROFILES);
    }

    Optional<CarProfile> getCarProfileById(UUID id) {
        return CAR_PROFILES.stream().filter(carProfile -> carProfile.getCarProfileId().equals(id)).findFirst();
    }

    boolean deleteCarProfileById(UUID id) {
        return isCarProfileInDatabase(id) && CAR_PROFILES.remove(getCarProfileById(id).get());
    }

    private boolean isCarProfileInDatabase(UUID id) {
        return CAR_PROFILES.stream().anyMatch(carProfile -> carProfile.getCarProfileId().equals(id));
    }

    boolean addCarProfile(CarProfile carProfile) {
        return CAR_PROFILES.add(carProfile);
    }

    void updateCarProfile(CarProfile updatedCarProfile) {
        for (CarProfile carProfile : CAR_PROFILES) {
            if (carProfile.getCarProfileId().equals(updatedCarProfile.getCarProfileId())) {
                carProfile.update(updatedCarProfile);
                break;
            }
        }
    }
}
