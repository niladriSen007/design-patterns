package questions.notificationsystem.decorator;

import questions.notificationsystem.entiity.Notification;
import questions.notificationsystem.strategy.INotificationGateway;

public class RetryableNotificationGatewayDecorator implements INotificationGateway {
    private final INotificationGateway wrapperGateway;
    private final int maxRetries;
    private final Long retryDelayMs;

    public RetryableNotificationGatewayDecorator(INotificationGateway wrapperGateway, int maxRetries) {
        this.wrapperGateway = wrapperGateway;
        this.maxRetries = maxRetries;
        this.retryDelayMs = 1000L;
    }


    @Override
    public void send(Notification notification) throws Exception {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                wrapperGateway.send(notification);
                return;
            } catch (Exception e) {
                attempt++;
                System.out.println("Error: Attempt " + attempt + " failed for notification " + notification.getId() + ". Retrying...");
                if (attempt >= maxRetries) {
                    System.out.println(e.getMessage());
                    throw new Exception("Failed to send notification after " + maxRetries + " attempts.", e);
                }
                Thread.sleep(retryDelayMs);
            }
        }
    }
}
