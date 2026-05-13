package questions.parkinglot.java.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final LocalDateTime entryTime;
    private final ParkingSpot spot;
    private LocalDateTime exitTime;

    public ParkingTicket(String ticketId, Vehicle vehicle, ParkingSpot spot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
    }

    public String getTicketId() {
        return ticketId;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public long getTotalParkingDuration() {
        LocalDateTime exit = exitTime != null ? exitTime : LocalDateTime.now();
        long hours = ChronoUnit.HOURS.between(entryTime, exit);
        return Math.max(hours, 1); // Minimum charge for 1 hour
    }


}
