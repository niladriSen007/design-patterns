package behavioural.strategy;

record OrderII(float totalWeight, String destinationZone, double orderValue) {
}

interface ShippingStrategy {
    double calculateCost(OrderII order);
}

class FlatRateShippingStrategy implements ShippingStrategy {

    private final double rate;

    FlatRateShippingStrategy(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateCost(OrderII order) {
        System.out.println("Calculating with Flat Rate strategy ($" + rate + ")");
        return rate;
    }
}

class WeightBasedShippingStrategy implements ShippingStrategy {
    private final double ratePerKg;

    public WeightBasedShippingStrategy(double ratePerKg) {
        this.ratePerKg = ratePerKg;
    }

    @Override
    public double calculateCost(OrderII order) {
        System.out.println("Calculating with Weight-Based strategy ($" + ratePerKg + "/kg)");
        return order.totalWeight() * ratePerKg;
    }
}

class DistanceBasedShippingStrategy implements ShippingStrategy {
    private double ratePerKm;

    public DistanceBasedShippingStrategy(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    @Override
    public double calculateCost(OrderII order) {
        System.out.println("Calculating with Distance-Based strategy for zone: " + order.destinationZone());
        return switch (order.destinationZone()) {
            case "ZoneA" -> ratePerKm * 5.0;
            case "ZoneB" -> ratePerKm * 7.0;
            default -> ratePerKm * 10.0;
        };
    }
}

class ThirdPartyApiShippingStrategy implements ShippingStrategy {
    private final double baseFee;
    private final double percentageFee;

    public ThirdPartyApiShippingStrategy(double baseFee, double percentageFee) {
        this.baseFee = baseFee;
        this.percentageFee = percentageFee;
    }

    @Override
    public double calculateCost(OrderII order) {
        System.out.println("Calculating with Third-Party API strategy.");
        // Simulate API call
        return baseFee + (order.orderValue() * percentageFee);
    }
}

class ShippingService {
    private ShippingStrategy shippingStrategy;

    public ShippingService(ShippingStrategy shippingStrategy) {
        this.shippingStrategy = shippingStrategy;
    }

    public void changeShippingStrategy(ShippingStrategy shippingStrategy) {
        System.out.println("ShippingCostService: Strategy changed to " + shippingStrategy.getClass().getSimpleName());
        this.shippingStrategy = shippingStrategy;
    }

    public double calculateShippingCost(OrderII order) {
        if (shippingStrategy == null) {
            throw new IllegalStateException("Shipping strategy not set.");
        }
        double cost = shippingStrategy.calculateCost(order);
        System.out.println("ShippingCostService: Final Calculated Shipping Cost: $" + cost +
                " (using " + shippingStrategy.getClass().getSimpleName() + ")");
        return cost;
    }
}

public class ThisIsWhyStrategy {
    static void main() {
        OrderII order = new OrderII(12, "ZoneA", 1200);

        // Create different strategy instances
        ShippingStrategy flatRate = new FlatRateShippingStrategy(10.0);
        ShippingStrategy weightBased = new WeightBasedShippingStrategy(2.5);
        ShippingStrategy distanceBased = new DistanceBasedShippingStrategy(5.0);
        ShippingStrategy thirdParty = new ThirdPartyApiShippingStrategy(7.5, 0.02);

        ShippingService shippingService = new ShippingService(flatRate);
        System.out.println("--- Order 1: Using Flat Rate (initial) ---");
        shippingService.calculateShippingCost(order);

        System.out.println("\n--- Order 1: Changing to Weight-Based ---");
        shippingService.changeShippingStrategy(weightBased);
        shippingService.calculateShippingCost(order);

        System.out.println("\n--- Order 1: Changing to Distance-Based ---");
        shippingService.changeShippingStrategy(distanceBased);
        shippingService.calculateShippingCost(order);

        System.out.println("\n--- Order 1: Changing to Third-Party API ---");
        shippingService.changeShippingStrategy(thirdParty);
        shippingService.calculateShippingCost(order);
    }
}
