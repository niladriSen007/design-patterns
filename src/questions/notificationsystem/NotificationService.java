package questions.notificationsystem;

import questions.notificationsystem.decorator.RetryableNotificationGatewayDecorator;
import questions.notificationsystem.entiity.Notification;
import questions.notificationsystem.factory.NotificationFactory;
import questions.notificationsystem.strategy.INotificationGateway;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationService {
    private final ExecutorService executorService;

    public NotificationService(int poolSize) {
        executorService = Executors.newFixedThreadPool(poolSize);
    }

    public void sendNotification(Notification notification) {
        executorService.submit(() -> {
            INotificationGateway retryableNotificationGatewayDecorator = new RetryableNotificationGatewayDecorator(
                    NotificationFactory.createGateway(notification.getType()),
                    3
            );
            try {
                retryableNotificationGatewayDecorator.send(notification);
            } catch (Exception e) {
                System.out.println("Exception while sending notification: " + e);
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
