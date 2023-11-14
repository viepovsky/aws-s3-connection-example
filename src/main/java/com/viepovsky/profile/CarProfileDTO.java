package com.viepovsky.profile;

import java.util.UUID;

public record CarProfileDTO(
        UUID carProfileId,
        String brand,
        String model,
        String carProfileImageKey
) {
}
