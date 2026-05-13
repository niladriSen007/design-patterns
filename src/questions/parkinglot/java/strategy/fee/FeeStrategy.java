package questions.parkinglot.java.strategy.fee;

import questions.parkinglot.java.entity.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket ticket);
}
