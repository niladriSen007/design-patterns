package questions.parkinglot.java.singleton;

import questions.parkinglot.java.entity.*;
import questions.parkinglot.java.exceptions.ParkingException;
import questions.parkinglot.java.strategy.fee.FeeStrategy;
import questions.parkinglot.java.strategy.spot.SpotAllocationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static volatile ParkingLot instance;
    private static final Object lock = new Object();

    private List<ParkingFloor>  parkingFloors;
    private FeeStrategy feeStrategy;
    private SpotAllocationStrategy  spotAllocationStrategy;
    private final Map<String, ParkingTicket> activeTickets;

    private ParkingLot() {
        this.activeTickets = new ConcurrentHashMap<>();
        this.parkingFloors = new ArrayList<>();
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ParkingLot();
                }
            }
        }
        return instance;
    }

    public void initialize(List<ParkingFloor> floors,
                           FeeStrategy feeStrategy,
                           SpotAllocationStrategy spotAllocationStrategy) {
        this.parkingFloors = floors;
        this.feeStrategy = feeStrategy;
        this.spotAllocationStrategy = spotAllocationStrategy;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        // Find a spot using the allocation strategy
        ParkingSpot spot = spotAllocationStrategy.findSpot(parkingFloors, vehicle.getSize());

        if (spot == null) {
            throw new ParkingException("No available spot for vehicle size: " +
                    vehicle.getSize());
        }

        // Park the vehicle
        spot.parkVehicle(vehicle);

        // Create and store ticket
        String ticketId = UUID.randomUUID().toString().substring(0, 8);
        ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, spot);
        activeTickets.put(ticketId, ticket);

        System.out.println("Parked " + vehicle + " at spot " + spot.getSpotId() +
                ". Ticket: " + ticketId);

        return ticket;
    }

    public double unparkVehicle(String ticketId) {
        ParkingTicket ticket = activeTickets.get(ticketId);

        if (ticket == null) {
            throw new ParkingException("Invalid ticket: " + ticketId);
        }

        // Set exit time and calculate fee
        ticket.setExitTime(java.time.LocalDateTime.now());
        double fee = feeStrategy.calculateFee(ticket);

        // Unpark the vehicle
        ticket.getSpot().unparkVehicle();

        // Remove ticket from active tickets
        activeTickets.remove(ticketId);

        System.out.printf("Unparked %s from spot %s. Fee: $%.2f%n",
                ticket.getVehicle(), ticket.getSpot().getSpotId(), fee);

        return fee;
    }

    public void displayAvailability() {
        System.out.println("\n===== PARKING AVAILABILITY =====");
        for (ParkingFloor floor : parkingFloors) {
            StringBuilder sb = new StringBuilder();
            sb.append("Floor ").append(floor.getFloorNumber()).append(": ");

            for (VehicleSize size : VehicleSize.values()) {
                int count = 0;
                for (ParkingSpot spot : floor.getParkingSpots()) {
                    if (spot.isSpotAvailable() && spot.getSpotSize() == size) {
                        count++;
                    }
                }
                sb.append(size).append("=").append(count);
                if (size.ordinal() < VehicleSize.values().length - 1) {
                    sb.append(", ");
                }
            }
            System.out.println(sb);
        }
        System.out.println("================================\n");
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setAllocationStrategy(SpotAllocationStrategy allocationStrategy) {
        this.spotAllocationStrategy = allocationStrategy;
    }

    public static void resetInstance() {
        synchronized (lock) {
            instance = null;
        }
    }


}
