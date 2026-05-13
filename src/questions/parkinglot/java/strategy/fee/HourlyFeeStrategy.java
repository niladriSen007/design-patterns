package questions.parkinglot.java.strategy.fee;

import questions.parkinglot.java.entity.ParkingTicket;

public class HourlyFeeStrategy implements FeeStrategy {

    private final float hourlyFee;

    public HourlyFeeStrategy(float hourlyFee) {
        this.hourlyFee = hourlyFee;
    }

    @Override
    public double calculateFee(ParkingTicket ticket) {
        return ticket.getTotalParkingDuration() *  hourlyFee;
    }
}
