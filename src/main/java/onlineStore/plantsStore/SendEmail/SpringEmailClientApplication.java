package onlineStore.plantsStore.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;


public class SpringEmailClientApplication {

    @Autowired
    private sendVerifyCode service;
    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws MessagingException {


    }
}