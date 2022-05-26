package onlineStore.plantsStore.SendEmail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

public class sendVerifyCode {
    public String setCode(){
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        String verify = uuidAsString.substring(0,5).toUpperCase();
        return verify;
    }
    public void sendCode(String toMail) {

        final String username = "plantsstore2022@gmail.com";
        final String password = "sdudpunotibtfoor";
        String verify=setCode();
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toMail)
            );
            message.setSubject("Dear Client Welcome to Plant Store");
            message.setText("\n\n The verify code is:       "+ verify);
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}