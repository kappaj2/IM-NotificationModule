package za.co.ajk.notification.service.messaging.email;

import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import za.co.ajk.notification.config.EmailConfig;
import za.co.ajk.notification.service.messaging.email.impl.EmailServiceImpl;
import za.co.ajk.notification.service.messaging.email.impl.MailContentBuilder;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmailConfig.class,
                           EmailService.class,
                           EmailServiceImpl.class,
                           MailContentBuilder.class,
                           TemplateEngine.class})
public class EmailServiceTest {
    
    @Autowired
    public TemplateEngine templateEngine;
    
    @Autowired
    private EmailService emailService;
    
    private Fixture fixture;
    
    @Before
    public void setUp() throws Exception {
        fixture = new Fixture();
    }
    
    @After
    public void tearDown() throws Exception {
        fixture.shutdown();
    }
    
    @Test
    public void testSimpleHtmlEmail() throws Exception{
        String testMessage = "TestMessage";
        String recipient = "test@testmail.com";
        
        fixture.givenMessage(testMessage);
        fixture.givenRecipient(recipient);
        fixture.whenSendHtmlEmail();
        fixture.thenMessageContentShouldBe(testMessage);
    }
    
    private class Fixture {
        
        private GreenMail smtpServer;
        private String recipient =null;
        private String message = null;
        private String content = null;
        private MimeMessage[] receivedMessages;
        
        public Fixture(){
            
            ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
            resolver.setTemplateMode("XHTML");
            resolver.setSuffix(".html");
            resolver.setPrefix("templates/");
            templateEngine.setTemplateResolver(resolver);
            
            smtpServer = new GreenMail(new ServerSetup(3025, null, "smtp"));
            smtpServer.start();
        }
        
        public void shutdown(){
            smtpServer.stop();
        }
        
        public void givenRecipient(String recipient){
            this.recipient = recipient;
        }
        
        public void givenMessage(String message){
            this.message = message;
        }
        
        public void whenSendHtmlEmail(){
            emailService.prepareAndSendTemplate(recipient, message);
            content = "<span>" + message + "</span>";
            receivedMessages = smtpServer.getReceivedMessages();
        }
        
        public void thenMessageLengt(int messageLength){
            assertEquals(receivedMessages.length,  messageLength);
        }
        
        public void thenMessageContentShouldBe(String expectedContent) throws Exception{
            String content = (String) receivedMessages[0].getContent();
            assertTrue(content.contains(expectedContent));
        }
    }
    

    
}
