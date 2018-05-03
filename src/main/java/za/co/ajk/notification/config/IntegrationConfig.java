package za.co.ajk.notification.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;


@Configuration
@EnableIntegration
public class IntegrationConfig {

//    @Bean
//    public IntegrationFlow imapIdleFlow() {
//        return IntegrationFlows
//            .from(Mail.imapIdleAdapter("imap://user:pw@localhost:" + imapIdleServer.getPort() + "/INBOX")
//                .autoStartup(true)
//                .searchTermStrategy(this::fromAndNotSeenTerm)
//                .userFlag("testSIUserFlag")
//                .javaMailProperties(p -> p.put("mail.debug", "false")
//                    .put("mail.imap.connectionpoolsize", "5"))
//                .shouldReconnectAutomatically(false)
//                .headerMapper(mailHeaderMapper()))
//            .channel(MessageChannels.queue("imapIdleChannel"))
//            .get();
//    }
}
