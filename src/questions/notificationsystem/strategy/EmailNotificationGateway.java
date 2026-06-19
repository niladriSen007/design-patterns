package questions.notificationsystem.strategy;

import questions.notificationsystem.entiity.Notification;

public class EmailNotificationGateway implements INotificationGateway {

    @Override
    public void send(Notification notification) {
        String email = notification.getRecipient().getEmail()
                .orElseThrow(() -> new IllegalArgumentException("Email address is required"));
        System.out.println("--- Sending EMAIL ---");
        System.out.println("To: " + email);
        System.out.println("Subject: " + notification.getSubject());
        System.out.println("Body: " + notification.getMessage());
        System.out.println("---------------------\n");
    }
}
