package questions.parkinglot.java.strategy.fee;

import questions.parkinglot.java.entity.ParkingTicket;

public class FlatRateFeeStrategy implements FeeStrategy {

    private final double flatRate;

    public FlatRateFeeStrategy(double flatRate) {
        this.flatRate = flatRate;
    }

    @Override
    public double calculateFee(ParkingTicket ticket) {
        return flatRate;
    }
}
