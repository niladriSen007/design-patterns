package questions.parkinglot.java.entity;

public abstract class Vehicle {

    private final String licensePlate;
    private final VehicleSize size;

    public Vehicle(String licensePlate, VehicleSize size) {
        if (licensePlate == null || licensePlate.isEmpty() || size == null) {
            throw new IllegalArgumentException("License plate cannot be null or empty");
        }
        this.licensePlate = licensePlate;
        this.size = size;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleSize getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "licensePlate='" + licensePlate + '\'' +
                ", size=" + size +
                '}';
    }
}
