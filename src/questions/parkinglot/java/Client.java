package questions.parkinglot.java;

import questions.parkinglot.java.entity.*;
import questions.parkinglot.java.singleton.ParkingLot;
import questions.parkinglot.java.strategy.fee.HourlyFeeStrategy;
import questions.parkinglot.java.strategy.spot.BestFitStrategy;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Client {
    static void main() {
        ParkingLot parkingLot = ParkingLot.getInstance();

        Map<VehicleSize, Integer> parkingSpots = Map.of(
                VehicleSize.SMALL, 1,
                VehicleSize.MEDIUM, 20,
                VehicleSize.LARGE, 1
        );

        // 1. Initialize the parking lot with floors and spots
        ParkingFloor floor1 = new ParkingFloor(1, parkingSpots);

        Map<VehicleSize, Integer> parkingSpotsII = Map.of(
                VehicleSize.SMALL, 3,
                VehicleSize.MEDIUM, 2,
                VehicleSize.LARGE, 7
        );
        ParkingFloor floor2 = new ParkingFloor(2, parkingSpotsII);
//        floor2.addSpot(new ParkingSpot("F2-M1", VehicleSize.MEDIUM));
//        floor2.addSpot(new ParkingSpot("F2-M2", VehicleSize.MEDIUM));

        parkingLot.initialize(List.of(floor1, floor2), new HourlyFeeStrategy(2.5f), new BestFitStrategy());

//        parkingLot.setFeeStrategy(new VehicleBasedFeeStrategy());

        // 2. Simulate vehicle entries
        System.out.println("\n--- Vehicle Entries ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        Vehicle bike = new Bike("B-123");
        Vehicle car = new Car("C-456");
        Vehicle truck = new Truck("T-789");

        ParkingTicket bikeTicketOpt = parkingLot.parkVehicle(bike);

        ParkingTicket carTicketOpt = parkingLot.parkVehicle(car);

        ParkingTicket truckTicketOpt = parkingLot.parkVehicle(truck);

        System.out.println("\n--- Availability after parking ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        // 3. Simulate another car entry (should go to floor 2)
        Vehicle car2 = new Car("C-999");
        ParkingTicket car2TicketOpt = parkingLot.parkVehicle(car2);

        // 4. Simulate a vehicle entry that fails (no available spots)
        Vehicle bike2 = new Bike("B-000");
        ParkingTicket failedBikeTicketOpt = parkingLot.parkVehicle(bike2);

        // 5. Simulate vehicle exits and fee calculation
        System.out.println("\n--- Vehicle Exits ---");

        if (carTicketOpt != null) {
            Double feeOpt = parkingLot.unparkVehicle(carTicketOpt.getTicketId());
            System.out.printf("Car C-456 unparked. Fee: $%.2f\n", feeOpt);
        }

        System.out.println("\n--- Availability after one car leaves ---");
        floor1.displayAvailability();
        floor2.displayAvailability();
    }
}
