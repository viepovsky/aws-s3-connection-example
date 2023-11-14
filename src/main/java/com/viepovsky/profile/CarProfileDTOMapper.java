package com.viepovsky.profile;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
class CarProfileDTOMapper implements Function<CarProfile, CarProfileDTO> {
    @Override
    public CarProfileDTO apply(CarProfile carProfile) {
        return new CarProfileDTO(
                carProfile.getCarProfileId(),
                carProfile.getBrand(),
                carProfile.getModel(),
                carProfile.getCarProfileImageKey()
        );
    }
}
