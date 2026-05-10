package behavioural.strategy;

record Order(float totalWeight, String destinationZone, double orderValue) {
}

class ShippingCostCalculatorNaive {
    public void calculateShippingCost(Order order, String strategyType) {
        double cost = 0.0;

        if ("FLAT_RATE".equalsIgnoreCase(strategyType)) {
            System.out.println("Calculating with Flat Rate strategy.");
            cost = 10.0;

        } else if ("WEIGHT_BASED".equalsIgnoreCase(strategyType)) {
            System.out.println("Calculating with Weight-Based strategy.");
            cost = order.totalWeight() * 2.5;

        } else if ("DISTANCE_BASED".equalsIgnoreCase(strategyType)) {
            System.out.println("Calculating with Distance-Based strategy.");
            if ("ZoneA".equals(order.destinationZone())) {
                cost = 5.0;
            } else if ("ZoneB".equals(order.destinationZone())) {
                cost = 12.0;
            } else {
                cost = 20.0; // fallback
            }

        } else if ("THIRD_PARTY_API".equalsIgnoreCase(strategyType)) {
            System.out.println("Calculating with Third-Party API strategy.");
            // Simulated external call
            cost = 7.5 + (order.orderValue() * 0.02);

        } else {
            throw new IllegalArgumentException("Unknown shipping strategy: " + strategyType);
        }

        System.out.println("Calculated Shipping Cost: $" + cost);
    }
}

public class WhyStrategy {
    static void main() {
        ShippingCostCalculatorNaive shippingCostCalculatorNaive = new ShippingCostCalculatorNaive();
        Order order = new Order(12, "ZoneA", 1200);
        System.out.println("--- Order 1 ---");
        shippingCostCalculatorNaive.calculateShippingCost(order, "FLAT_RATE");
    }
}
