package questions.parkinglot.java.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber, Map<VehicleSize, Integer> parkingSpots) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<>();
        initializeSpots(parkingSpots);
        System.out.println("Initialized Floor " + floorNumber + " with spots: " + this.parkingSpots.size());
    }

    private void initializeSpots(Map<VehicleSize, Integer> parkingSpots) {
        for (VehicleSize size : VehicleSize.values()) {
            Integer count = parkingSpots.getOrDefault(size, 0);
            String sizePrefix = getSizePrefix(size);
            for (int i = 1; i <= count; i++) {
                String spotId = String.format("F%d-%s%03d", floorNumber, sizePrefix, i);
                this.parkingSpots.add(new ParkingSpot(spotId, size));
            }
        }
    }

    private String getSizePrefix(VehicleSize size) {
        return switch (size) {
            case SMALL -> "S";
            case MEDIUM -> "M";
            case LARGE -> "L";
            default -> "X";
        };
    }

    public ParkingSpot findAvailableParkingSpot(VehicleSize size) {
        for (ParkingSpot parkingSpot : this.parkingSpots) {
            if (parkingSpot.isSpotAvailable() && parkingSpot.canVehicleFit(size)) {
                return parkingSpot;
            }
        }
        return null;
    }

    public int getAvailableSpotCountBySize(VehicleSize size) {
        int count = 0;
        for (ParkingSpot parkingSpot : this.parkingSpots) {
            if (parkingSpot.isSpotAvailable() && parkingSpot.canVehicleFit(size)) {
                count++;
            }
        }
        return count;
    }

    public int getFloorNumber() {
        return this.floorNumber;
    }

    public List<ParkingSpot> getParkingSpots() {
        return Collections.unmodifiableList(parkingSpots);
    }

    public void displayAvailability() {
        System.out.printf("--- Floor %d Availability ---\n", floorNumber);
        Map<VehicleSize, Long> availableCounts = parkingSpots.stream()
                .filter(ParkingSpot::isSpotAvailable)
                .collect(Collectors.groupingBy(ParkingSpot::getSpotSize, Collectors.counting()));

        for (VehicleSize size : VehicleSize.values()) {
            System.out.printf("  %s spots: %d\n", size, availableCounts.getOrDefault(size, 0L));
        }
    }

}

