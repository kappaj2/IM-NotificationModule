package za.co.ajk.notification.service.messaging.email;

public interface EmailService {
    
    void sendSimpleMessage(String to, String subject, String text);
    void prepareAndSendTemplate(String recipient, String message);
}
