package questions.notificationsystem.strategy;

import questions.notificationsystem.entiity.Notification;

public class SmsNotificationGateway implements INotificationGateway {
    @Override
    public void send(Notification notification) {
        String phone = notification.getRecipient().getPhoneNumber()
                .orElseThrow(() -> new IllegalArgumentException("Phone number is required for SMS notification."));
        System.out.println("--- Sending SMS Notification ---");
        System.out.println("To Device : " + phone);
        System.out.println("Title: " + notification.getSubject()); // Re-using subject for title
        System.out.println("Body: " + notification.getMessage());
        System.out.println("---------------------------------\n");
    }
}
