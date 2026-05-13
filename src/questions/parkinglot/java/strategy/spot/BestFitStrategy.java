package questions.parkinglot.java.strategy.spot;

import questions.parkinglot.java.entity.ParkingFloor;
import questions.parkinglot.java.entity.ParkingSpot;
import questions.parkinglot.java.entity.VehicleSize;

import java.util.List;

public class BestFitStrategy implements SpotAllocationStrategy {
    @Override
    public ParkingSpot findSpot(List<ParkingFloor> floors, VehicleSize vehicleSize) {

        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getParkingSpots()) {
                if (spot.isSpotAvailable() && spot.getSpotSize().ordinal() == vehicleSize.ordinal()) {
                    return spot;
                }
            }
        }

        for (VehicleSize spotSize : VehicleSize.values()) {
            if (spotSize.ordinal() < vehicleSize.ordinal()) {
                continue;
            }
            for (ParkingFloor floor : floors) {
                for (ParkingSpot spot : floor.getParkingSpots()) {
                    if (spot.isSpotAvailable() && spot.getSpotSize().ordinal() >= vehicleSize.ordinal()) {
                        return spot;
                    }
                }
            }
        }
        return null;
    }
}
