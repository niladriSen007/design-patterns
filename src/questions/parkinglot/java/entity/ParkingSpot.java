package questions.parkinglot.java.entity;

import questions.parkinglot.java.exceptions.ParkingException;

public class ParkingSpot {
    private final String spotId;
    private final VehicleSize spotSize;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
        this.parkedVehicle = null;
    }

    public String getSpotId() {
        return spotId;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public boolean isSpotAvailable() {
        return parkedVehicle == null;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public boolean canVehicleFit(VehicleSize vehicleSize) {
        return spotSize.ordinal() >= vehicleSize.ordinal();
    }

    public synchronized void parkVehicle(Vehicle vehicle) {
        if (!isSpotAvailable()) {
            throw new ParkingException("Spot " + spotId + " is already occupied");
        }
        if (!canVehicleFit(vehicle.getSize())) {
            throw new ParkingException("Spot " + spotId + " is not available");
        }
        this.parkedVehicle = vehicle;
    }

    public synchronized Vehicle unparkVehicle() {
        if (isSpotAvailable()) {
            throw new ParkingException("Spot " + spotId + " is already available");
        }
        Vehicle parkedVehicle = this.parkedVehicle;
        this.parkedVehicle = null;
        return parkedVehicle;
    }
}
