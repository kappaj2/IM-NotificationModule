package za.co.ajk.notification.cucumber.stepdefs;

import za.co.ajk.notification.NotificationModuleApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = NotificationModuleApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
