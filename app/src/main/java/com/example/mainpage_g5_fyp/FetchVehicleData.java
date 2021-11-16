package com.example.mainpage_g5_fyp;

public class FetchVehicleData {
    String vehicleNumber;
    String model;
    String description;

    public FetchVehicleData() {
    }

    public FetchVehicleData(String vehicleNumber, String model, String description) {
        this.vehicleNumber = vehicleNumber;
        this.model = model;
        this.description = description;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
