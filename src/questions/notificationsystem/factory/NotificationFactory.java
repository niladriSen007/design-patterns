package questions.notificationsystem.factory;

import questions.notificationsystem.entiity.NotificationType;
import questions.notificationsystem.strategy.EmailNotificationGateway;
import questions.notificationsystem.strategy.INotificationGateway;
import questions.notificationsystem.strategy.SmsNotificationGateway;

import java.util.HashMap;
import java.util.Map;

public class NotificationFactory {
    private static final Map<NotificationType, INotificationGateway> gatewayMap = new HashMap<>();

    public static INotificationGateway createGateway(NotificationType type) {
        if (gatewayMap.containsKey(type)) {
            return gatewayMap.get(type);
        }

        INotificationGateway notificationGateway = switch (type) {
            case EMAIL -> new EmailNotificationGateway();
            case SMS -> new SmsNotificationGateway();
            default -> new EmailNotificationGateway();
        };

        gatewayMap.put(type, notificationGateway);
        return notificationGateway;
    }
}
