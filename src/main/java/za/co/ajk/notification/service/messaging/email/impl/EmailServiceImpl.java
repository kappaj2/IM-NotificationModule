package za.co.ajk.notification.service.messaging.email.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import za.co.ajk.notification.service.messaging.email.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    
    private JavaMailSender mailSender;
    private MailContentBuilder mailContentBuilder;
    
    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender,
                            MailContentBuilder mailContentBuilder) {
        this.mailSender = mailSender;
        this.mailContentBuilder = mailContentBuilder;
    }
    
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
    
    public void prepareAndSendTemplate(String recipient, String message){
    
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("sample@dolszewski.com");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Sample mail subject");
            String content = mailContentBuilder.build(message);
            messageHelper.setText(content, true);
        };
    
        try {
            mailSender.send(messagePreparator);
        } catch (MailException ex) {
            log.error("Error sending email", ex);
        }
    }
}
