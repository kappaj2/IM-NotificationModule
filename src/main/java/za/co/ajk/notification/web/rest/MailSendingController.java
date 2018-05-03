package za.co.ajk.notification.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.ajk.notification.service.messaging.email.EmailService;

@RestController
@RequestMapping("/api/mail")
public class MailSendingController {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    
    private EmailService emailService;
    
    public MailSendingController(EmailService emailService) {
        this.emailService = emailService;
    }
    
    @GetMapping("/send-simple")
    public void sendEmail(){
        log.info("Sending test message");
        emailService.sendSimpleMessage("kappaj@gmail.com", "Test email subject", "Test body text");
    }
    
    @GetMapping("/send-html")
    public void sendHtmlEmail(){
        log.info("Sending test HTML message");
        emailService.prepareAndSendTemplate("kappaj@gmail.com", "Test email subject");
    }
}
