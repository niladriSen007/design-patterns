package structural.adapter;

class LegacyGatewayAdapter implements PaymentProcessor {

    private final LegacyGateway legacyGateway;
    private long currentRef;

    public LegacyGatewayAdapter(LegacyGateway legacyGateway) {
        this.legacyGateway = legacyGateway;
    }

    @Override
    public void processPayment(double amount, String currency) {
        System.out.println("Adapter: Translating processPayment() for " + amount + " " + currency);
        legacyGateway.executeTransaction(amount, currency);
        currentRef = legacyGateway.getReferenceNumber(); // Store for later use
    }

    @Override
    public boolean isPaymentSuccessful() {
        return legacyGateway.checkStatus(currentRef);
    }

    @Override
    public String getTransactionId() {
        return "LEGACY_TXN_" + currentRef;
    }
}

public class ThisIsWhyAdapter {
    static void main() {
        PaymentProcessor legacyGateway = new LegacyGatewayAdapter(new LegacyGateway());
        CheckoutService checkoutService = new CheckoutService(legacyGateway);
        checkoutService.checkout(75.50, "USD");

    }
}
