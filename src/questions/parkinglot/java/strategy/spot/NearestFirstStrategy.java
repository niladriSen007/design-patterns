package questions.parkinglot.java.strategy.spot;

import questions.parkinglot.java.entity.ParkingFloor;
import questions.parkinglot.java.entity.ParkingSpot;
import questions.parkinglot.java.entity.VehicleSize;

import java.util.List;

public class NearestFirstStrategy implements SpotAllocationStrategy {
    @Override
    public ParkingSpot findSpot(List<ParkingFloor> floors, VehicleSize size) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findAvailableParkingSpot(size);
            if (spot != null) {
                return spot;
            }
        }
        return null;
    }
}
