package com.viepovsky.profile;

import java.util.Objects;
import java.util.UUID;

class CarProfile {
    private final UUID carProfileId;
    private String brand;
    private String model;
    private String carProfileImageKey;

    CarProfile(UUID carProfileId, String brand, String model, String carProfileImageKey) {
        this.carProfileId = carProfileId;
        this.brand = brand;
        this.model = model;
        this.carProfileImageKey = carProfileImageKey;
    }

    void update(CarProfile carProfile) {
        if (carProfile != null) {
            this.brand = carProfile.getBrand();
            this.model = carProfile.getModel();
            this.carProfileImageKey = carProfile.getCarProfileImageKey();
        }
    }

    UUID getCarProfileId() {
        return carProfileId;
    }

    String getBrand() {
        return brand;
    }

    String getModel() {
        return model;
    }

    String getCarProfileImageKey() {
        return carProfileImageKey;
    }

    void setCarProfileImageKey(String carProfileImageKey) {
        this.carProfileImageKey = carProfileImageKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarProfile that = (CarProfile) o;

        if (!Objects.equals(carProfileId, that.carProfileId)) return false;
        if (!Objects.equals(brand, that.brand)) return false;
        if (!Objects.equals(model, that.model)) return false;
        return Objects.equals(carProfileImageKey, that.carProfileImageKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carProfileId, brand, model, carProfileImageKey);
    }
}
