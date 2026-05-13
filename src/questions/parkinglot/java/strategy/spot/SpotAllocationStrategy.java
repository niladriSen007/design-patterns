package questions.parkinglot.java.strategy.spot;

import questions.parkinglot.java.entity.ParkingFloor;
import questions.parkinglot.java.entity.ParkingSpot;
import questions.parkinglot.java.entity.VehicleSize;

import java.util.List;

public interface SpotAllocationStrategy {
    ParkingSpot findSpot(List<ParkingFloor> floors, VehicleSize size);
}
