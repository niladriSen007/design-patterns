package questions.notificationsystem.strategy;

import questions.notificationsystem.entiity.Notification;

public interface INotificationGateway {
    void send(Notification notification) throws Exception;
}
